package org.usfirst.frc.team4400.robot.autonomous.commands;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class VueltaConTiempo extends TimedCommand {

	double ValorDeVuelta;
	
    public VueltaConTiempo(double TiempoFuera, double poder) {
        super(TiempoFuera);
    	//Usar requires() aqui para declarar las dependencias de un subsistema
        requires(Robot.conduccion);
        ValorDeVuelta = poder;
    }
    //Llama justo antes de que el comando se ejecute por primera vez
    protected void initialize() {
    	Robot.conduccion.reiniciarEncoder();
    }
    //Llama repetidamente cuando el comando es programado para que corra
    protected void execute() {
    	Robot.conduccion.tweenedDrive(ValorDeVuelta, 0);
    }
    //Hace que se devuelva true cuando este comando no necesita ya correr el execute()
    //Llamado despues del TiempoFuera
    protected void end() {
    	Robot.conduccion.arcadeDrive(0, 0);
    }
    //Llamado cuando otro comando que requiere uno o mas dependencias de un subsistema esta programado para que corra
    protected void interrupted() {
    }
}