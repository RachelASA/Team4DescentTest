package org.usfirst.frc4.Team4Descent.subsystems;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCamera.ResolutionT;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.util.SortedVector;


/**
 *
 * @author JULIAN
 * @author JUSTIN
 */
public class Camera {
    private static SortedVector lastResult = null;
    public static boolean getto_lock = false;
    public static Thread imageProcessor = null;
    private static double timestamp = 0;
    static double targetHeight = 104;
    static double height = 60;
    final static int X_IMAGE_RES = 320;          //X Image resolution in pixels, should be 160, 320 or 640
    final static double VIEW_ANGLE = 43.5;       //Axis 206 camera
//    final double VIEW_ANGLE = 48;       //Axis M1011 camera
    
    public static void startProcessing() {
        if (!getto_lock) {
            new Thread() {
                public void run() {
                    getto_lock = true;
                    Camera.calculate(null);
                    timestamp = Timer.getFPGATimestamp();
                    getto_lock = false;
                }
                
            }.start();
        }
    }
    
    public static double getLastImageTimestamp() {
        return timestamp;
    }

    public static double getVerticalPixels() {
        Rectangle rectangle = getTopRectangle();
        if (rectangle != null)
            return Math.abs((2*(rectangle.coy-rectangle.h/rectangle.ih)));
        return 0;
    }
    
    public static class Rectangle {
        public int x,y,w,h;
        public double cox, coy;
        public double score;
        public int iw, ih;
    }
    
    public static class RectangleComparator implements SortedVector.Comparator {

        public int compare(Object object1, Object object2) {
            //returns: -1, 0, or 1 if the first object is less than, equal to, or greater than the second, respectively
            Rectangle a = (Rectangle)object1;
            Rectangle b = (Rectangle)object2;
            //sort by score first
            if (a.score != b.score)
                //if the two are not equal in score then return according to note at top of method
                return a.score > b.score?1:-1;
            
            //sort by height second
            if (a.y != b.y)
                return a.score > b.score?1:-1;
            
            //if there was no differentiation yet then they're the same height
            return 0;
            
        }
        
    }
    
    public static void setFPS(int FPS) {
        AxisCamera.getInstance().writeMaxFPS(FPS);
    }
    
    public static void setSize(ResolutionT resolution) {
        AxisCamera.getInstance().writeResolution(resolution);
    }
    
    public static boolean newImage() {
        return AxisCamera.getInstance().freshImage();
    }
    
    public static SortedVector findRectangle() {
        if (newImage())
            startProcessing();
        return lastResult;
    }
    
