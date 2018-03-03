package org.usfirst.frc.team4400.robot.subsystems;

import org.usfirst.frc.team4400.robot.MapaDelRobot;
import org.usfirst.frc.team4400.robot.commands.recogedor.RecogedorPorDefecto;

import edu.wpi.first.wpilibj.VictorSP;
//import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Recogedor extends Subsystem {
	//Poner metodos para controlar este subsistema.Llamar estos metodos desde los comandos	
	private VictorSP MotorIzquierda, MotorDerecha;
//DoubleSolenoid SolenoideRecogedor; 
	boolean armShift=false;
	public Recogedor(){
		super();
		MotorIzquierda = new VictorSP(MapaDelRobot.recogedorMotorIzquierdo);
		MotorDerecha = new VictorSP(MapaDelRobot.recogedorMotorDerecho);
		this.AbrirCerrarBrazo(armShift);
	}
    public void initDefaultCommand() {
        //Establecer el comando por defecto para el subsistema aqui.
        //setDefaultCommand(new ComandoPorDefecto());
    	setDefaultCommand(new RecogedorPorDefecto());
    }
	
    /**
     * 
     * @param valueL Negativo para in 
     * @param valueR Positivo para in
     */
	public void set(double valueL, double valueR){
		MotorIzquierda.set(valueL);
		MotorDerecha.set(valueR);
	}
	
    /**
     * 
     * @param value Negativo para in
     */
	public void set(double value){
		set(value, -value);
	}
	
	public void AbrirCerrarBrazo(boolean value) {
    	//arm.set(value ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    	armShift = value;
	}
	
	public boolean isArmShifted() {
		return armShift;
	}
}