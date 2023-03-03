package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;

public class SetTiltCommand extends CommandBase{

    private final WristSubsystem m_tiltSubsystem;
    private final String m_direction;
    
    public SetTiltCommand(WristSubsystem tiltSubystem, String direction){
        m_tiltSubsystem = tiltSubystem;
        m_direction = direction;
        addRequirements(m_tiltSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        if(m_direction == "high"){
            m_tiltSubsystem.wristUp();
        }
        if(m_direction == "mid"){
            m_tiltSubsystem.wristMid();
        }
        if(m_direction == "low"){
            m_tiltSubsystem.wristDown();;
        }
        if(m_direction == "home"){
            m_tiltSubsystem.wristHome();
        }
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return true;
    }

}
