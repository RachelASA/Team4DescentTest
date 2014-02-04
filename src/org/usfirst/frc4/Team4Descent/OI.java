package org.usfirst.frc4.Team4Descent;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4.Team4Descent.commands.Autonomous;
import org.usfirst.frc4.Team4Descent.commands.Drive;
import org.usfirst.frc4.Team4Descent.commands.DriveStraight;
import org.usfirst.frc4.Team4Descent.commands.Shift;
import org.usfirst.frc4.Team4Descent.commands.ShiftChassisHigh;
import org.usfirst.frc4.Team4Descent.commands.ShiftChassisLow;
import org.usfirst.frc4.Team4Descent.commands.StartCompressor;
import org.usfirst.frc4.Team4Descent.commands.StopCompressor;

public class OI {
    
    public Joystick 
            driveCont,
            opCont;
    
    public JoystickButton 
            shiftHighButton,
            shiftLowButton,
            compStartButton,
            compStopButton,
            shooterMaxSpeed,
            shooterStop,
            tRexButton,
//            cameraAimButton,
            shooterExtend,
            
            lineDrive,
            shift;      //shift test
    
    public Gyro gyro;
    
    //Xbox Mappings    
    public static final int 
            AButton = 1,
            BButton = 2,
            XButton = 3,
            YButton = 4,
            LBumper = 5,
            RBumper = 6,
            BackButton = 7,
            StartButton = 8;
    
    public OI() {
        
        driveCont   = RobotMap.driveCont;
        opCont      = RobotMap.opCont;
        gyro        = RobotMap.gyro;
        
        
        
        
        //shiftLowButton = new JoystickButton(driveCont, LBumper);
        //shiftLowButton.whenPressed(new ShiftChassisLow());
        //shiftHighButton = new JoystickButton(driveCont, RBumper);
        //shiftHighButton.whenPressed(new ShiftChassisHigh());
        
        //compStartButton = new JoystickButton(driveCont, StartButton);
        //compStartButton.whenPressed(new StartCompressor());
        //compStopButton = new JoystickButton(driveCont, BackButton);
        //compStopButton.whenPressed(new StopCompressor());
        
        lineDrive   = new JoystickButton(driveCont, RBumper);
        lineDrive.whileHeld(new DriveStraight());
        
        shift  = new JoystickButton(driveCont, LBumper);
        shift.whenPressed(new Shift());
        
        //cameraAimButton = new JoystickButton(opCont, XButton);
        //cameraAimButton.whenPressed(new CameraAim());
        
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous", new Autonomous());
        SmartDashboard.putData("StartCompressor", new StartCompressor());
        SmartDashboard.putData("StopCompressor", new StopCompressor());
        SmartDashboard.putData("ShiftHigh", new ShiftChassisHigh());
        SmartDashboard.putData("ShiftLow", new ShiftChassisLow());
        SmartDashboard.putData("TankDrive", new Drive());
        SmartDashboard.putData("Autonomous", new Autonomous());
        //SmartDashboard.putData("CameraAim", new CameraAim());
        //SmartDashboard.putData("DriveStraight", new DriveStraight());
    }
    
    //Filered Joystick Controllers
    public double RightX(Joystick cont)                             {return lowPassFilter(cont.getRawAxis(4), .2);}
    public double rightY(Joystick cont)                             {return lowPassFilter(cont.getRawAxis(5), .2);}
    public double leftX(Joystick cont)                              {return lowPassFilter(cont.getRawAxis(1), .2);}
    public double leftY(Joystick cont)                              {return lowPassFilter(cont.getRawAxis(2), .2);}
    public double DPadHorizontal(Joystick cont)                     {return lowPassFilter(cont.getRawAxis(6), .1);}
    public double Triggers(Joystick cont, boolean highPass, double threshhold){
        if(highPass){return highPassFilter(cont.getRawAxis(3), threshhold);}
        else        {return lowPassFilter (cont.getRawAxis(3), threshhold);}
    }
    //Filters
    public double lowPassFilter(double n, double tolerance)                     {return Math.abs(n) > tolerance? n: 0;}
    public double midPassFilter(double value, double exact, double tolerance)   {return Math.abs(value - exact) < tolerance? exact : value;}
    public boolean withinTolerance(double value, double exact, double tolerance){return Math.abs(value - exact) < tolerance? true : false;}
    public static double highPassFilter(double n, double tolerance){
        if      (n > 1 - tolerance) {return 1;}
        else if (n < tolerance - 1) {return -1;}
        return n;
    }
    
}
