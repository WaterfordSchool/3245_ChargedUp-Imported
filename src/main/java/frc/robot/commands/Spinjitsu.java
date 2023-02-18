package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Spinjitsu extends CommandBase{
        
        private final DriveTrain m_driveTrain;
        int direction;
        XboxController driveControl;

        public Spinjitsu(DriveTrain subsystem, int m_direction, XboxController driverController) {
            m_driveTrain = subsystem;
            direction = m_direction;
            addRequirements(m_driveTrain);
            driveControl = driverController;
          }
          @Override
          public void initialize(){}

          // Called when the command is initially scheduled.
          @Override
          public void execute() {
            //spin really, really fast
            m_driveTrain.spinjitsu(direction);
          }
        
          @Override
          public boolean isFinished() {
            //not pressing right D pad 
            return !driveControl.getRawButton(7);
          }
  }