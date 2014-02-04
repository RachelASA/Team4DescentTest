/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc4.Team4Descent.subsystems;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4.Team4Descent.Robot;
import org.usfirst.frc4.Team4Descent.RobotMap;

/**
 *
 * @author Rachel
 */
public class tRex extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public SpeedController tRexVictor  = RobotMap.shooterTRexVictor;
    public AnalogChannel POT = RobotMap.shooterPOT;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    /*public void setTrexSpeed() {
        tRexVictor.set(s);
        SmartDashboard.putNumber("TRex given", Robot.tRex.tRexVictor.get());
    }*/
    public double getPOT(){
        return POT.getVoltage();
    }
    public double getDegrees(){
        return POT.getVoltage();//TODO:multiply/add constant
    }
    
}

