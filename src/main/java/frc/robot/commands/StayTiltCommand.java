package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WristSubsystem;

public class StayTiltCommand extends CommandBase{

    private final WristSubsystem m_tiltSubsystem;
    private double m_posToStayAt;
    private final XboxController m_controller;
    
    public StayTiltCommand(WristSubsystem tiltSubystem, XboxController controller){
        m_tiltSubsystem = tiltSubystem;
        m_controller = controller;
        addRequirements(m_tiltSubsystem);
    }

    @Override
    public void initialize() {
        m_posToStayAt = m_tiltSubsystem.getCurrentPosition();
        //get position when scheduler calls command
    }
        
    @Override
    public void execute() {
        m_tiltSubsystem.stay(m_posToStayAt);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        //return finished for engaged right joystick
        return m_tiltSubsystem.isAxisEngaged(m_controller);
    }
}
