package org.usfirst.frc.team4400.robot.commands.elevador;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ApagarElevador extends InstantCommand {

    public ApagarElevador() {
        super();
        // Usa requires() para declarar las dependencias de subsistema
        requires(Robot.elevador);
    }

    // LLamado una vez que el comando se ejecuta
    protected void initialize() {
    	Robot.elevador.set(0);
    }

}
