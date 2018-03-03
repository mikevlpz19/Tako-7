package org.usfirst.frc.team4400.robot.commands;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class CajaDeVelocidades extends InstantCommand {
	
	/**
	 * Esto va a cambiar el estado de la caja de velocidades
	 */
    public CajaDeVelocidades() {
        super();
        // Usa requires() para declarar las dependencias de subsistema
        requires(Robot.conduccion);
    }
    
    // Llamado una vez que el comando se ejecuta
    protected void initialize() {
    	Robot.conduccion.cambioDeMarcha(!Robot.conduccion.MarchaCambiando());
    }

}
