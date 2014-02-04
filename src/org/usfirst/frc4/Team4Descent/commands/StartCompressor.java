package org.usfirst.frc4.Team4Descent.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4.Team4Descent.Robot;

/**
 *
 * @author Justin
 */
public class StartCompressor extends Command {
    
    public StartCompressor() {
    }

    protected void initialize() {
        Robot.pneumatics.compressorStart();
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
