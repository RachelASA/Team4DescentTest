package org.usfirst.frc4.Team4Descent.subsystems;

import org.usfirst.frc4.Team4Descent.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {
    public Solenoid
            chassisSolenoid,
            shootSolenoid;
    
    Compressor compressor;

    public void initDefaultCommand(){
        chassisSolenoid = RobotMap.pneumaticsChassisSolenoid;
        shootSolenoid   = RobotMap.pneumaticsShootSolenoid;
        compressor      = RobotMap.pneumaticsCompressor;
    } 
    
    public void shiftHigh(Solenoid sol) {sol.set(true);}
    public void shiftLow(Solenoid sol)  {sol.set(false);}
    
    public void shift(Solenoid sol)     {sol.set(!sol.get());} //needs testing
    
    public void compressorStart() {compressor.start();}
    public void compressorStop()  {compressor.stop();}
}
