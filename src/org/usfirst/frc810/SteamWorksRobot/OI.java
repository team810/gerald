// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc810.SteamWorksRobot;

import org.usfirst.frc810.SteamWorksRobot.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc810.SteamWorksRobot.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

	public Button POV;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton halfSpeed;
    public JoystickButton lineUpButton;
    public JoystickButton gearFront;
    public JoystickButton intakeFront;
    public JoystickButton climberFront;
    public Joystick driveStick;
    public JoystickButton suck;
    public JoystickButton makeTheGearAProjectile;
    public JoystickButton releaseTheHopper;
    public JoystickButton runWinch;
    public JoystickButton bringItBackNowYall;
    public Joystick mechanismStick;
	private JoystickButton quarterSpeed;
	private JoystickButton lineUpButton2;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        mechanismStick = new Joystick(1);
        
        bringItBackNowYall = new JoystickButton(mechanismStick, 7);
        bringItBackNowYall.whenReleased(new DefaultGear());
        runWinch = new JoystickButton(mechanismStick, 1);
        runWinch.whileHeld(new ControlClimber());
        releaseTheHopper = new JoystickButton(mechanismStick, 2);
        releaseTheHopper.whileHeld(new DumpHopper());
        makeTheGearAProjectile = new JoystickButton(mechanismStick, 7);
        makeTheGearAProjectile.whenPressed(new ReleaseGear());
        suck = new JoystickButton(mechanismStick, 8);
        suck.whileHeld(new SuckItUp());
        driveStick = new Joystick(0);
        
     /*   climberFront = new JoystickButton(driveStick, 5);
        climberFront.whenPressed(new MakeClimberFront());
        intakeFront = new JoystickButton(driveStick, 4);
        intakeFront.whenPressed(new MakeIntakeFront());
        gearFront = new JoystickButton(driveStick, 6);
        gearFront.whenPressed(new MakeGearFront());*/
        lineUpButton = new JoystickButton(driveStick, 5);
        lineUpButton.whileHeld(new LineUpGroup());
        lineUpButton2 = new JoystickButton(driveStick, 6);
        lineUpButton2.whileHeld(new LineUpGroup());
        halfSpeed = new JoystickButton(driveStick, 3);
        halfSpeed.whileHeld(new Drive(.4));
        quarterSpeed = new JoystickButton(driveStick, 4);
        quarterSpeed.whileHeld(new Drive(.2));



        // SmartDashboard Buttons
        SmartDashboard.putData("SuckItUp", new SuckItUp());
        SmartDashboard.putData("ReleaseGear", new ReleaseGear());
        SmartDashboard.putData("DumpHopper", new DumpHopper());
        SmartDashboard.putData("DefaultGear", new DefaultGear());
        SmartDashboard.putData("FireGear", new FireGear());
        SmartDashboard.putData("ControlClimber", new ControlClimber());
        SmartDashboard.putData("RotateToAngle: angle", new RotateToAngle(0));
        SmartDashboard.putData("MakeIntakeFront", new MakeIntakeFront());
        SmartDashboard.putData("MakeGearFront", new MakeGearFront());
        SmartDashboard.putData("MakeClimberFront", new MakeClimberFront());
        SmartDashboard.putData("LineUpGroup", new LineUpGroup());
        SmartDashboard.putData("MakeGearAdjustment", new MakeGearAdjustment());
        SmartDashboard.putData("GearAdjustmentLineUp", new GearAdjustmentLineUp());
        SmartDashboard.putData("UnwindCommand", new UnwindCommand());
        SmartDashboard.putData("AutonomousCommand", new AutonomousCommand());
        SmartDashboard.putData("AutoGearStartFromLeft", new AutoGearStartFromLeft());
        SmartDashboard.putData("AutoGearSequence", new AutoGearSequence());
        SmartDashboard.putData("AutoGearStartFromCenter", new AutoGearStartFromCenter());
        SmartDashboard.putData("AutoGearStartFromRight", new AutoGearStartFromRight());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
       POV = new POVButton(driveStick);
       POV.whileHeld(new ThumbMoveCommand());
       AutoPutData.addBoolean("POV", POV::get);
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriveStick() {
        return driveStick;
    }

    public Joystick getMechanismStick() {
        return mechanismStick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public class POVButton extends Button{
    	GenericHID HID;
    	public POVButton(GenericHID h){
    		HID = h;
    	}
		@Override
		public boolean get() {
			// TODO Auto-generated method stub
		return HID.getPOV() != -1;
		}
    	
    	
    }
}