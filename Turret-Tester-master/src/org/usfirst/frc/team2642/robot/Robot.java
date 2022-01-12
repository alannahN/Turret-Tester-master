
package org.usfirst.frc.team2642.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2642.robot.commands.*;
import org.usfirst.frc.team2642.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final VisionTurret visionTurret = new VisionTurret();
	public static final DriveTrain driveTrain = new DriveTrain();

	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	
	//Camera Resolution
		public static UsbCamera camera;
		private static final int IMG_WIDTH = 320;
		private static final int IMG_HEIGHT = 240;
		//VisionThread for delegating vision to its own task
		private VisionThread visionthread;
		//Name of the thread
		public static final Object imgLock = new Object();
		//Center of the rectangle
		public static double centerX = 0.0;
		public static double centerY = 0.0;
		public static double targetArea = 0.0;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		//Creates a new USB Camera
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);	//Sets the camera resolution
		camera.setFPS(15);
		setCameraDrive();
		
		
		//Creates a new vision thread and a new pipeline
		//Creates a rectangle based on the contours
		//Finds the center of the rectangle
		visionthread = new VisionThread(camera, new PipelineBoiler(), pipeline -> {
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock) {
	                centerX = 2*r.x + r.width - (IMG_WIDTH/2);
	                centerY = 2*r.y + r.height - (IMG_HEIGHT/2);
	                targetArea = r.area();
	            }
	        }
	    });
		visionthread.start();
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Pot Value", VisionTurret.getPot());
		
		SmartDashboard.putNumber("Center X", centerX);
		SmartDashboard.putNumber("Center Y", centerY);
		SmartDashboard.putNumber("Area", targetArea);
		
		Scheduler.getInstance().run();
	
	}
	
	public static void setCameraVision() {
		camera.setExposureManual(0);
		camera.setBrightness(0);
	}
	
	public static void setCameraDrive() {
		camera.setExposureManual(50);
		camera.setBrightness(50);
	}
	


	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
