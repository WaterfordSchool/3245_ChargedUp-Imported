package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawV1RunSubsystem;
import frc.robot.subsystems.ClawV1CloseSubsystem;

public class SetClawSpitCommand extends CommandBase{

    private final ClawV1RunSubsystem m_lobterRunSubsystem;
    private final ClawV1CloseSubsystem m_lobterStretchSubsystem;
    
    public SetClawSpitCommand(ClawV1RunSubsystem lobterRunSubsystem, ClawV1CloseSubsystem lobterStretchSubsystem){
        m_lobterRunSubsystem = lobterRunSubsystem;
        m_lobterStretchSubsystem = lobterStretchSubsystem;
        addRequirements(m_lobterRunSubsystem);
        addRequirements(m_lobterStretchSubsystem);
    }

    @Override
    public void initialize() {}
        
    @Override
    public void execute() {
        m_lobterRunSubsystem.spit();
        m_lobterStretchSubsystem.drop();
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return true;
    }

}
