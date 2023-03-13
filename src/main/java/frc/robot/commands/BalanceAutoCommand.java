package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.BalanceSubsystem;

public class BalanceAutoCommand extends CommandBase{

    private final DriveTrain m_driveTrain;
    private final int m_count = 0;
    private double m_pitch;
    
    public BalanceAutoCommand(DriveTrain driveTrain){
        m_driveTrain = driveTrain;
        addRequirements(m_driveTrain);
    }

    @Override
    public void initialize() {
        m_driveTrain.resetStuff();
    }
        
    @Override
    public void execute() {
        m_pitch = m_driveTrain.getPitch();
        m_driveTrain.balance(m_pitch, m_count);
   }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        m_driveTrain.stop();
        return false;
    }
}