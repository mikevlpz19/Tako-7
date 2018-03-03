package org.usfirst.frc.team4400.robot.subsystems.pixy;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * lee datos de la pixy
 * 
 * Some Terminos clave:
 * 
 * frame: foto tomada por la pixy
 * 
 * signature - se usa para la distencion pa
 * 
 * block - objeto que la pixy reconoce.
 * 
 * pixy toma una foto llamada frame. pixy busca el frame de objeto.se pueden obtener facilmente pero no quisimos.
 *
 * readBlocks metodo buscara encontrar el control de los liquidos;
 */
public class PixyI2C{
	
	//nombre de la pixy, calma estos gracs Name of pixy camera. Helps when print info out to id which one is being
	// referred to.
	String nombre;

	// LA CONECCION I2C A LA CAMARA.
	I2C puerto;

	public static final int MAX_X = 320, MIN_X = 0;
	public static final int MAX_Y = 200, MIN_Y = 0;
	public static final int X_CENTER = (MIN_X + MAX_X) / 2;
	public static final int Y_CENTER = (MIN_Y + MAX_Y) / 2;

	public PixyI2C(String id, I2C i2c) {
		puerto = i2c;
		nombre = id;
	}
	// Este método analiza datos sin procesar del pixy en enteros legibles
	public int bytesToInt(byte upper, byte lower) {
		return (((int) upper & 0xff) << 8) | ((int) lower & 0xff);
	}
	/**
	 * Leer datos de len de Pixy. Si ocurre alguna excepción o menos datos que
	 * solicitado es read return null.
	 * 
	 * @param len
	 *            cantidad de datos para leer
	 * @return byte arreglo que contiene datos.
	 */
	private byte[] leerDatos(int len) {
		byte[] rawData = new byte[len];
		try {
			puerto.readOnly(rawData, len);
		} catch (RuntimeException e) {
			SmartDashboard.putString(nombre + "Status", e.toString());
			System.out.println(nombre + "  " + e);
			return null;
		}
		if (rawData.length < len) {
			SmartDashboard.putString(nombre + "Status", "raw data length " + rawData.length);
			System.out.println("byte array length is broken length=" + rawData.length);
			return null;
		}
		return rawData;
	}

	/**
	 * lee una palabra de la pixy si hay un error devuele 0
	 */
	private int readWord() {
		byte[] data = leerDatos(2);
		if (data == null) {
			return 0;
		}
		return bytesToInt(data[1], data[0]);
	}

	public BloquePixy leerBloque(int checksum) {


		byte[] data = leerDatos(10);
		if (data == null) {
			return null;
		}
		BloquePixy block = new BloquePixy();
		block.ImagenAsignada = bytesToInt(data[1], data[0]);
		if (block.ImagenAsignada <= 0 || block.ImagenAsignada > 7) {
			return null;
		}
		block.X = bytesToInt(data[3], data[2]);
		block.Y = bytesToInt(data[5], data[4]);
		block.Ancho = bytesToInt(data[7], data[6]);
		block.Altura = bytesToInt(data[9], data[8]);

		int sum = block.ImagenAsignada + block.X + block.Y + block.Ancho + block.Altura;
		if (sum != checksum) {
			return null;
		}
		return block;
	}

	// numero maximos de imagenes asignadas que pueda ver la pixy
	private final int MAX_SIGNATURES = 1;
	// numero de bytes que representan un objeto
	private final int OBJECT_SIZE = 12;
	// Secuencia de bytes que representa el inicio de un bloque. Dos de estos (o uno de
	// estas y una palabra de código de color) en una fila
	// indica el inicio de un nuevo cuadro.
	private final int START_WORD = 0xaa55;
	// secuencia de byte que representa el inicio de codigo de bloques de color
	private final int START_WORD_CC = 0xaa56;
// si veeos este secuencia de bytes estamos leyendo los datos de un lado

	private final int START_WORD_X = 0x55aa;
	/**
* Busque las palabras de inicio. Dos palabras iniciales en una fila indican el comienzo de una nueva
* cuadro. La palabra de inicio único indica el comienzo de un objeto. Limitar el
* número de bytes para leer, por lo que no hacemos bucles sin fin buscando el marco.
*
* @return true si se encuentra la palabra de inicio. falso si no
	 */
	public boolean findFrameStart() {
		int numBytesRead = 0;
		int lastWord = 0xffff;
		// Mientras que la condición era originalmente cierta ... puede que no sea una buena idea si
		// algo va mal robot quedará atrapado en este ciclo para siempre. Asi que
		// vamos a leer cierta cantidad de bytes y darnos por vencidos para que otras cosas de robot
		// puede tener la oportunidad de correr. ¿Qué número de bytes elegir? Tal vez el tamaño
		// de un bloque * número máximo de firmas que se pueden detectar? O como
		// sobre el tamaño del bloque y el número máximo de bloques que estamos buscando?
		while (numBytesRead < (OBJECT_SIZE * MAX_SIGNATURES)) {
			int word = readWord();
			numBytesRead += 2;
			if (word == 0 && lastWord == 0) {
				return false;
			} else if (word == START_WORD && lastWord == START_WORD) {
				//inicio de un marco
				return true;
			} else if (word == START_WORD_CC && lastWord == START_WORD) {
				// inicio de un marco
				return true;
			} else if (word == START_WORD_X) {
				// Encontramos un byte que indica el final de la palabra de inicio y uno
				// byte que indica la siguiente palabra de inicio. Eso significa que somos
				// desplazado / compensado por un byte. Intenta leer un byte solo
				// podemos volver a la pista.
				leerDatos(1);
				numBytesRead += 1;
			}
			lastWord = word;
		}
		return false;
	}

	private boolean skipStart = false;

	/**
	 * leer todos los bloques en el marco.
	 * 
 
	 */
	public BloquePixy[] leerBloques() {
		//esto debe unir el numero maximo de bloques establecidos en pixymon?
		// TODO: podemos configurar la pixy via i2x para buscar cierto numero de bloquescan
		int maxBlocks = 2;
		BloquePixy[] blocks = new BloquePixy[maxBlocks];
//busca el inicio de un nuevo marco
		if (!skipStart) {
			if (findFrameStart() == false) {
				return null;
			}
		} else {
			skipStart = false;
		}
		//leer datos de la pixy y marcarlos para buscar bloques 
		for (int i = 0; i < maxBlocks; i++) {

			blocks[i] = null;
			int checksum = readWord();
			if (checksum == START_WORD) {
				//comienza la creacion de un nuevo marco(frame)
				skipStart = true;
				return blocks;
			} else if (checksum == START_WORD_CC) {
				//comienza la creacion de un nuevo marco(frame)
				skipStart = true;
				return blocks;
			} else if (checksum == 0) {
				return blocks;
			}
			blocks[i] = leerBloque(checksum);
		}
		return blocks;
	}
}