package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawV1RunSubsystem;

public class ManualClawRunCommand extends CommandBase{

    private final ClawV1RunSubsystem m_lobterRunSubsystem;
    private XboxController m_controller;
    
    public ManualClawRunCommand(ClawV1RunSubsystem lobterRunSubsystem, XboxController controller){
        m_lobterRunSubsystem = lobterRunSubsystem;
        m_controller = controller;
        addRequirements(m_lobterRunSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        m_lobterRunSubsystem.manualSpin(m_controller);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }

}
