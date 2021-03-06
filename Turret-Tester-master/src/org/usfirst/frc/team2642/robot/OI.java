package org.usfirst.frc.team2642.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team2642.robot.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private static XboxController xBox = new XboxController(0);
	public static XboxController getxBox(){
		return xBox;
	}
	
	Button xBoxA = new JoystickButton(xBox, 1);
	//sets camera vision to look or to drive
	public OI() {
		xBoxA.whenPressed(new SetCameraVision());
		xBoxA.whenReleased(new SetCameraDrive());
	}
}