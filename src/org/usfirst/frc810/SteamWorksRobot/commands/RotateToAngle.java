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

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RotateToAngle extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private double m_angle;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    private PIDController pid;
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public RotateToAngle(double angle) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_angle = angle;

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pid = new PIDController(0,0,0, RobotMap.navX, a -> {});
    	
    	pid.setContinuous(true);
    	pid.setInputRange(-180, 180);
    	pid.setOutputRange(-.5, .5);
    	pid.setAbsoluteTolerance(3);
    	pid.setSetpoint(m_angle-Robot.startAngle);
    	pid.setToleranceBuffer(10);
    
    	
    	pid.setPID(SmartDashboard.getNumber("PID P",0), SmartDashboard.getNumber("PID I",0), SmartDashboard.getNumber("PID D",0));
    	pid.enable();
    }

    int counter = 0;
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.driveTrain.mecanum(0, 0, pid.get());
    		if (pid.onTarget())
    			counter++;
    		else
    			counter = 0;
    		SmartDashboard.putNumber("PID Output", pid.get());
    		SmartDashboard.putNumber("PID Error", pid.getError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return counter >= 10;   
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    	pid.free();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
