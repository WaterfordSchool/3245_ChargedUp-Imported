package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoBalanceDriveCommand extends CommandBase {
    /**
     * Creates a new AutoCommand.
     */
    private final DriveTrain m_driveTrain;
    private double m_speed;
    private double m_angle;
    private double m_pitch;
    
    public AutoBalanceDriveCommand(DriveTrain driveTrain, double speed, double angle) {
      // Use addRequirements() here to declare subsystem dependencies.
      m_driveTrain = driveTrain;
      addRequirements(m_driveTrain);
      m_speed = speed;
      m_angle = angle;
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_pitch = m_driveTrain.getPitch();
        m_driveTrain.driveAuto(m_speed, m_angle);
      // drive straight at half speed
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return Math.abs(m_pitch)>8.7;
      // end the command if gyro angle is greater than 0
      //later use PID for better accuracy, start with  bang-bang control
    }
  }