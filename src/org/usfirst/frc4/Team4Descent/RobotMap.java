package org.usfirst.frc4.Team4Descent;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotMap {
    
    //Controllers
    public static Joystick 
            driveCont = new Joystick(1),
            opCont = new Joystick(2);

    //Speed Controllers
    public static SpeedController
            chassisLeft,
            chassisRight,
            shooterFrontVictor,
            shooterBackVictor,
            shooterTRexVictor;
    
    //Robot Drive
    public static RobotDrive 
            chassisDrive;
    
    //Pneumatics
    public static Compressor 
            pneumaticsCompressor;
    public static Solenoid 
            pneumaticsChassisSolenoid,
            pneumaticsShootSolenoid;
    //Analog Channels
    public static AnalogChannel 
            shooterPOT;
    
    //Gyro
    public static Gyro gyro;
    
    public static void init(){
        //Controllers
        driveCont = new Joystick(1);
        opCont    = new Joystick(2);
        
        //Actuators
        chassisLeft         = new Victor(1, 1);
        chassisRight        = new Victor(1, 2);
        shooterFrontVictor  = new Victor(1, 7);     
        shooterBackVictor   = new Victor(1, 8);
        shooterTRexVictor   = new Victor(1, 5);
        
        //Robot Drive
        chassisDrive = new RobotDrive(chassisLeft, chassisRight);
        chassisDrive.setSafetyEnabled(true);
        chassisDrive.setExpiration(0.1);
        chassisDrive.setSensitivity(0.5);
        chassisDrive.setMaxOutput(1.0);
        
        //PNEUMATICS
        pneumaticsCompressor        = new Compressor(1, 1, 1, 2);
        pneumaticsChassisSolenoid   = new Solenoid(8);
        pneumaticsShootSolenoid     = new Solenoid(7);

        //8 Shifting
        //7 Shooter
        
        //Analog Channels
        shooterPOT = new AnalogChannel(1,1);
        
        //Gyro
        gyro = new Gyro(2);
        
        ////////******LiveWindow******////////
        
        //Actuators
        LiveWindow.addActuator("Chassis", "Left", (Victor) chassisLeft);
        LiveWindow.addActuator("Chassis", "Right", (Victor) chassisRight);
        LiveWindow.addActuator("Shooter", "Front", (Victor) shooterFrontVictor);
        LiveWindow.addActuator("Shooter", "Back", (Victor) shooterBackVictor);
        LiveWindow.addActuator("Shooter", "TRex", (Victor) shooterTRexVictor);
        LiveWindow.addSensor("Shooter", "POT", shooterPOT);
        SmartDashboard.getNumber("Gyro", gyro.getAngle());
    }
}
