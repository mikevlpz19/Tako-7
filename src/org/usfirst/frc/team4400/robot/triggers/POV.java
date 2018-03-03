package org.usfirst.frc.team4400.robot.triggers;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;
/**
 */
public class POV extends Trigger {

	public enum Direccion {
		Arriba,Abajo,Izquierda,Derecha,IzquierdaArriba,DerechaArriba,IzquierdaAbajo,DerechaAbajo
	}
	Direccion direccion;
	Joystick stick;
	public POV(Joystick stick, Direccion direccion) {
		this.stick = stick;
		this.direccion = direccion;
	}
    public boolean get(){
        switch(direccion) {
	        case Arriba : return stick.getPOV() == 0;
	        case DerechaArriba : return stick.getPOV() == 45;
	        case Derecha : return stick.getPOV() == 90;
	        case DerechaAbajo : return stick.getPOV() == 135;
	        case Abajo : return stick.getPOV() == 180;
	        case IzquierdaAbajo : return stick.getPOV() == 225;
	        case Izquierda : return stick.getPOV() == 270;
	        case IzquierdaArriba : return stick.getPOV() == 315;
			default: return false;
        }
    }
}
