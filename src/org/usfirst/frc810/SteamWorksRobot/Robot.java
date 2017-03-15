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

import java.util.function.Supplier;

import org.usfirst.frc810.SteamWorksRobot.commands.AutoGearStartFromCenter;
import org.usfirst.frc810.SteamWorksRobot.commands.AutoGearStartFromLeft;
import org.usfirst.frc810.SteamWorksRobot.commands.AutoGearStartFromRight;
import org.usfirst.frc810.SteamWorksRobot.commands.AutonomousCommand;
import org.usfirst.frc810.SteamWorksRobot.commands.DefaultGear;
import org.usfirst.frc810.SteamWorksRobot.commands.MakeIntakeFront;
import org.usfirst.frc810.SteamWorksRobot.commands.SetCameraPosition;
import org.usfirst.frc810.SteamWorksRobot.subsystems.Ballintake;
import org.usfirst.frc810.SteamWorksRobot.subsystems.CameraMount;
import org.usfirst.frc810.SteamWorksRobot.subsystems.Climber;
import org.usfirst.frc810.SteamWorksRobot.subsystems.DriveTrain;
import org.usfirst.frc810.SteamWorksRobot.subsystems.GearMechanism;
import org.usfirst.frc810.SteamWorksRobot.subsystems.General;
import org.usfirst.frc810.SteamWorksRobot.subsystems.Hopper;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;
import vision.GripPipeline;
import vision.VisionListener;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Ballintake ballintake;
    public static Hopper hopper;
    public static GearMechanism gearMechanism;
    public static CameraMount cameraMount;
    public static General general;
    public static Climber climber;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static UsbCamera c;
    public static UsbCamera c2;
    public static AxisCamera ac;
    public static double startAngle;
    public static final double gearStep1Dist = .165;
    public static final double gearStep2Dist = .14;
    
    public static SendableChooser<Supplier<Command>> AutoCommandSupplier; 

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	System.out.println("Initializing");
    RobotMap.init();
    startAngle = RobotMap.getGyroAngle();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        ballintake = new Ballintake();
        hopper = new Hopper();
        gearMechanism = new GearMechanism();
        cameraMount = new CameraMount();
        general = new General();
        climber = new Climber();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        
        AutoCommandSupplier = new SendableChooser<>();
        AutoCommandSupplier.addDefault("Do Nothing", ()->new WaitCommand(.01));
        AutoCommandSupplier.addObject("Gear Starting From Left", AutoGearStartFromLeft::new);
        AutoCommandSupplier.addObject("Gear Starting From Center", AutoGearStartFromCenter::new);
        AutoCommandSupplier.addObject("Gear Starting From Right", AutoGearStartFromRight::new);
        SmartDashboard.putData("Autonomous Selector", AutoCommandSupplier);
        
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new AutonomousCommand();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        driveTrain.resetG();
        
        c = CameraServer.getInstance().startAutomaticCapture();
        c2 = CameraServer.getInstance().startAutomaticCapture();
        Timer.delay(.1);
        c.setExposureManual(20);
       
        SmartDashboard.putString("AutoFile", "");
        new VisionThread(c, new GripPipeline(), 
        		new VisionListener(CameraServer.getInstance().putVideo("Processed", 160, 120)
        		,CameraServer.getInstance().getVideo())).start();
        
        ac = CameraServer.getInstance().addAxisCamera("Axis","169.254.28.217");
        
      //  AutoPutData.addNumber("Slider", Robot.oi.getDriveStick()::getThrottle);
        SmartDashboard.putNumber("Exposure", SmartDashboard.getNumber("Exposure", 20));
      AutoPutData.addNumber("Pov", oi.driveStick::getPOV);
    }
        
   

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Navx angle", RobotMap.getGyroAngle());
       c.setExposureManual((int)SmartDashboard.getNumber("Exposure",20));
    }

    public void autonomousInit() {
    	Scheduler.getInstance().add(new DefaultGear());
        // schedule the autonomous command (example)
    	autonomousCommand = AutoCommandSupplier.getSelected().get();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	Scheduler.getInstance().add(new DefaultGear());
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Scheduler.getInstance().add(new SetCameraPosition(.5,.5));
        Scheduler.getInstance().add(new MakeIntakeFront());
        ballcounter = 0;
        RobotMap.navX.reset();
        
    }
    
    int ballcounter = 0;
    boolean ballstate = false;
    

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        double intakeCurrent = RobotMap.generalPDP.getCurrent(0);
        if(intakeCurrent>28 && !ballstate){
        	ballcounter++;
        	ballstate = true;
        }
        if(intakeCurrent<25){
        	ballstate = false;
        }
        
        SmartDashboard.putNumber("Total fuel", ballcounter);
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
