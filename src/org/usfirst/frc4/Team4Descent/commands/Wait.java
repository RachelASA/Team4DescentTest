package org.usfirst.frc4.Team4Descent.commands;
import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author sysadmin
 */
public class Wait extends Command {
    private final Timer timer;
    private final double seconds;
    
    public Wait(double seconds) {
        this.seconds = seconds;
        timer = new Timer();
    }

    protected void initialize() {
        timer.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return timer.get() > (seconds);
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}