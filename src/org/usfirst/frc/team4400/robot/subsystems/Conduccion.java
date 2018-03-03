package org.usfirst.frc.team4400.robot.subsystems;

import org.usfirst.frc.team4400.robot.MapaDelRobot;
import org.usfirst.frc.team4400.robot.commands.ConduccionPorDefecto;
//import org.usfirst.frc.team4400.robot.subsystems.Pixy.PixyI2C;
//import org.usfirst.frc.team4400.robot.subsystems.Pixy.PixyBlock;
//import org.usfirst.frc.team4400.robot.subsystems.Pixy.Target;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Conduccion extends Subsystem {
	//Poner metodos para controlar este subsistema.Llamarlos desde los comandos
	private WPI_TalonSRX izquierdaFrente, izquierdaMedio, izquierdaAtras, derechaFrente, derechaMedio, derechaAtras;
	private SpeedControllerGroup GrupoDeMotoresIzquierda, GrupoDeMotoresDerecha;
	private double lastTurnPower = 0, lastPower = 0;
	private static final double maxGain = 0.05, kPulsesPerTurn = 40, kDiameter = 4 * 2.54, kDistancePerPulse = kDiameter/kPulsesPerTurn;
	private boolean invertedAxisY = true, gearShift = false, oneStickMode = false;
	private AHRS navx;
	private Encoder CIMcoder;
	private double limiteDeVelocidad = 1;
	//private PixyI2C pixy;
	private DoubleSolenoid shift;
	private String type = SmartDashboard.getString("Tipo Drive", "Arcade");
	public 	Conduccion(){
		super();
		izquierdaFrente = new WPI_TalonSRX(MapaDelRobot.izquierdaFrenteMotor);
		izquierdaMedio = new WPI_TalonSRX(MapaDelRobot.izquierdaMedioMotor);
		izquierdaAtras = new WPI_TalonSRX(MapaDelRobot.izquierdaAtrasMotor);
		derechaFrente = new WPI_TalonSRX(MapaDelRobot.derechaFrenteMotor);
		derechaMedio = new WPI_TalonSRX(MapaDelRobot.derechaMedioMotor);
		derechaAtras = new WPI_TalonSRX(MapaDelRobot.derechaAtrasMotor);
	
		izquierdaFrente.setInverted(false);
		izquierdaMedio.setInverted(!izquierdaFrente.getInverted());
		izquierdaAtras.setInverted(izquierdaFrente.getInverted());
		derechaFrente.setInverted(!izquierdaFrente.getInverted());
		derechaMedio.setInverted(izquierdaFrente.getInverted());
		derechaAtras.setInverted(!izquierdaFrente.getInverted());
		
		NeutralMode mode =NeutralMode.Brake;
		
		izquierdaFrente.setNeutralMode(mode);
		derechaFrente.setNeutralMode(mode);
		izquierdaAtras.setNeutralMode(mode);
		derechaAtras.setNeutralMode(mode);
		izquierdaMedio.setNeutralMode(mode);
		derechaMedio.setNeutralMode(mode);
		
		GrupoDeMotoresIzquierda = new SpeedControllerGroup(izquierdaFrente, izquierdaMedio, izquierdaAtras);
		GrupoDeMotoresDerecha = new SpeedControllerGroup(derechaFrente, derechaMedio, derechaAtras);
		
		gearShift = false;
		this.cambioDeMarcha(gearShift);
		
		navx = new AHRS(SerialPort.Port.kUSB1);
		navx.reset();
		navx.enableLogging(true);
		
		CIMcoder = new Encoder(MapaDelRobot.CIMencoderCanallA, MapaDelRobot.CIMencoderCanalB);
		CIMcoder.reset();
		CIMcoder.setDistancePerPulse(kDistancePerPulse);
	}
    public void initDefaultCommand() {
    	//establecer el comando por defecto para el subsistema aqui
    	//setDefaultCommand(new ComandoPorDefecto());
    	setDefaultCommand(new ConduccionPorDefecto());
    }
	  
    /**
     * @return devuelve la NavX usada en el robot
     */
    public AHRS establecerNavx() {
    	return navx;
    }
    
    /**
     * Angulo adquirido por la NavX-MXP
     */
    public double obtenerAnguloNavx(){
    	return navx.getAngle();
    }
    
    public double getNavxAccel() {
    	return navx.getDisplacementX();
    }
    
    /**
     * True si Navx esta conectada y falso si no
     */
    public boolean seConectoNavx() {
    	return navx.isConnected();
    }
    
    /**Obtener la distancia que el robot ha conducido desde la ultima vez que se cerro el setDistancePerPulse(Double)
     */    
    public double obtenerDistancia(){
    	return CIMcoder.getDistance();
    }
    
    /**
     * devuelve el encoder usado para el sistema de conduccion
     */
    public Encoder obtenerCIMcoder() {
    	return CIMcoder;
    }

    /**
     * Regresa la distancia del encoder a cero. resetea la cuenta por asi decirlo damas y caballeros.
     */
    public void reiniciarEncoder(){
    	CIMcoder.reset();
    }
    
    /**
     * Borra todos los valores Navx a cero
     */
    public void reiniciarNavx(){
    	navx.reset();
    }
    
    /**
     *
     * @param isInvertedY
     */
    public void marcarEjesInvertidos(boolean isInvertedY){
    	invertedAxisY = isInvertedY;
    }
    
    /**
     * devuelve true si el ejey ESTA INVERTIDO, falso si no
     */
    public boolean seInvertioEjeY(){
    	return invertedAxisY;
    }
    
    
    /**Devuelve el valor de una variable booleana que va a indicar si el robot es controlado por un stick o por dos
     */
    public boolean modoStick(){
    	return oneStickMode;
    }
    
    /**
     * 
     * @param modoStick True para un Stick Drive Mode
     */
    public void establecerModoStick(boolean stickMode){
    	oneStickMode = stickMode;
    }
    /**
     *  Returns if the drivetrain has driven the desired distance
     * @param setpoint
     * @return True or false
     */
    public boolean onDriveSetpoint(double setpoint){
    	return Math.abs(setpoint - this.obtenerDistancia()) < 10 ? true : false;
    }
    
    /**
     * devuelve si la conduccion se ha pasado de la distancia ignorada
     * @param setpoint
     * @return verdadero y falso
     */
    public boolean onAngleSetpoint(double setpoint){
    	return Math.abs(setpoint - this.obtenerAnguloNavx())< 8 ? true : false;
    }
    
    /**
     * Devuelve un valor booleano que va a indicar el estado de gearBox
     */
    public boolean MarchaCambiando() {
    	return gearShift;
    }
    
    /**
     * esto va hacer a la cajadecambios, cambiar
     * @param True para delante , false para atras
     */
    public void cambioDeMarcha(boolean valor) {
    	//shift.set(value ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    	gearShift = valor;
 
    }
    
    /**
     * esto va a multiplicar el limite para todos los motores 
     * @param valeor limite de potencia ignorado Max output desired
     */
    public void establecerLimiteVelocidad(double valor) {
    	limiteDeVelocidad = valor;
    }
    
    private void establecerIzquierda(double izquierdaPoder){
    	if (izquierdaPoder>1)
    		izquierdaPoder=1;
    	if (izquierdaPoder<-1)
    		izquierdaPoder=-1;
    	GrupoDeMotoresIzquierda.set(izquierdaPoder*limiteDeVelocidad);
    }
    
    private void establecerDerecha(double derechaPoder){
    	if (derechaPoder>1)
    		derechaPoder=1;
    	if (derechaPoder<-1)
    		derechaPoder=-1;
    	GrupoDeMotoresDerecha.set(derechaPoder*limiteDeVelocidad);
    }
    
    /**
     * algoritmo smple para un manejo tipo arcade
     * poderVelocidad usualmente en el eje X
     * poderRotacion usualmente en el eje Y
     */
    public void arcadeDrive(double poderRotacion, double poderVelocidad){
    	establecerIzquierda(poderVelocidad+ poderRotacion);
    	establecerDerecha(poderVelocidad - poderRotacion);
        lastTurnPower = poderRotacion;
        lastPower = poderVelocidad;
    }
    /**
     * recibe solo un joystick y usa sus ejes X y Y para el manejo tipo tweened
     * @param joystick Joystick that will control the robot
     * @param deadzone Area of the joystick that won't send any data
     */
	public void tweenedDrive(Joystick joystick, double zonaMuerta) {
		tweenedDrive(joystick.getX(), joystick.getY(), zonaMuerta);
	}

    /**
     * va hacer un tweened drive despues de eliminar la zonaMuerta del joysick 
     * @param poderRotacion usualmente eje X
     * @param poderVelocidad usualmente eje Y
     * @param zonaMuerta es el area del joystick que no envia ningun dato
     */
	public void tweenedDrive(double poderRotacion, double poderVelocidad, double zonaMuerta) {
		tweenedDrive(Math.abs(poderRotacion) > zonaMuerta ? poderRotacion : 0, 
				Math.abs(poderVelocidad) > zonaMuerta ? poderVelocidad : 0);
	}
	
	/**
	 * Recive solo un joystick y usa los ejes X y Y para el tweened drive 
	 * @param joystick Joystick que va a controlar el robot
	 */
	public void tweenedDrive(Joystick joystick) {
		tweenedDrive(joystick.getX(), joystick.getY());
	}
    
	/**
	 * Va a usar maxGain constante para elaborar el drivetrain movimiento mas suave y no dañar los motores
	 */
    public void tweenedDrive(double poderRotacion, double poderVelocidad){
     poderVelocidad *= invertedAxisY ? -1 : 1;
    	if(Math.abs(poderRotacion- lastTurnPower) > maxGain)
    		poderRotacion = poderRotacion >= lastTurnPower ? 
    				lastTurnPower + maxGain : lastTurnPower - maxGain;
    	if(Math.abs(poderVelocidad - lastPower) > maxGain)
    		poderVelocidad = poderVelocidad >= lastPower ? 
    				lastPower + maxGain : lastPower - maxGain;
    	arcadeDrive(poderVelocidad, poderRotacion);
    }
    
    /*public PixyBlock[] getPixyBlocks() {
    	return pixy.readBlocks();
    }
    
    public void printPixyBlocks(){
    	try {
    		PixyBlock[] blocks = getPixyBlocks();
    		if(blocks != null )
	    		for(int i = 0; i < blocks.length; i ++)
	    			if(blocks[i] != null)
	    			{
	    				System.out.println(blocks[i].toString());
	    				SmartDashboard.putString("PixyBlock " + i, blocks[i].toString());
	    			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public Objetivo obtenerObjetivo() {
		BloquePixy[] blocks = new BloquePixy();
		if(blocks != null)
			if(blocks[0] != null && blocks[1] != null) {
				System.out.println("Encontre 2");
				return new Objetivo(blocks[0], blocks[1]);
			} else if (blocks[0] != null) {
				System.out.println("Encontre 1");
				return new Objetivo(blocks[0]);
			}
		System.out.println("Encontre 0");
		return null;
    }*/
}
