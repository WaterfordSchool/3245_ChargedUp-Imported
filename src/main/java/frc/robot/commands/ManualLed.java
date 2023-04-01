package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LEDSSubsystem;

public class ManualLed extends CommandBase{

    private final LEDSSubsystem m_ledsSubsystem;
    private XboxController m_controller;
    
    public ManualLed(LEDSSubsystem ledsSubsystem, XboxController controller){
        m_ledsSubsystem = ledsSubsystem;
        m_controller = controller;
        addRequirements(m_ledsSubsystem);
    }

    @Override
    public void initialize() {
    }
        
    @Override
    public void execute() {
       m_ledsSubsystem.manualControl(m_controller);
       //m_ledsSubsystem.yellow();
    }
//8 yellow, 7 purple
    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }

}
