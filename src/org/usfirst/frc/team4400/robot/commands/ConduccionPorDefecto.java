package org.usfirst.frc.team4400.robot.commands;

import org.usfirst.frc.team4400.robot.Robot;
import org.usfirst.frc.team4400.robot.subsystems.Conduccion;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ConduccionPorDefecto extends Command {

	Conduccion conduccion;
	Joystick Joy1;
    public ConduccionPorDefecto() {
        //Usa requires() aqui para declarar las dependencias de subsistema
        requires(Robot.conduccion);
        conduccion = Robot.conduccion;
        Joy1 = Robot.oi.getJoystick(0);
    }
    //Llama justo antes de que el comando se ejecute por primera vez
    protected void initialize() {
    }
    //Llama repetidamente cuando el comando es programado para que corra
    protected void execute() {
    conduccion.establecerLimiteVelocidad(SmartDashboard.getNumber("Potencia Motores", 0.5) 
    			- Joy1.getRawAxis(2) * 0.25 + Joy1.getRawAxis(3) * 0.25);
    	if(conduccion.modoStick())
        	conduccion.tweenedDrive(Joy1.getRawAxis(0) + Joy1.getRawAxis(4),
        			Joy1.getRawAxis(1) + Joy1.getRawAxis(5),
        			SmartDashboard.getNumber("Tolerancia Joysticks", 0.1));
    	else
    		conduccion.tweenedDrive(Joy1.getRawAxis(4),
        			Joy1.getRawAxis(1),
        			SmartDashboard.getNumber("Tolerancia Joysticks", 0.1));
    	//drive.printPixyBlocks();  
    	SmartDashboard.putNumber("POVValue", Joy1.getPOV());
    }
    //Hace que se devuelva true cuando este comando no necesita ya correr el execute()
    protected boolean isFinished() {
        return false;
    }
    //llamado despues de que el isFinished devuelve true 
    protected void end() {
    }
    //Llamado cuando otro comando que requiere uno o mas dependencias de un subsistema esta programado para que corra
    protected void interrupted() {
    }
}
