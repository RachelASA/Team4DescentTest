package org.usfirst.frc4.Team4Descent.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc4.Team4Descent.RobotMap;
import org.usfirst.frc4.Team4Descent.commands.Drive;

/**
 *
 * @author Justin
 */
public class Chassis extends Subsystem {
    public SpeedController 
            left = RobotMap.chassisLeft,
            right = RobotMap.chassisRight;
    
    RobotDrive drive = RobotMap.chassisDrive;
//
//    double driveWheelCirc = 4 * Math.PI;
//    double ppr = 720; //pulse per roation (360 *2 because of two channel encoder type)
//    
//    public double getDPP(){
//        return ppr / driveWheelCirc;
//    }
        
    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }
    
    public void tankDrive(double l, double r) {
        drive.tankDrive(l, r);
        //FOR TESTING
        SmartDashboard.putNumber("LeftGiven", l);
        SmartDashboard.putNumber("LeftReceived", left.get());
        SmartDashboard.putNumber("RightGiven", r);
        SmartDashboard.putNumber("RightReceived", right.get()); 
        //
        SmartDashboard.putString("Drive STATE", Drive.getSTATE());
    }
    
    public void arcadeDrive(double s, double r){ 
        drive.arcadeDrive(s, r);
        SmartDashboard.putNumber("LeftRecieved", left.get());
        SmartDashboard.putNumber("RightRecieved", right.get());
        SmartDashboard.putString("Drive STATE", Drive.getSTATE());
    }
    
//    public void phoenixDrive(double s, double r, boolean quickTurn){
//        drive.tankDrive(s + r, s - r);
//        SmartDashboard.putNumber("LeftRecieved", left.get());
//        SmartDashboard.putNumber("RightRecieved", right.get());
//        SmartDashboard.putString("Drive STATE", Drive.getSTATE());
//    }
    
    public void stop() {
        drive.drive(0, 0);
    }
}
