package org.usfirst.frc.team4400.robot.commands;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ModoStick extends InstantCommand {

    public ModoStick() {
        super();
        //usa requires() aqui para declarar las dependencias de subsistema
        requires(Robot.conduccion);
    }
    //Llamado una vez que el comando se ejecuta
    protected void initialize() {
    	Robot.conduccion.establecerModoStick(!Robot.conduccion.modoStick());
    }

}