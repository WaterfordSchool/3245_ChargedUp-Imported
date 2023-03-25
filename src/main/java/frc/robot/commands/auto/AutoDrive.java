package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoDrive extends CommandBase {
    /**
     * Creates a new AutoCommand.
     */
    private final DriveTrain m_driveTrain;
    Timer timer;
    double time;
    double m_speed;
    double m_angle;
    double m_seconds;
    
    public AutoDrive(DriveTrain driveTrain, double seconds, double speed, double angle) {
      // Use addRequirements() here to declare subsystem dependencies.
      m_driveTrain = driveTrain;
      addRequirements(m_driveTrain);
      timer = new Timer();
      m_seconds = seconds;
      m_speed = speed;
      m_angle = angle;
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
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
      return timer.get()>m_seconds;
      // end the command if gyro angle is greater than 0
      //later use PID for better accuracy, start with  bang-bang control
    }
  }