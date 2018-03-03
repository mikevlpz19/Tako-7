package org.usfirst.frc.team4400.robot.commands;
import org.usfirst.frc.team4400.robot.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;
/**
 *
 */
public class AbrirCerrarBrazo extends InstantCommand {

	public AbrirCerrarBrazo() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.recogedor);
    }
    
    // Called once when the command executes
    protected void initialize() {
    	Robot.recogedor.AbrirCerrarBrazo(!Robot.recogedor.isArmShifted());
    }
}
