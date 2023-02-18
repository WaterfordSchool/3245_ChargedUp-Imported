package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class SetElevatorCommand extends CommandBase{

    private final ElevatorSubsystem m_elevatorSubsystem;
    private final String m_height;
    
    public SetElevatorCommand(ElevatorSubsystem elevatorSubsystem, String height){
        m_elevatorSubsystem = elevatorSubsystem;
        m_height = height;
        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        if(m_height == "up"){
            m_elevatorSubsystem.moveUpElevator();
        }
        if(m_height == "down"){
            m_elevatorSubsystem.moveDownElevator();
        }
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return true;
    }

}
