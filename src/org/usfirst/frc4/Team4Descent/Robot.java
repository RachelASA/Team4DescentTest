package org.usfirst.frc4.Team4Descent;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc4.Team4Descent.commands.Autonomous;
//import org.usfirst.frc4.Team4Descent.commands.DriveStraightAuton;
import org.usfirst.frc4.Team4Descent.subsystems.Camera;
import org.usfirst.frc4.Team4Descent.subsystems.Chassis;
import org.usfirst.frc4.Team4Descent.subsystems.Pneumatics;
import org.usfirst.frc4.Team4Descent.subsystems.tRex;

public class Robot extends IterativeRobot {

    Command autonomousCommand;
    
    //Command DriveStraightAuton;
    

    public static Chassis chassis;
    public static Pneumatics pneumatics;
    public static Camera camera;
<<<<<<< HEAD
    public static tRex tRex;
=======
    public static OI oi;
>>>>>>> 8ec0ffaced068fbf54514b31d9c780fdfc1094ec
    
    public void robotInit() {
        
        //DriveStraightAuton  = new DriveStraightAuton(-0.8, 0);
        RobotMap.init();
        chassis             = new Chassis();
        pneumatics          = new Pneumatics();
        camera              = new Camera();
        
        autonomousCommand   = new Autonomous();
        
        oi                  = new OI();
    }

    public void autonomousInit() {
        
        //DriveStraightAuton.start();
        if (autonomousCommand != null) autonomousCommand.start();
    }
    
    public void autonomousPeriodic() {
        
        Scheduler.getInstance().run();
    
    }

    public void teleopInit() {
        
        //DriveStraightAuton.cancel();
        
        if (autonomousCommand != null) autonomousCommand.cancel();
    
    }
    
    public void teleopPeriodic() {
        
        Scheduler.getInstance().run();
    
    }
    
    public void testPeriodic() {LiveWindow.run();}
}
