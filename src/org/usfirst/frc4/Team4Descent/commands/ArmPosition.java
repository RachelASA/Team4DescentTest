/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc4.Team4Descent.commands;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import org.usfirst.frc4.Team4Descent.Robot;
import org.usfirst.frc4.Team4Descent.RobotMap;

/**
 *
 * @author Rachel
 */
public class ArmPosition extends Command {
    
    double speed;
    double turn;
    
    double Kp;
    double Kd;
    double botAngle;
    double previousP;
    double magicVar;
    AnalogChannel shooterPOT = RobotMap.shooterPOT;
    
    public ArmPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.tRex);
        speed = -.5;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Kp          = 0.11;
        Kd          = 0.22;
        magicVar    = 0.03 * 360; //Percent
        //shooterPOT.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double setPoint = 0;
        double processVariable;
        processVariable = shooterPOT.getVoltage();
        
        double PID_P = -(setPoint - processVariable);  
        double PID_D = (PID_P - previousP);
        
        double PID = (PID_P * Kp) + (PID_D * Kd);
        
        //Robot.tRex(Robot.oi.rightY(PID)); //angle fix

        previousP = processVariable;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
