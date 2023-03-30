package frc.robot.commands.auto;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class BalancePIDCommand extends CommandBase{

    private final DriveTrain m_driveTrain;
    private double m_pitch;
    private double m_yawSetpoint;
    double kPYaw;
    double yawOffset;
    private final PIDController pidController;
    
    public BalancePIDCommand(DriveTrain driveTrain){
        m_driveTrain = driveTrain;
        addRequirements(m_driveTrain);
        pidController = new PIDController(0.03, 0, 0);
        pidController.setSetpoint(0);
        m_yawSetpoint = 0;
        kPYaw = 0.03;
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        m_pitch = m_driveTrain.getPitch();
        yawOffset = m_driveTrain.getYaw();

        if(Math.abs(m_pitch)>.6){
            m_driveTrain.closedArcadeDrive(pidController.calculate(m_pitch), 0);
        } else {
            m_driveTrain.closedArcadeDrive(0, 0);
            pidController.reset();
        }
   }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}