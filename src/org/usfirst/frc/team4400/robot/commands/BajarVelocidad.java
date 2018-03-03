package org.usfirst.frc.team4400.robot.commands;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BajarVelocidad extends InstantCommand{ 

    public BajarVelocidad() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.conduccion);
    }

    // Called once when the command executes
    protected void initialize() {
    	SmartDashboard.putNumber("Potencia Motores", 
    			SmartDashboard.getNumber("Potencia Motores", 0.75) - 0.25);
    }

}
