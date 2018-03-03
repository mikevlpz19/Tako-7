/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4400.robot;
	//El MapaDelRobot(RobotMap) es un mapa de puertos de sensores y actuadores que estan conectados a un nombre de variable.
 	//Esto de flexibilidad para cambiar conexiones,checar mas facil las conexiones y llevar un orden.
 
public class MapaDelRobot {
	//Por ejemplo para organizar el motor izquierdo y derecho, puedes definir las variables para usar en el subsistema de manejo 
	// public static int MotorIzquierdo = 1;
	// public static int MotorDerecho = 2;

	//Si estas usando multiples modulos,asegurate de definir el numero de puerto y el modulo.
	
	// . Por ejemplo con un rangefinder:
	// public static int rangefinderPuerto = 1;
	// public static int rangefinderModulo = 1;
	
	public static final byte izquierdaFrenteMotor = 4;
	public static final byte derechaFrenteMotor = 3;
	public static final byte izquierdaAtrasMotor = 6;
	public static final byte derechaAtrasMotor = 5;
	public static final byte izquierdaMedioMotor = 8;
	public static final byte derechaMedioMotor = 7;
	
	public static final byte recogedorMotorIzquierdo = 0;
	public static final byte recogedorMotorDerecho = 1;

	public static final byte elevadorMotor1 = 11;
	public static final byte elevadorMotor2= 12;
	public static final byte elevadorInterruptorCima = 2;
	public static final byte elevadorInterruptorBajo = 3;
	
	public static final byte escaladorMotor1 = 9;
	public static final byte escaladorMotor2 = 10;
	public static final byte escaladorInterruptorCima=0;
	public static final byte escaladorInterruptorMedio=1;
	
	public static final byte kLeftShiftSolenoidFwd = 0;
	public static final byte kLeftShiftSolenoidBwd = 1;

	public static final byte CIMencoderCanallA = 8;
	public static final byte CIMencoderCanalB = 9;
}
