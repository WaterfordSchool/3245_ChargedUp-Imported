package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawCloseSubsystem;

public class SetClawStretchCommand extends CommandBase{

    private final ClawCloseSubsystem m_lobterStretchSubsystem;
    
    public SetClawStretchCommand(ClawCloseSubsystem lobterStretchSubsystem, String size){
        m_lobterStretchSubsystem = lobterStretchSubsystem;
        addRequirements(m_lobterStretchSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        m_lobterStretchSubsystem.limSwitchMode();
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return m_lobterStretchSubsystem.getLimSwitch();
    }

}
