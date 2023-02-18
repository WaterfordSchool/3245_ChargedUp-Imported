package frc.robot.commands;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoCommandBalance extends CommandBase {
    /**
     * Creates a new AutoCommand.
     */
    private final DriveTrain m_driveTrain;
    Timer timer;
    private ADXRS450_Gyro gyro;
    
    public AutoCommandBalance(DriveTrain driveTrain) {
      // Use addRequirements() here to declare subsystem dependencies.
      m_driveTrain = driveTrain;
      addRequirements(m_driveTrain);
      timer = new Timer();
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        timer.reset();
        timer.start();
        gyro.calibrate();
        gyro.reset();
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      m_driveTrain.driveSlowAuto();
      // drive straight at half speed
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return gyro.getAngle()>0;
      // end the command if gyro angle is greater than 0
      //later use PID for better accuracy, start with  bang-bang control
    }
  }