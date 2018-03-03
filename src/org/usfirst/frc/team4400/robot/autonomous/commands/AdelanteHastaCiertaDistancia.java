package org.usfirst.frc.team4400.robot.autonomous.commands;
import org.usfirst.frc.team4400.robot.Robot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class AdelanteHastaCiertaDistancia extends Command implements PIDOutput {
	double puntoFijo, inicio, anguloOriginal, poder = 0;
	PIDController pidc;
    public AdelanteHastaCiertaDistancia(double puntoFijo) {
    	//Usar requires() aqui para declarar las dependencias de un subsistema
    	requires(Robot.conduccion);
    	this.puntoFijo = puntoFijo;
    	pidc = new PIDController(0.008, 0.000002, 0.009, Robot.conduccion.obtenerCIMcoder(), this);
    	pidc.setAbsoluteTolerance(5);
    	pidc.setInputRange(-500, 500);
    	pidc.setOutputRange(-1, 1);
    	pidc.setContinuous(false);
    }

    //Llama justo antes de que el comando se ejecute por primera vez
    protected void initialize() {
    	Robot.conduccion.reiniciarEncoder();
    	Robot.conduccion.marcarEjesInvertidos(false);
    	anguloOriginal = Robot.conduccion.obtenerAnguloNavx();
    	pidc.setSetpoint(puntoFijo);
    	inicio = Timer.getFPGATimestamp();
    }

    //Llama repetidamente cuando el comando es programado para que corra

    protected void execute() {
    	if (Timer.getFPGATimestamp() - inicio > 0.5 && !pidc.isEnabled())
    		pidc.enable();
    	Robot.conduccion.tweenedDrive((anguloOriginal - Robot.conduccion.obtenerAnguloNavx()) * 0.03, poder);
    }

    //Hace que se devuelva true cuando este comando no necesita ya correr el execute()
    protected boolean isFinished() {
        return pidc.onTarget();
    }
    //llamado despues de que el isFinished devuelve true 
    protected void end() {
    	pidc.disable();
    }
    //Llamado cuando otro comando que requiere uno o mas dependencias de un subsistema esta programado para que corra
    protected void interrupted() {
    }

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		poder = output;
	}
}

