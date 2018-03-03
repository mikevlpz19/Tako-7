package org.usfirst.frc.team4400.robot.commands.elevador;
import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevadorPorDefecto extends Command {
    public ElevadorPorDefecto() {
        //Usar requires() aqui para declarar las dependencias de un subsistema
        requires(Robot.elevador);
    }
    //Llama justo antes de que el comando se ejecute por primera vez
    protected void initialize() {
    }
    //Llama repetidamente cuando el comando es programado para que corra
    protected void execute() {
    	Robot.elevador.set(Robot.oi.getJoystick(0).getRawButton(4) ?
    			SmartDashboard.getNumber("Potencia Elevador", 0.8) : Robot.oi.getJoystick(0).getRawButton(1) ?
    					-SmartDashboard.getNumber("Potencia Elevador", 0.8) * 0.65 : 0);
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