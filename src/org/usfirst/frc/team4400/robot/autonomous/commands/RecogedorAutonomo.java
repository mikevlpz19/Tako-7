package org.usfirst.frc.team4400.robot.autonomous.commands;
import org.usfirst.frc.team4400.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.TimedCommand;
/**
 *
 */
public class RecogedorAutonomo extends TimedCommand {
	double ValorDeMovimiento, inicio;
    public RecogedorAutonomo(double tiempoFuera, double poder) {
        super(tiempoFuera);
        //Usar requires() aqui para declarar las dependencias de un subsistema
        requires(Robot.recogedor);
        ValorDeMovimiento = poder;
    }
    //Llama justo antes de que el comando se ejecute por primera vez    
    protected void initialize() {
    	inicio = Timer.getFPGATimestamp();
    }
    //Llama repetidamente cuando el comando es programado para que corra
    protected void execute() {
    	if (Timer.getFPGATimestamp() - inicio > 0.5)
    		Robot.recogedor.set(ValorDeMovimiento, -ValorDeMovimiento);
    }
    // Llamado una vez despues del tiempoFuera
    protected void end() {
    	Robot.recogedor.set(0, 0);
    }
    //Llamado cuando otro comando que requiere uno o mas dependencias de un subsistema esta programado para que corra
    protected void interrupted() {
    }
}
