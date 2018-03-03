package org.usfirst.frc.team4400.robot.autonomous.commands;
import org.usfirst.frc.team4400.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.TimedCommand;
/**
 *
 */
public class ConducirDerecho extends TimedCommand {
	double valorDeMovimiento, inicio, anguloOriginal;
    public ConducirDerecho(double tiempoFuera, double poder) {
        super(tiempoFuera);
    	//Usar requires() aqui para declarar las dependencias de un subsistema
        requires(Robot.conduccion);
        valorDeMovimiento = poder;
    }
    //Llama justo antes de que el comando se ejecute por primera vez
    protected void initialize() {
    	Robot.conduccion.reiniciarEncoder();
    	Robot.conduccion.marcarEjesInvertidos(false); // falso para adelante cuando los valores son positivos
    	anguloOriginal = Robot.conduccion.obtenerAnguloNavx();
    	inicio = Timer.getFPGATimestamp();
    }
    //Llama repetidamente cuando el comando es programado para que corra
    protected void execute() {
    	if (Timer.getFPGATimestamp() - inicio > 0.5)
    		Robot.conduccion.tweenedDrive((anguloOriginal - Robot.conduccion.obtenerAnguloNavx()) * 0.03, valorDeMovimiento);
    }
    // Llamado despues del TiempoFuera
    protected void end() {
    	Robot.conduccion.arcadeDrive(0, 0);
    	Robot.conduccion.marcarEjesInvertidos(true);
    }
    //Llamado cuando otro comando que requiere uno o mas dependencias de un subsistema esta programado para que corra
    protected void interrupted() {
    }
}
