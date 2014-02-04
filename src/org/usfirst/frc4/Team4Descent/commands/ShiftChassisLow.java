package org.usfirst.frc4.Team4Descent.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4.Team4Descent.Robot;

public class  ShiftChassisLow extends Command {
    public ShiftChassisLow() {
    }

    protected void initialize() {
        Robot.pneumatics.shiftLow(Robot.pneumatics.chassisSolenoid);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
}
