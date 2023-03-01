package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClawV2Subsystem;

public class SetSpitClawV2Command extends CommandBase{

    private final ClawV2Subsystem m_clawSubsystem;
    private final double m_durationSecs;
    Timer timer = new Timer();
    
    public SetSpitClawV2Command(ClawV2Subsystem clawSubsystem, double durationSecs){
        m_clawSubsystem = clawSubsystem;
        m_durationSecs = durationSecs;
        addRequirements(m_clawSubsystem);
    }
    
    @Override
    public void initialize() {
        timer.start();
        timer.reset();
    }
        
    @Override
    public void execute() {
        m_clawSubsystem.spit();
    }

    @Override
    public void end(boolean interrupted){
        m_clawSubsystem.stop();
    }

    @Override
    public boolean isFinished(){
        return timer.get()>m_durationSecs;
    }

}
