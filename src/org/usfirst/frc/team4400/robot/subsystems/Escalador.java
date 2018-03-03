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
public class Escalador extends Subsystem {
	//Poner metodos para controlar este subsistema.Llamar estos metodos desde los comandos
	WPI_TalonSRX motor1, motor2;
	SpeedControllerGroup motor;
	DigitalInput InterruptorBajo, InterruptorCima;
	public Escalador() {
		super();
		motor1 = new WPI_TalonSRX(MapaDelRobot.escaladorMotor1);
		motor1.setInverted(false);
		motor2 = new WPI_TalonSRX(MapaDelRobot.escaladorMotor2);
		motor2.setInverted(false);
		InterruptorBajo = new DigitalInput(MapaDelRobot.escaladorInterruptorMedio);
		InterruptorCima = new DigitalInput(MapaDelRobot.escaladorInterruptorCima);

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
     * @param value Positive for the elevator to go up (-1 , 1)
     */
    public void set(double value) {
    	motor1.setNeutralMode(NeutralMode.Brake); // Solo en caso
    	motor2.setNeutralMode(NeutralMode.Brake); // Solo en caso 	
    	if (value < 0 && !InterruptorBajo.get()) {
        	motor.set(value);
    	}
    	else if (value > 0 && !InterruptorCima.get()) {
    		motor.set(value);
    	}
    	else {
    		motor.set(0);
    	}
    }
}

