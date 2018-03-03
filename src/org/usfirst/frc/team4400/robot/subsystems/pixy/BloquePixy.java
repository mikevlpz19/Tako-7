package org.usfirst.frc.team4400.robot.subsystems.pixy;

/**
 * representa un bloque pixy.El formato de datos de un bloque puede ser encontrado aqui
* http://www.cmucam.org/projects/cmucam5/wiki/Porting_Guide en la seccion 'Object
* Block Format'.
*
*/
public class BloquePixy {
	public int ImagenAsignada;
	public int X;
	public int Y;
	public int Ancho;
	public int Altura;

	public String toString() {
		return "" + " I:" + "ImagenAsignada" + " X:" + X + " Y:" + Y + " W:" + Ancho + " H:" + Altura;
	}
}