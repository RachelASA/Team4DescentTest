package org.usfirst.frc4.Team4Descent.commands.sandbox;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4.Team4Descent.Robot;

/**
 *
 * @author Justin
 */
public class Victors extends Command {
    
    SpeedController speedController;
    
    public Victors() {
    }
    
    public Victors(SpeedController sController) {
        speedController = sController;
    }

    protected void initialize() {
    }

    protected void execute() {
        speedController.set(Robot.oi.leftY(Robot.oi.opCont));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        speedController.set(0);
    }

    protected void interrupted() {
        end();
    }
}