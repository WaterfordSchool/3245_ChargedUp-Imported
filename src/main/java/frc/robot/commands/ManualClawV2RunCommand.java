package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawV2Subsystem;

public class ManualClawV2RunCommand extends CommandBase{

    private final ClawV2Subsystem m_clawSubsystem;
    private XboxController m_controller;
    
    public ManualClawV2RunCommand(ClawV2Subsystem clawSubsystem, XboxController controller){
        m_clawSubsystem = clawSubsystem;
        m_controller = controller;
        addRequirements(m_clawSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        m_clawSubsystem.manualSpin(m_controller);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }

}
