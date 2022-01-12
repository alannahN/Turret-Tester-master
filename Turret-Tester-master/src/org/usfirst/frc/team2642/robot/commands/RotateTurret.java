package org.usfirst.frc.team2642.robot.commands;

import org.usfirst.frc.team2642.robot.OI;
import org.usfirst.frc.team2642.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateTurret extends Command {

    public RotateTurret() {
    	requires(Robot.visionTurret);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    //move turret on xbox
    protected void execute() {
    	Robot.visionTurret.moveTurret(-OI.getxBox().getX(Hand.kRight));
    	System.out.println("X Value: " + Robot.visionTurret.returnPIDInput());
    	
    	if(OI.getxBox().getYButton()){
    		Robot.visionTurret.enablePID();
    	}else{
    		Robot.visionTurret.disablePID();
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
