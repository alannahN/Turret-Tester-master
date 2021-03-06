package org.usfirst.frc.team2642.robot.commands;

import org.usfirst.frc.team2642.robot.Robot;
import org.usfirst.frc.team2642.robot.OI;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.GenericHID.Hand;


/**
 *depricated
 */
public class ArcadeDrive extends Command {
    public ArcadeDrive() {
    	requires(Robot.driveTrain);
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.getxBox().getRawAxis(3) > 0.6){
        	Robot.driveTrain.drive(OI.getxBox().getY(Hand.kLeft) * 1.1, OI.getxBox().getX(Hand.kLeft) * 1.1);
    	}else{
    		Robot.driveTrain.drive(OI.getxBox().getY(Hand.kLeft) * 0.6, OI.getxBox().getX(Hand.kLeft) * 0.6);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
