/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc4.Team4Descent.commands;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4.Team4Descent.Robot;
import org.usfirst.frc4.Team4Descent.RobotMap;

/**
 *
 * @author james2
 */
public class DriveStraightAuton extends Command {
    
    double 
            speed,
            turn,
            Kp,
            Kd,
            botAngle,
            previousP,
            setPoint,
            totalTime;
    
    Gyro gyro;
    
    Timer DST; //drive straight time
    
    public DriveStraightAuton (double s, double angle, double time) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        speed = s;
        setPoint = angle;
        totalTime = time;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Kp          = 0.09;
        Kd          = 0.10;
        
        gyro = RobotMap.gyro;
        
        DST = new Timer();
        
        DST.start();
        
        gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        
        double processVariable = gyro.getAngle();
        
        double PID_P    = -(setPoint - processVariable);  
        double PID_D    = (PID_P - previousP);
        
        double PID      = (PID_P * Kp) + (PID_D * Kd);
        
        Robot.chassis.arcadeDrive(speed, PID); //angle fix

        previousP = processVariable;
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
        return DST.get() > totalTime;
        
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.chassis.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