    private static void calculate(RGBImage input) {
        
        //TODO: replace this with some kind of timery thing
        SortedVector result = new SortedVector(new RectangleComparator());
        
        ColorImage image = input;
        BinaryImage threshold = null;
        BinaryImage bigObjects = null;
        BinaryImage convexHull = null;
                
        try {
            
            //Get an image from the camera
            if (input == null) {
                try {
                    if (!newImage()) {
                        return;
                    }
                    image = AxisCamera.getInstance().getImage();    
                } catch (AxisCameraException ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
            }
            
            //Get constants for threshold operation
            Preferences pref = Preferences.getInstance();
            //VisionErrChk(IVA_CLRThreshold(image, 0, 255, 0, 45, 187, 255, IMAQ_HSL));
            int hueLow = pref.getInt("cameraHueLow", 170);//Low Blue
            int hueHigh = pref.getInt("cameraHueHigh", 210);//High Blue
            int saturationLow = pref.getInt("cameraSaturationLow", 62);
            int saturationHigh = pref.getInt("cameraSaturationHigh", 255);
            int luminenceLow = pref.getInt("cameraLuminenceLow", 143);
            int luminenceHigh = pref.getInt("cameraLuminenceHigh", 255);

            //Apply threshold (converts ColorImage to BinaryImage)
            threshold = image.thresholdHSL(hueLow, hueHigh, saturationLow, saturationHigh, luminenceLow, luminenceHigh);
            //Remove small objects
            convexHull = threshold.convexHull(true);
            bigObjects = convexHull.removeSmallObjects(false, 2);
            //Do convexHull
            
            
            //Populate sortable vector with hoop rectangle information
            int particles = bigObjects.getNumberParticles();
            for (int i=0; i < particles; i++ ) {
                ParticleAnalysisReport report = bigObjects.getParticleAnalysisReport(i);
                Rectangle temp = new Rectangle();
                temp.x = report.boundingRectLeft;
                temp.y = report.boundingRectTop;
                temp.w = report.boundingRectWidth;
                temp.h = report.boundingRectHeight;
                temp.cox = report.center_mass_x_normalized;
                temp.coy = report.center_mass_y_normalized;
                temp.score = (report.particleArea) / (temp.w * temp.h);
                temp.iw = report.imageWidth;
                temp.ih = report.imageHeight;
                result.addElement(temp);
            }
            //Sort that vector
            result.sort();
        } catch (NIVisionException e) {
        } finally {
            //free the C-bound resources that we're done with now
            try {
                if (image != null)
                    image.free();
                if (threshold != null)
                    threshold.free();
                if (bigObjects != null)
                    bigObjects.free();
                if (convexHull != null)
                    convexHull.free();
            } catch (NIVisionException ex) {
            }
        }
        lastResult = result;
    }
    
    public static Rectangle getTopRectangle() {
        SortedVector rectangles = findRectangle();
        if (haveRectangle() &&  rectangles.size() != 0)
            return (Rectangle) rectangles.firstElement();
        else
            return null;
    }
    
    public static boolean haveRectangle() {
        SortedVector rectangles = findRectangle();
        if (rectangles == null || rectangles.size() == 0)
            return false;
        double minScore = Preferences.getInstance().getDouble("minimumRectangleScore", 0);
        for(int i=0; i < rectangles.size(); i++) {
            Rectangle rect = (Rectangle)rectangles.elementAt(i);
            if (rect.score >= minScore)
                return true;
        }
        return false;
    }
    
    public static double findRectangleHAngularError() {
        Rectangle rectangle = getTopRectangle();
        double errorToAngle = Preferences.getInstance().getDouble("errorToAngle", 23.5);
        //rectangle.cox is in terms of -1 to 1, so our errorToAngle is basically
        //the field of view of the camera
        return (rectangle.cox * errorToAngle);
    }
    
    public static double findRectangleVAngularError() {
        Rectangle rectangle = getTopRectangle();
        double errorToAngle = Preferences.getInstance().getDouble("errorToAngle", VIEW_ANGLE*3/4);
        //rectangle.coy is in terms of -1 to 1, so our errorToAngle is basically
        //the field of view of the camera
        return (rectangle.coy * errorToAngle);
    }

    public static double findRectangleDistance(Rectangle rectangle) {
        if (rectangle == null)
            return 0;
        return X_IMAGE_RES * targetHeight / (height * 12 * 2 * Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
    }
    
    public static void printRectangeList(SortedVector rectangles) {
        for(int i=0; i < rectangles.size(); i++) {
            Rectangle rectangle = (Rectangle)rectangles.elementAt(i);
            System.out.println(i+" x:"+rectangle.x
                    +" y:"+rectangle.y
                    +" w:"+rectangle.w
                    +" h:"+rectangle.h
                    +" score:"+rectangle.score);
        }
    }
    
    public double arctan(double opposite, double adjacent, int n){
        double theta = 0;
        for(int i = 0; i < n; i++){
            theta += (pow(-1, i + 1) * pow(opposite / adjacent, 2 * i - 1) / (2 * i + 1));
        }
        return theta;
    }
    
    public double pow(double base, int exponent){
        if(exponent == -1){
            return (1 / base);
        }
        int answer = 1;
        for(int i = 0; i < exponent; i++){
            answer *= base;
        }
        return answer;
    }
}
