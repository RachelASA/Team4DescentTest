package org.usfirst.frc4.Team4Descent.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4.Team4Descent.Robot;

public class CameraAim extends Command {
    
    double rotateSpeed = .1;//<--for testing: CHANGE
    double tolerance = 3;//DEGREES
    
    public CameraAim() {
        //requires(Robot.chassis);
    }

    protected void initialize() {
        Robot.chassis.arcadeDrive(0, (Robot.camera.findRectangleHAngularError() < 0) ? rotateSpeed : -rotateSpeed);
    }
    
    protected void execute() {
    }
    
    protected boolean isFinished() {
        return (Robot.oi.withinTolerance(Robot.camera.findRectangleHAngularError(), 0, tolerance)
                ||Robot.oi.opCont.getRawButton(Robot.oi.BButton));
    }

    protected void end() {
        Robot.chassis.stop();
    }

    protected void interrupted() {
        end();
    }
}
