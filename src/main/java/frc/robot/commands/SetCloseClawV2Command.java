package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawV2Subsystem;

public class SetCloseClawV2Command extends CommandBase{

    private final ClawV2Subsystem m_clawSubsystem;
    
    public SetCloseClawV2Command(ClawV2Subsystem clawSubsystem){
        m_clawSubsystem = clawSubsystem;
        addRequirements(m_clawSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        m_clawSubsystem.closeWithLimSwitch();
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return m_clawSubsystem.getLimSwitch();
    }

}
