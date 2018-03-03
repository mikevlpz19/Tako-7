package org.usfirst.frc.team4400.robot.subsystems;
import org.usfirst.frc.team4400.robot.MapaDelRobot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Elevador extends Subsystem {
	//Poner metodos para controlar este subsistema.Llamar estos metodos desde los comandos
	WPI_TalonSRX motor1, motor2;
	SpeedControllerGroup motor;
	DigitalInput SwitchAbajo, SwitchCima;
		public Elevador() {
			super();
			motor1 = new WPI_TalonSRX(MapaDelRobot.elevadorMotor1);
			motor1.setInverted(true);
			motor2 = new WPI_TalonSRX(MapaDelRobot.elevadorMotor2);
			motor2.setInverted(true);
			SwitchAbajo = new DigitalInput(MapaDelRobot.elevadorInterruptorBajo);
			SwitchCima = new DigitalInput(MapaDelRobot.elevadorInterruptorCima);

			motor1.setNeutralMode(NeutralMode.Brake);
			motor2.setNeutralMode(NeutralMode.Brake);
			
			motor = new SpeedControllerGroup(motor1, motor2);
		}
    public void initDefaultCommand() {
    	//Declarar aqui el comando que estara por defecto para el subsistema.
        //setDefaultCommand(new ComandoPorDefecto());
    }
    /**
     * 
     * @param valor Positivo para el elevador para subir (-1 , 1)
     */
	public void PorDefecto() {
		motor.set(0);
	}
    public void set(double value) {
    	motor1.setNeutralMode(NeutralMode.Brake); // Just in case
    	motor2.setNeutralMode(NeutralMode.Brake); // Just in case
    	 if (value < 0 && !SwitchAbajo.get()) {
        	motor.set(value);
    	}
    	else if (value > 0 && !SwitchCima.get()) {
    		motor.set(value);
    	}
    	else {
    		motor.set(0);
    	}
    	motor.set(value);
    }
}