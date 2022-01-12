package org.usfirst.frc.team2642.robot.subsystems;

import org.usfirst.frc.team2642.robot.RobotMap;
import org.usfirst.frc.team2642.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *regular drive code, depricated marked, abnormal marked
 */
public class DriveTrain extends Subsystem {
	//depricated, rewrite
	RobotDrive robotdrive = new RobotDrive(RobotMap.frontLeft,
										   RobotMap.rearLeft,
										   RobotMap.frontRight,
										   RobotMap.rearRight);		
		
	
	

	public void drive(double y, double x){
		robotdrive.arcadeDrive(-y, -x);
		
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new ArcadeDrive());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

}

