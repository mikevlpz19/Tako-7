/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team4400.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import org.usfirst.frc.team4400.robot.commands.Autonomo;
import org.usfirst.frc.team4400.robot.subsystems.*;

import com.kauailabs.navx.frc.AHRS;
/**
 * El VM esta configurado para automaticamente correr esta clase, y llamar las funciones correspondientes a cada modo,
 * como se describe en la documentacion del TimedRobot
 * Si cambias el nombre de esta clase o el package despues de crear este proyecto, debes tambien cambiarlo en build.properties.
 */
public class Robot extends TimedRobot {
	public static final Conduccion conduccion = new Conduccion();
	public static final Escalador escalador = new Escalador();
	public static final Recogedor recogedor = new Recogedor();
	public static final Elevador elevador = new Elevador();
	public static OI oi;
	private Command ComandoAutonomo;
	AHRS ahrs;
	/**
	 * Esto va a subir todos los valores que se tengan a la Dashboard
	 */
	public void publishDataInDashboard(){
		SmartDashboard.putNumber("DistanceDriven", conduccion.obtenerDistancia());
		SmartDashboard.putNumber("Gyro", conduccion.obtenerAnguloNavx());
		
		/*SmartDashboard.putBoolean("InvertedAxisY", conduccion.isAxisYInverted());
		SmartDashboard.putBoolean("GearBox", conduccion.isGearShifted());
		SmartDashboard.putBoolean("OneStickMode", conduccion.isOneStickMode());
		
		*SmartDashboard.putBoolean("ArmShifted", recogedor.isArmShifted());
		/*
	
		 * SmartDashboard.putNumber("SonarL", drive.getSonarL());
		 * SmartDashboard.putNumber("SonarR", drive.getSonarR());
		 * SmartDashboard.putNumber("AverageSonar", drive.getAverageSonar());
		*/
		
		/* Display 6-axis Processed Angle Data                                      */
        SmartDashboard.putBoolean(  "IMU_Conectada",        ahrs.isConnected());
        SmartDashboard.putBoolean(  "IMU_SeEstaCalibrando",    ahrs.isCalibrating());
        SmartDashboard.putNumber(   "IMU_Yaw",              ahrs.getYaw());
        SmartDashboard.putNumber(   "IMU_tono",            ahrs.getPitch());
        SmartDashboard.putNumber(   "IMU_rodar",             ahrs.getRoll());
        
        /* Muestra el rumbo corregido por la inclinacion gracias al magnetometro, de preferencia, calibrar el magnetometro
         *                                    */
        SmartDashboard.putNumber("	IMU_CompassHeading",   ahrs.getCompassHeading());
        
        /* Display 9-axis Heading (magnetometro calibrado puede ser util)  */
        SmartDashboard.putNumber("	IMU_FusedHeading",     ahrs.getFusedHeading());

        /* Estas funciones son compatibles con la clase WPI Gyro que nos provee una simple*/
        /* ruta para actualizar desde el giroscopio Kit-de-Partes al navx MXP */
        
        SmartDashboard.putNumber(   "IMU_TotalYaw",         ahrs.getAngle());
        SmartDashboard.putNumber(   "IMU_YawRateDPS",       ahrs.getRate());

        /* Mostrar datos de aceleracion procesada (aceleracion linear, deteccion de movimiento) */
        
        SmartDashboard.putNumber(   "IMU_Accel_X",          ahrs.getWorldLinearAccelX());
        SmartDashboard.putNumber(   "IMU_Accel_Y",          ahrs.getWorldLinearAccelY());
        SmartDashboard.putBoolean(  "IMU_SeEstaMoviendo",         ahrs.isMoving());
        SmartDashboard.putBoolean(  "IMU_EstaRotandose",       ahrs.isRotating());

        /* Muestra un estimado de la relacion entre la velocidad y el desplazamiento
         * note que esos valores no se espera que sean demasiado precisos para determinar la posicion del robot en una   */
        /* FIRST FRC Robotics Field, debido al ruido del acelerometro y la union de 
         * estos errores de integracion de velocidad y desplazamiento*/
        
        SmartDashboard.putNumber(   "Velocidad_X",           ahrs.getVelocityX());
        SmartDashboard.putNumber(   "Velocidad_Y",           ahrs.getVelocityY());
        SmartDashboard.putNumber(   "Desplazamiento_X",       ahrs.getDisplacementX());
        SmartDashboard.putNumber(   "Desplazamiento_Y",       ahrs.getDisplacementY());
        

        /*Mostrar los valores de Giróscopo / acelerómetro / magnetómetro sin procesar 
         * NOTA: estos valores normalmente no son necesarios, pero están disponibles para usuarios avanzados
         *.Antes de utilizar estos datos, considere si los datos procesados ​​(consulte más arriba) se adaptarán a sus necesidades.*/    
        
        SmartDashboard.putNumber(   "RawGyro_X",            ahrs.getRawGyroX());
        SmartDashboard.putNumber(   "RawGyro_Y",            ahrs.getRawGyroY());
        SmartDashboard.putNumber(   "RawGyro_Z",            ahrs.getRawGyroZ());
        SmartDashboard.putNumber(   "RawAccel_X",           ahrs.getRawAccelX());
        SmartDashboard.putNumber(   "RawAccel_Y",           ahrs.getRawAccelY());
        SmartDashboard.putNumber(   "RawAccel_Z",           ahrs.getRawAccelZ());
        SmartDashboard.putNumber(   "RawMag_X",             ahrs.getRawMagX());
        SmartDashboard.putNumber(   "RawMag_Y",             ahrs.getRawMagY());
        SmartDashboard.putNumber(   "RawMag_Z",             ahrs.getRawMagZ());
        SmartDashboard.putNumber(   "IMU_Temp_C",           ahrs.getTempC());
        SmartDashboard.putNumber(   "IMU_MarcaDeTiempo",        ahrs.getLastSensorTimestamp());
        
        /* Omnimount Yaw Axis Information                                           */
        /* Para mas informacion, ver http://navx-mxp.kauailabs.com/installation/omnimount  */
        AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
        SmartDashboard.putString(   "YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
        SmartDashboard.putNumber(   "YawAxis",              yaw_axis.board_axis.getValue() );
        
        /* Información de la placa del sensor*/
        SmartDashboard.putString(   "FirmwareVersion",      ahrs.getFirmwareVersion());
        

 /* Quaternion Datos * /
        / * Quaterniones son fascinantes, y son la representación más compacta de * /
        / * datos de orientación. Todos los valores de Yaw, Pitch and Roll pueden derivarse * /
        / * de los Quaternions. Si está interesado en el procesamiento de movimiento, el conocimiento de * /
        / * Quaternions es muy recomendable.                                      */
        SmartDashboard.putNumber(   "QuaternionW",          ahrs.getQuaternionW());
        SmartDashboard.putNumber(   "QuaternionX",          ahrs.getQuaternionX());
        SmartDashboard.putNumber(   "QuaternionY",          ahrs.getQuaternionY());
        SmartDashboard.putNumber(   "QuaternionZ",           ahrs.getQuaternionZ());
        
        /*Soporte de depuración de conectividad*/
        SmartDashboard.putNumber(   "IMU_Byte_Count",       ahrs.getByteCount());
        SmartDashboard.putNumber(   "IMU_Update_Count",     ahrs.getUpdateCount());
	}
		/*
		 * SmartDashboard.putNumber("SonarL", drive.getSonarL());
		 * SmartDashboard.putNumber("SonarR", drive.getSonarR());
		 * SmartDashboard.putNumber("AverageSonar", drive.getAverageSonar());
		*/
	  //Esta funcion es ejecutada cuando el robot inicia a trabajar por primera vez y debe ser usado para un codigo de inicio.	 
	@Override
	public void robotInit() {
		oi = new OI();
		ahrs = conduccion.establecerNavx();
	}
	/**
	 * Esta funcion es llamada una cada vez que el robot entra en Disabled mode.
	 * Puede ser usada para borrar un subsistema de informacion que quieras limpiar cuando el robot esta deshabilitado.
	 */
	@Override
	public void disabledInit() {
		//Robot.drive.shiftGears(false);
	}
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		Robot.conduccion.reiniciarEncoder();
		Robot.conduccion.reiniciarNavx();
		//ComandoAutonomo = new Autonomo(SmartDashboard.getString("Select Auto", "NoAuto")); //chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		//programar el comando autónomo(ejemplo)
		if (ComandoAutonomo != null)
			ComandoAutonomo.start();
	}
	/**
	 * Esta funcion es llamada perodicamente durante el autonomo.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		publishDataInDashboard();
	}
	@Override
	public void teleopInit() {
		//Esto asegura que el autonomo se detiene cuando el teleop empieza.Si quieres que el autonomo siga aunque sea interruptido 
		//por otros comandos.Elimina o comenta esta linea de codigo.
	if(ComandoAutonomo!=null)
		ComandoAutonomo.cancel();
	}
	 //Esta funcion es llamada periodicamente durante el control de operador.
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		publishDataInDashboard();
	}
	/**
	 * Esta funcion es llamada periodicamente durante el modo de prueba.
	 */
	@Override
	public void testPeriodic() {
	}
}

