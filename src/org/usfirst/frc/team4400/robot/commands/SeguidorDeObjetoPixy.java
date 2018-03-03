package org.usfirst.frc.team4400.robot.commands;

import org.usfirst.frc.team4400.robot.Robot;
import org.usfirst.frc.team4400.robot.subsystems.pixy.*;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SeguidorDeObjetoPixy extends InstantCommand {

	//Target t;
	private int XPrevError, YPrevError;
	private int XPGain, XDGain, YPGain, YDGain;
	private double XVel, YVel;
	private int desiredWidth;
	
    public SeguidorDeObjetoPixy() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.conduccion);
        desiredWidth = 80;
        XPGain = 300;
        XDGain = 500;
        YPGain = 300;
        YDGain = 300;
        
    }

    // Called once when the command executes
    /*protected void initialize() {
    	t = Robot.conduccion.getTarget();
    	int XError;
    	int YError;
		SmartDashboard.putString("Intento", "true");
		if(t != null) {
			SmartDashboard.putString("Veo Algo", "true");
    		XError = t.X - PixyI2C.X_CENTER;
    		YError = t.Width - desiredWidth;
    		SmartDashboard.putString("YError", "" + YError);
    		SmartDashboard.putString("YVel", "" + YVel);
    		updatePixyFollower(XError, YError);
    		Robot.conduccion.arcadeDrive(-YVel, XVel);
		}
    }*/
    
    private void updatePixyFollower(int XError, int YError) {
    	if(XPrevError!=0x80000000) {
    		XVel = ((XError * XPGain + (XError - XPrevError) * XDGain)>>10) / 55.0;
    	}
    	XPrevError = XError;
    	if(YPrevError!=0x80000000) {
    		YVel = ((YError * YPGain + (YError - YPrevError) * YDGain) >> 10) / 25.0;
    	}
    	YPrevError = YError;
    }

}
