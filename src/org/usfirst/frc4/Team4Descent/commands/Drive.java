package org.usfirst.frc4.Team4Descent.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4.Team4Descent.Robot;
import org.usfirst.frc4.Team4Descent.RobotMap;
import org.usfirst.frc4.Team4Descent.subsystems.Chassis;
import org.usfirst.frc4.Team4Descent.commands.DriveStraight;

/**
 *
 * @author Justin
 */
public class Drive extends Command {
    
    boolean 
            quickTurn = false;
    
    double 
            speed = 0,
            rotateValue = 0,
            driveErrorConst = 0,
            threshold = .1;
    
    static String STATE = "NORMAL";
    
    public static String getSTATE(){
        return STATE;
    }
    
    public Drive() {	
        requires(Robot.chassis);
    }

    protected void initialize() {
    }

    protected void execute() {
        
        //Arcade 2
        
        Robot.chassis.arcadeDrive(Robot.oi.rightY(Robot.oi.driveCont), -Robot.oi.leftX(Robot.oi.driveCont));
        
        //TANK
//        if(Robot.oi.Triggers(Robot.oi.driveCont, true, threshold) < 0){//For drivestraight
//            STATE = "DRIVESTRAIGHT";
//            Robot.chassis.tankDrive(Robot.oi.rightY(Robot.oi.driveCont), Robot.oi.rightY(Robot.oi.driveCont));
//        }else if(Robot.oi.Triggers(Robot.oi.driveCont, true, threshold) > 0){//For reversedrive
//            STATE = "REVERSED";
//            Robot.chassis.tankDrive(-Robot.oi.leftY(Robot.oi.driveCont), -Robot.oi.rightY(Robot.oi.driveCont));
//        }else{//For tankdrive
//            STATE = "TANK";
//            Robot.chassis.tankDrive(Robot.oi.rightY(Robot.oi.driveCont), Robot.oi.leftY(Robot.oi.driveCont));
//        }
        //ARCADE
//        speed = Robot.oi.rightY(Robot.oi.driveCont);
//        driveErrorConst = (speed == 0) ? 0 : ((speed > 0) ? -.1 : .3);
//        rotateValue = -Robot.oi.RightX(Robot.oi.driveCont) + driveErrorConst;
//        rotateValue = (rotateValue > 1) ? 1 : (rotateValue < -1 ? -1 : rotateValue);
//        if(Robot.oi.Triggers(Robot.oi.driveCont, true, threshold) < 0){//For drivestraight
//            STATE = "DRIVESTRAIGHT";
//            Robot.chassis.arcadeDrive(speed, 0);
//        }else if(Robot.oi.Triggers(Robot.oi.driveCont, true, threshold) > 0){//For reversedrive
//            STATE = "REVERSE";
//            Robot.chassis.arcadeDrive(-speed, rotateValue);
//        }else{//For arcade
//            STATE = "ARCADE";
//            Robot.chassis.arcadeDrive(speed, rotateValue);
//        }
        //Phoenix
//        speed = Robot.oi.rightY(Robot.oi.driveCont);
//        driveErrorConst = (speed == 0) ? 0 : ((speed > 0) ? -.1 : .3);
//        rotateValue = -Robot.oi.RightX(Robot.oi.driveCont) + driveErrorConst;
//        rotateValue = (rotateValue > 1) ? 1 : (rotateValue < -1 ? -1 : rotateValue);
//        quickTurn = Robot.oi.driveCont.getRawButton(Robot.oi.LBumper);
//        if(Robot.oi.Triggers(Robot.oi.driveCont, true, threshold) < 0){//For drivestraight
//            STATE = "DRIVESTRAIGHT";
//            Robot.chassis.phoenixDrive(speed, 0, quickTurn);
//        }else if(Robot.oi.Triggers(Robot.oi.driveCont, true, threshold) > 0){//For reversedrive
//            STATE = "REVERSE";
//            Robot.chassis.phoenixDrive(-speed, rotateValue, quickTurn);
//        }else{//For arcade
//            STATE = "ARCADE";
//            Robot.chassis.phoenixDrive(speed, rotateValue, quickTurn);
//        }
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.chassis.stop();
    }

    protected void interrupted() {
        end();
    }
}
