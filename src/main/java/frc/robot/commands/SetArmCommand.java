package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubystem;

public class SetArmCommand extends CommandBase{

    private final ArmSubystem m_armSubsystem;
    private final String m_armHeight;
    
    public SetArmCommand(ArmSubystem armSubystem, String armHeight){
        m_armSubsystem = armSubystem;
        m_armHeight = armHeight;
        addRequirements(m_armSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        if(m_armHeight == "high"){
            m_armSubsystem.moveHigh();
        }
        if(m_armHeight == "mid"){
            m_armSubsystem.moveMid();
        }
        if(m_armHeight == "low"){
            m_armSubsystem.moveLow();
        }

        if(m_armHeight == "home"){
            m_armSubsystem.moveHome();
        }

        if(m_armHeight == "reset"){
            m_armSubsystem.resetEncoders();
        }

        if(m_armHeight == "auto high"){
            m_armSubsystem.moveHighAutoCone();
        }
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return true;
    }

}
