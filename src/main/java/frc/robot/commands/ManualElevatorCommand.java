package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ElevatorSubsystem;

public class ManualElevatorCommand extends CommandBase{

    private final ElevatorSubsystem m_elevatorSubsystem;
    private XboxController m_controller;
    
    public ManualElevatorCommand(ElevatorSubsystem elevatorSubsystem, XboxController controller){
        m_elevatorSubsystem = elevatorSubsystem;
        m_controller = controller;
        addRequirements(m_elevatorSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
        m_elevatorSubsystem.moveManual(m_controller);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }

}
