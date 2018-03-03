package org.usfirst.frc.team4400.robot.autonomous.commands;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class ElevadorAutonomo extends TimedCommand {

	double ValorDeMovimiento, inicio;
	
	public ElevadorAutonomo(double tiempoFuera, double poder) {
	    //Usar requires() aqui para declarar las dependencias de un subsistema
		super(tiempoFuera);
        requires(Robot.elevador);
        ValorDeMovimiento = poder;
    }
    //Llama justo antes de que el comando se ejecute por primera vez
    protected void initialize() {
    	inicio = Timer.getFPGATimestamp();
    }
    //Llama repetidamente cuando el comando es programado para que corra
    protected void execute() {
    	if (Timer.getFPGATimestamp() - inicio > 0.5)
    		Robot.elevador.set(ValorDeMovimiento);
    }
    //Hace que se devuelva true cuando este comando no necesita ya correr el execute()
    protected void end() {
    	Robot.elevador.set(0);
    }
    //Llamado cuando otro comando que requiere uno o mas dependencias de un subsistema esta programado para que corra
    protected void interrupted() {
    }
}
