package org.usfirst.frc.team2642.robot.subsystems;

import org.usfirst.frc.team2642.robot.Robot;
import org.usfirst.frc.team2642.robot.RobotMap;
import org.usfirst.frc.team2642.robot.commands.RotateTurret;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;


public class VisionTurret extends PIDSubsystem {

	//must purchace servo, or change to motor
    //must have potentiometer, will send explaination
	Talon turretServo = new Talon(RobotMap.turretServo);
	static AnalogPotentiometer turretPot = new AnalogPotentiometer(RobotMap.turretPot);
	
    // Initialize your subsystem here
    public VisionTurret() {
    	super("VisionTurret", 0.02, 0, 0.06);
    	setSetpoint(0);
    	disable();
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new RotateTurret());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
//centers robot on x axis with turret, see robot for imglock
    public double returnPIDInput() {
    	synchronized (Robot.imgLock){
    		return Robot.centerX;
    	}
    	
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    }
    //instantiates output as variable PID
    protected void usePIDOutput(double output) {
    	moveTurret(output);
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
    
    public void enablePID(){
    	enable();
    }
    
    public void disablePID(){
    	disable();
    }
    
    public static double getPot(){
    	return turretPot.get();
    }
    //might be depricated
    public void moveTurret(double speed){
    	System.out.print("Output  " + speed);
    	System.out.println("     Pot  " + turretPot.get());
    	
    	if((speed < 0) && (turretPot.get() < RobotMap.turretUpper)){
    		turretServo.set(speed);
    	}else if((speed > 0) && (turretPot.get() > RobotMap.turretLower)){
    		turretServo.set(speed);
    	}else{
    		turretServo.set(0.0);
    	}
    }
    
    
}
