package org.usfirst.frc.team4400.robot.commands.recogedor;

import org.usfirst.frc.team4400.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RecogedorDerecha extends InstantCommand {

    public RecogedorDerecha() {
        super();
        // Use requieres() aqui para declarar las dependencias de subsistema requires()
    }

    // Llamado una vez que el comando se ejecuta
    protected void initialize() {
    	Robot.recogedor.set(-SmartDashboard.getNumber("Potencia Recogedor", 0.9),
    			-SmartDashboard.getNumber("Potencia Recogedor", 0.9));
    }

}