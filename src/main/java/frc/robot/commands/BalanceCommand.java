package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.BalanceSubsystem;

public class BalanceCommand extends CommandBase{

    private final BalanceSubsystem m_balanceSubsystem;
    private final DriveTrain m_driveTrain;
    
    public BalanceCommand(BalanceSubsystem balanceSubsystem, DriveTrain driveTrain){
        m_balanceSubsystem = balanceSubsystem;
        m_driveTrain = driveTrain;
        addRequirements(m_balanceSubsystem);
        addRequirements(m_driveTrain);
    }

    @Override
    public void initialize() {
        m_balanceSubsystem.calibrate();
        m_balanceSubsystem.reset();
    }
        
    @Override
    public void execute() {
        m_driveTrain.driveSlowAuto();
   }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        m_driveTrain.stop();
        return m_balanceSubsystem.onTarget();
    }
}