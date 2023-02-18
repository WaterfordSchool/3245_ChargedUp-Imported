package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase{
        
        private final DriveTrain m_driveTrain;
        double speed;
        double turnSpeed;
        XboxController driveControl;

        public ArcadeDrive(DriveTrain subsystem, double kSpeed, double kTurnSpeed, XboxController driverController) {
            m_driveTrain = subsystem;
            addRequirements(m_driveTrain);
            speed = kSpeed;
            turnSpeed = kTurnSpeed;
            driveControl = driverController;
          }
          
          @Override
          public void initialize(){
            //ramping
          }

          // Called when the command is initially scheduled.
          @Override
          public void execute() {
            m_driveTrain.drive(driveControl, speed, turnSpeed);
          }
        
          @Override
          public boolean isFinished() {
            return false;
          }
  }