package org.usfirst.frc.team4400.robot.commands.recogedor;
import org.usfirst.frc.team4400.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class PonerCaja extends Command {
    public PonerCaja() {
    //Usar requires() aqui para declarar las dependencias de un subsistema
        requires(Robot.recogedor);
    }
    //Llama justo antes de que el comando se ejecute por primera vez
    protected void initialize() {
    	Robot.recogedor.set(SmartDashboard.getNumber("Potencia Recogedor", 0.9));
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