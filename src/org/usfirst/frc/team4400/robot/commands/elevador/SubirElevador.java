package org.usfirst.frc.team4400.robot.commands.elevador;
import org.usfirst.frc.team4400.robot.Robot;
import org.usfirst.frc.team4400.robot.subsystems.Elevador;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class SubirElevador extends Command {
	Elevador elevador;
    public SubirElevador() {
    	//Usar requires() aqui para declarar las dependencias de un subsistema
        requires(Robot.elevador);
    }
    //Llama justo antes de que el comando se ejecute por primera vez
    protected void initialize() {
    	Robot.elevador.set(SmartDashboard.getNumber("Potencia Elevador", 0.75));
    }
    //Llama repetidamente cuando el comando es programado para que corra
    protected void execute() {

    }
    //Hace que se devuelva true cuando este comando no necesita ya correr el execute()
    protected boolean isFinished() {
        return false;
    }
    //llamado despues de que el isFinished devuelve true 
    protected void end() {
    }
    //Llamado cuando otro comando que requiere uno o mas dependencias de un subsistema esta programado para que corra
    protected void interrupted() {
    }
}