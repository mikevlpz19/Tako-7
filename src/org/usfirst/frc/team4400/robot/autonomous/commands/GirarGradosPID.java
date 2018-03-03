package org.usfirst.frc.team4400.robot.autonomous.commands;

import org.usfirst.frc.team4400.robot.Robot;

//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.PIDSource;
//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class GirarGradosPID extends Command implements PIDOutput{
	PIDController ControladorDeVuelta;
	double PuntoFijo, GirarAVelocidadDeAngulo;
	static final double kP = 0.03;
    static final double kI = 0.00;
    static final double kD = 0.00;
    static final double kF = 0.00;
    static final double GradosDeTolerancia = 2.0f;
    public GirarGradosPID(int i) {
    	//Usar requires() aqui para declarar las dependencias de un subsistema
    	requires(Robot.conduccion);
    	this.PuntoFijo = i;
    	ControladorDeVuelta = new PIDController(kP, kI, kD, kF, Robot.conduccion.establecerNavx(), this);
    	ControladorDeVuelta.setInputRange(-180.0f,  180.0f);
    	ControladorDeVuelta.setOutputRange(-1.0, 1.0);
    	ControladorDeVuelta.setAbsoluteTolerance(GradosDeTolerancia);
    	ControladorDeVuelta.setContinuous(true);
    	ControladorDeVuelta.setSetpoint(PuntoFijo);
    }
    //Llama justo antes de que el comando se ejecute por primera vez
    protected void initialize() {
    	ControladorDeVuelta.reset();
    	ControladorDeVuelta.enable();
    }
    //Llama repetidamente cuando el comando es programado para que corra
    protected void execute() {
    	Robot.conduccion.arcadeDrive(0,GirarAVelocidadDeAngulo);	
    }
    //Hace que se devuelva true cuando este comando no necesita ya correr el execute()
    protected boolean isFinished() {
        return ControladorDeVuelta.onTarget();
    }
    //llamado despues de que el isFinished devuelve true 
    protected void end() {
    	ControladorDeVuelta.disable();
    	Robot.conduccion.arcadeDrive(0, 0);
    }
    //Llamado cuando otro comando que requiere uno o mas dependencias de un subsistema esta programado para que corra
    protected void interrupted() {
    }
	@Override
	public void pidWrite(double output) {
		GirarAVelocidadDeAngulo = output;	
	}
}
