package org.usfirst.frc4.Team4Descent.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Justin
 */
public class Autonomous extends CommandGroup {
    double
            revTime = 4,
            intervalTime = 3;
    
    public Autonomous() {
        
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
        
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        
        addSequential(new DriveStraightAuton(-0.8, 0, 0.5));
        //addSequential(new DriveStraightAuton(-0, 90, 3));
        
    }
}
