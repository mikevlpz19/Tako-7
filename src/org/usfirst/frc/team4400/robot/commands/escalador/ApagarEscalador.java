package org.usfirst.frc.team4400.robot.commands.escalador;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ApagarEscalador extends InstantCommand {

    public ApagarEscalador() {
        super();
        //Usar requires() aqui para declarar las dependencias de un subsistema
        requires(Robot.escalador);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.escalador.set(0);
    }

}
