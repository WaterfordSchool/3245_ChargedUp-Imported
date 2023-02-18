package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveTimedCommand extends CommandBase{
        
        private final DriveTrain m_driveTrain;
        double speed;
        double time;
        Timer timer;
        public DriveTimedCommand(DriveTrain subsystem, double kSpeed, double timePassed) {
            m_driveTrain = subsystem;
            addRequirements(m_driveTrain);
            speed = kSpeed;
            timePassed = time;
          }
          @Override
          public void initialize(){
            //ramping
            timer.start();
            timer.reset();
          }

          // Called when the command is initially scheduled.
          @Override
          public void execute() {
            m_driveTrain.driveNoController(speed, time);
          }
        
          @Override
          public boolean isFinished() {
            return timer.get()>time;
          }
  }