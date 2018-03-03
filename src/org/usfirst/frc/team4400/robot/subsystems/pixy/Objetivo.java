package org.usfirst.frc.team4400.robot.subsystems.pixy;

import org.usfirst.frc.team4400.robot.subsystems.pixy.BloquePixy;

public class Objetivo{
	public int X;
	public int Y;
	public int Ancho;
	public int Altura;
	public String toString() {
		return "X:" + X + " Y:" + Y + " W:" + Ancho + " H:" + Altura;
	}
	public Objetivo(BloquePixy l, BloquePixy r) {
		X = (l.X + r.X)/2;
		Y = (l.Y + r.Y)/2;
		Ancho = l.Ancho / 2 + r.Ancho / 2 + Math.abs(l.X - r.X);
		Altura = (l.Altura + r.Altura) / 2;
	}
	public Objetivo(BloquePixy bloquePixy) {
		X = bloquePixy.X;
		Y =bloquePixy.Y;
		Ancho = bloquePixy.Ancho;
		Altura = bloquePixy.Altura;
	}
}

