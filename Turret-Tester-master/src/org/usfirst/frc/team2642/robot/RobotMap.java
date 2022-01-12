package org.usfirst.frc.team2642.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Drive motors (PWM)
	public static int frontLeft = 2,
			  rearLeft = 0,
			  frontRight = 1,
			  rearRight = 3;
	
	//Turret Stuff
	public static int turretPot = 0; //Analog
	public static int turretServo = 9; //PWM
	public static double turretUpper = 0.8;
	public static double turretLower = 0.2;
	
}
	
	
	
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

