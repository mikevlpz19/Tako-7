 package org.usfirst.frc.team4400.robot.commands.escalador;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BajarEscalador extends InstantCommand {

    public BajarEscalador() {
        super();
        //Usar requires() aqui para declarar las dependencias de un subsistema
        requires(Robot.escalador);
    }
    //Llamado una vez que el comando se ejecuta
    protected void initialize() {
    	Robot.escalador.set(-SmartDashboard.getNumber("Potencia Escalador", 0.75) * 0.65);
    }
}