package org.usfirst.frc.team4400.robot.commands;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class InvertirEjeY extends InstantCommand {

    public InvertirEjeY() {
        super();
        //Usa requires() aqui para declarar las dependencias de subsistema
        requires(Robot.conduccion);
    }

    // LLmadao una vez que el comando se ejecuta
    protected void initialize() {
    	Robot.conduccion.marcarEjesInvertidos(!Robot.conduccion.seInvertioEjeY());
    }

}