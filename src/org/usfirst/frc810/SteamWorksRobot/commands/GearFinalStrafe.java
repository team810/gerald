// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc810.SteamWorksRobot.commands;

import org.usfirst.frc810.SteamWorksRobot.Robot;
import org.usfirst.frc810.SteamWorksRobot.RobotMap;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import vision.VisionListener;

/**
 *
 */
public class GearFinalStrafe extends ConditionalCommand {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public GearFinalStrafe(double ultraVal) {
	super(new StrafeToValue(ultraVal), new GearAdjustmentLineUp());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR

    }

    protected boolean condition(){
    	System.out.println("GearFinalStrafe: "+(VisionListener.numResults!=1));
        return VisionListener.numResults!=1;
    }
    
   @Override protected boolean isFinished(){
    	return RobotMap.driveTrainUltra.getAverageVoltage()<Robot.gearStep2Dist;
    }
    
    @Override protected void end(){
    	super.end();
    	System.out.println("GearFinalSteafe:end");
    }
}

