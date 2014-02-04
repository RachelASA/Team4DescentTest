package org.usfirst.frc4.Team4Descent.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4.Team4Descent.Robot;

/**
 *
 * @author Justin
 */
public class StopCompressor extends Command {
    
    public StopCompressor() {
    }

    protected void initialize() {
        Robot.pneumatics.compressorStop();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
