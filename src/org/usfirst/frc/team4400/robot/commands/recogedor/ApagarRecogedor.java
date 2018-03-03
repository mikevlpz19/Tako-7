package org.usfirst.frc.team4400.robot.commands.recogedor;

import org.usfirst.frc.team4400.robot.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ApagarRecogedor extends InstantCommand {

	    public ApagarRecogedor() {
	        super();
	        //Usa requires() aqui para declarar las dependencias de subsistema 
	        requires(Robot.recogedor);
	    }

	//Llamado una vez que el comando se ejecuta
	    protected void initialize() {
	    	Robot.recogedor.set(0);
	    }


	}
