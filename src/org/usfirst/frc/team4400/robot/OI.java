package org.usfirst.frc.team4400.robot;
import org.usfirst.frc.team4400.robot.commands.InvertirEjeY;
import org.usfirst.frc.team4400.robot.commands.ModoStick;
import org.usfirst.frc.team4400.robot.commands.elevador.ApagarElevador;
import org.usfirst.frc.team4400.robot.commands.elevador.BajarElevador;
import org.usfirst.frc.team4400.robot.commands.elevador.SubirElevador;
//import org.usfirst.frc.team4400.robot.commands.escalador.BajarEscalador;
//import org.usfirst.frc.team4400.robot.commands.escalador.SubirEscalador;

import org.usfirst.frc.team4400.robot.commands.recogedor.AgarrarCaja;
import org.usfirst.frc.team4400.robot.commands.recogedor.ApagarRecogedor;

import org.usfirst.frc.team4400.robot.commands.recogedor.PonerCaja;
import org.usfirst.frc.team4400.robot.commands.recogedor.RecogedorDerecha;
import org.usfirst.frc.team4400.robot.commands.recogedor.RecogedorIzquierda;
//import org.usfirst.frc.team4400.robot.commands.recogedor.RecogedorIzquierda;
//import org.usfirst.frc.team4400.robot.commands.recogedor.RecogedorDerecha;
import org.usfirst.frc.team4400.robot.triggers.POV;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Esta clase es el pegamento que une los controles en el operador de interfaz fisico a los comandos y grupos de comandos que permiten
 * controlar el robot*/
public class OI {
////CREACION DE BOTONES.
	// Un tipo de boton es un boton en un joystick.
	// Se crea uno diciendo en que Joystick esta y que numero de boton es el boton.
	// Joystick stick = new Joystick(puerto del Joystick);
	// Button boton = new JoystickButton(Nombre del joystick en del boton, Numero del boton);
	//Hay algunos botones incorporados adicionales que puede usar. Adicionalmente,
	//Al subclasificar el botón puede crear activadores personalizados y vincularlos a
	//comandos igual que cualquier otro botón.
/// EJECUTAR COMANDOS CON BOTONES
	// Cuando ya tienes un boton, es insignificante unirlo a un boton en tres formas. 
	// Iniciar el comando cuando el boton es presionado y dejar correr el comando mientras sucede
	// boton.whenPressed(new ComandoEjemplo());
	// Correr el comando mientras el boton es presionado y se interrumpe cuando se deja de presionar.
	// boton.whileHeld(new ComandoEjemplo());
	// Iniciar el comando cuando el boton es liberado y lo deja correr.
	// boton.whenReleased(new ComandoEjemplo());

	Joystick stick0, stick1;

	Button j1btnA, j1btnB, j1btnX, j1btnY, j1btnLB, j1btnRB, j1btnSelect, j1btnStart, j1btnLS, j1btnRS;
	Trigger j1POVUp, j1POVDown, j1POVLeft, j1POVRight;
	Button j2btnA, j2btnB, j2btnX, j2btnY, j2btnLB, j2btnRB, j2btnSelect, j2btnStart, j2btnLS, j2btnRS;
	Trigger j2POVUp, j2POVDown, j2POVLeft, j2POVRight;
	
	public OI() {
		super();
		
		stick0 = new Joystick(0);
		stick1 = new Joystick(1);

		j1btnA = new JoystickButton(stick0, 1);
		j1btnB = new JoystickButton(stick0, 2);
		j1btnX = new JoystickButton(stick0, 3);
		j1btnY = new JoystickButton(stick0, 4);
		j1btnLB = new JoystickButton(stick0, 5);
		j1btnRB = new JoystickButton(stick0, 6);
		j1btnSelect = new JoystickButton(stick0, 7);
		j1btnStart = new JoystickButton(stick0, 8);
		j1btnLS = new JoystickButton(stick0, 9);
		j1btnRS = new JoystickButton(stick0, 10);
		j1POVUp = new POV(stick0, POV.Direccion.Arriba);
		j1POVDown = new POV(stick0, POV.Direccion.Abajo);
		j1POVLeft = new POV(stick0, POV.Direccion.Izquierda);
		j1POVRight = new POV(stick0, POV.Direccion.Derecha);

		/*j2btnA = new JoystickButton(stick1, 1);
		j2btnB = new JoystickButton(stick1, 2);
		j2btnX = new JoystickButton(stick1, 3);
		j2btnY = new JoystickButton(stick1, 4);
		j2btnLB = new JoystickButton(stick1, 5);
		j2btnRB = new JoystickButton(stick1, 6);
		j2btnSelect = new JoystickButton(stick1, 7);
		j2btnStart = new JoystickButton(stick1, 8);
		j2btnLS = new JoystickButton(stick1, 9);
		j2btnRS = new JoystickButton(stick1, 10);
		j2POVUp = new POV(stick0, POV.Direccion.Arriba);
		j2POVDown = new POV(stick0, POV.Direccion.Abajo);
		j2POVLeft = new POV(stick0, POV.Direccion.Izquierda);
		j2POVRight = new POV(stick0, POV.Direccion.Derecha);
		*/
		//j1btnStart.whenPressed(new ShiftGearBox());
		j1btnSelect.whenPressed(new InvertirEjeY());
		//j1btnLB.whenPressed(new SpeedDownDrive());
		//j1btnRB.whenPressed(new SpeedUpDrive());
		
		//j1btnRB.whenPressed(new ShiftArm());
		j1btnLS.whenPressed(new ModoStick());
		j1btnRS.whenPressed(new ModoStick());

		/*
		j1btnA.whileHeld(new IntakeLeft());
		j1btnA.whenReleased(new IntakeOff());
		j1btnY.whileHeld(new IntakeRight());
		j1btnY.whenReleased(new IntakeOff());
		*/
		j1btnA.whileHeld(new AgarrarCaja());
		j1btnA.whenReleased(new ApagarRecogedor());
		j1btnY.whileHeld(new PonerCaja());
		j1btnY.whenReleased(new ApagarRecogedor());
		j1btnX.whileHeld(new RecogedorIzquierda());
		j1btnX.whenReleased(new ApagarRecogedor());
		j1btnB.whileHeld(new RecogedorDerecha());
		j1btnB.whenReleased(new ApagarRecogedor());
		
		j1btnRB.whileHeld(new BajarElevador());
		j1btnRB.whenReleased(new ApagarElevador());
		j1btnLB.whileHeld(new SubirElevador());
		j1btnLB.whenReleased(new ApagarElevador());

		//j1POVUp.whilePressed(new SubirEscalador());
		//j1POVDown.whilePressed(new BajarEscalador());
	}
	
	public Joystick getJoystick(int i){
		return i == 0 ? stick0 : i == 1 ? stick1 : null;
	}
}

