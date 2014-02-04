package org.usfirst.frc4.Team4Descent.commands;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4.Team4Descent.Robot;
import org.usfirst.frc4.Team4Descent.RobotMap;

/**
 *
 * @author James
 */
public class DriveStraight extends Command {
    
    double 
            turn,
            
            Kp,
            Kd,
            botAngle,
            previousP,
            magicVar;
    
    Gyro gyro = RobotMap.gyro;
    
    public DriveStraight() {
        requires(Robot.chassis);
        //speed = Robot.oi.rightY(Robot.oi.driveCont);
    }


    protected void initialize() {
        Kp          = 0.11;
        Kd          = 0.22;
        magicVar    = 0.03 * 360; //Percent
        gyro.reset();
    }

    protected void execute() {
        
        
        double 
                setPoint = 0,
                processVariable = gyro.getAngle(),
                
                PID_P = -(setPoint - processVariable),
                PID_D = (PID_P - previousP),
                
                PID = (PID_P * Kp) + (PID_D * Kd);
        
        Robot.chassis.arcadeDrive(Robot.oi.rightY(Robot.oi.driveCont), PID); //angle fix
        //Robot.chassis.arcadeDrive(speed, PID);
        
        previousP = processVariable;
        
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