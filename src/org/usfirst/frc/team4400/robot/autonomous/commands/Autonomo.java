package org.usfirst.frc.team4400.robot.autonomous.commands;

import org.usfirst.frc.team4400.robot.Robot;
import org.usfirst.frc.team4400.robot.autonomous.commands.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
 
public class Autonomo extends CommandGroup {

    public Autonomo(String auto) {
    	/*Añadir comandos aqui:
    	 * addSequential(new comando1());
    	 * addSequential(new comando2());
    	 * estos comandos se ejecutaran en orden
    	 * Para ejecutar multiples comandos al mismo tiempo
    	 * use addParallel() en vez de addSequential y se ejecutaran al mismo tiempo
    	 * Un grupo de comandos va a requerir todos los subsistemas que cada miembro necesita.
    	 * por ejemplo, si comando1 requiere "chasis" y comando2 "elevador", el grupo de comandos 
    	 * va a necesitar chasis y elevador*/
    	String DatoDelJuego = DriverStation.getInstance().getGameSpecificMessage();
		Robot.conduccion.reiniciarNavx();
    	if(!auto.matches("NoAuto")) {
    		if(auto.matches("CenterAuto")) {
    			double leftOrRight = DatoDelJuego.charAt(1) == 'L' ? 1: -1.02;
    			if(DatoDelJuego.charAt(1) == 'L') {
        			addSequential(new ConducirDerecho(1.3, 0.6));
        			addSequential(new GirarGrados(-90 * leftOrRight));
        			addSequential(new ConducirDerecho(3.0, 0.6));
        			addSequential(new GirarGrados(90 * leftOrRight));
        			addSequential(new ConducirDerecho(5.6, 0.6));
        			addSequential(new ElevadorAutonomo(1.0, 0.8));
        			addSequential(new RecogedorAutonomo(0.6, 0.8));
        			addSequential(new ElevadorAutonomo(1.0, -0.8));
        			//addSequential(new TurnDegreesTakoDrive(90));
        			//addSequential(new TimedStraightTakoDrive(0.6, 0.6));
        			//addSequential(new TurnDegreesTakoDrive(90));
        			//addSequential(new TurnDegreesTakoDrive(-90));
    			}

    		}
    		
    			//double leftOrRight = auto.matches("LeftAuto") ? 1: -1.02;
    			
    			if(DatoDelJuego.charAt(1) == 'L' && auto.matches("LeftAuto"))
    			{
    				addSequential(new ConducirDerecho(3.7, 0.6));
    				addSequential(new ConducirDerecho(2.0, 0.6));
        			addSequential(new GirarGrados(90));
        			addSequential(new ElevadorAutonomo(1.0, 0.8));
        			addSequential(new RecogedorAutonomo(0.6, 0.8));
        			addSequential(new ElevadorAutonomo(1.0, -0.8));
        			
        			}
    			else if(DatoDelJuego.charAt(1)=='R' && auto.matches("LeftAuto")) {
    				addSequential(new ConducirDerecho(3.7, 0.6));
        			addSequential(new GirarGrados(90));
        			addSequential(new ConducirDerecho(4.5, 0.6));
        			addSequential(new GirarGrados(-90));
        			addSequential(new ConducirDerecho(2.0, 0.6));
        			addSequential(new ElevadorAutonomo(1.0, 0.8));
        			addSequential(new RecogedorAutonomo(0.6, 0.8));
        			addSequential(new ElevadorAutonomo(1.0, -0.8));
    			}
    			if(DatoDelJuego.charAt(1)=='R' && auto.matches("RightAuto")) {
    				addSequential(new ConducirDerecho(3.7, 0.6));
        			addSequential(new ConducirDerecho(2.0, 0.6));
        			addSequential(new GirarGrados(-90));
        			addSequential(new ElevadorAutonomo(1.0, 0.8));
        			addSequential(new RecogedorAutonomo(0.6, 0.8));
        			addSequential(new ElevadorAutonomo(1.0, -0.8));
    			}
    			else if (DatoDelJuego.charAt(1) == 'L' && auto.matches("RightAuto")){
    				addSequential(new ConducirDerecho(3.7, 0.6));
    				addSequential(new GirarGrados(-90));
        			addSequential(new ConducirDerecho(4.5, 0.6));
        			addSequential(new GirarGrados(90));
        			addSequential(new ConducirDerecho(2.0, 0.6));
        			addSequential(new ElevadorAutonomo(1.0, 0.8));
        			addSequential(new RecogedorAutonomo(0.6, 0.8));
        			addSequential(new ElevadorAutonomo(1.0, -0.8));
    			}
    	}
    }
}
    	/*if(auto.matches("CenterAuto")){
        	//addSequential(new DriveForward(-230)); // Negative values to drive forward
        	//addSequential(new TimedStraightTakoDrive(1.8, 0.75));
        	//addSequential(new TurnDegrees(90));
        	//addSequential(new PositionPerpendicularly());
        	//addSequential(new DriveToDistancePerpendicularly(50));
        	//addSequential(new DriveForward(200));
    		//addSequential( new ForwardToDistanceTakoDrive(50));
    		addSequential( new TurnDegreesTakoDrive(90));
    	}
    	else if(auto.matches("LeftAuto")||auto.matches("RightAuto")){
    		double leftOrRight = auto.matches("LeftAuto") ? 1: -1.02;
			//addSequential(new DriveForward(-240)); // Negative values to drive forward
    		addSequential(new TimedStraightTakoDrive(1.92, 0.75));
    		addSequential(new TimedTurnTakoDrive(1, 64.2 * leftOrRight));
			//addSequential(new DriveForward(-55)); // Negative values to drive forward
    		addSequential(new TimedStraightTakoDrive(0.55, 0.75));
    		//addSequential(new PositionPerpendicularly());
			//addSequential(new DriveToDistancePerpendicularly(50));
			//addSequential(new DriveForward(200));
    	}
    }
}
*/
