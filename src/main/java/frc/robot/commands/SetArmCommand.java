package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubystem;

public class SetArmCommand extends CommandBase{

    private final ArmSubystem m_armSubsystem;
    private final String m_armHeight;
    private final XboxController m_controllerOverride;
    
    public SetArmCommand(ArmSubystem armSubystem, String armHeight, XboxController controllerOverride){
        m_armSubsystem = armSubystem;
        m_armHeight = armHeight;
        m_controllerOverride = controllerOverride;
        addRequirements(m_armSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        if(m_armHeight == "high"){
            m_armSubsystem.moveHigh(m_controllerOverride);
        }
        if(m_armHeight == "mid"){
            m_armSubsystem.moveMid(m_controllerOverride);
        }
        if(m_armHeight == "low"){
            m_armSubsystem.moveLow(m_controllerOverride);
        }

        if(m_armHeight == "home"){
            m_armSubsystem.moveHome(m_controllerOverride);
        }

        if(m_armHeight == "reset"){
            m_armSubsystem.resetEncoders();
        }
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return true;
    }

}
