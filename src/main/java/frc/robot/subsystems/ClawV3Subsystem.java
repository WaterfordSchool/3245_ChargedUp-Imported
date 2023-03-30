package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawV3Subsystem extends SubsystemBase{
    private final TalonFX clawv3claw;
    
      public ClawV3Subsystem() {
        clawv3claw = new TalonFX(Constants.clawV2clawMotorID);
      }
    @Override
    public void periodic() {}
    
    public void manualSpin(XboxController controller){
        //claw in
        if(controller.getRawAxis(3)>0){
            clawv3claw.set(ControlMode.PercentOutput, Constants.clawRunSpeed);
       }
        //claw out
        if(controller.getRawAxis(2)>0){
            clawv3claw.set(ControlMode.PercentOutput, -Constants.clawRunSpeed);
        }
        
        //inactive claw
        if(controller.getRawAxis(3)==0 && controller.getRawAxis(2)==0){
            clawv3claw.set(ControlMode.PercentOutput, -Constants.clawConstantSpeedVal);
            //swapped polarity
        }

    }

    public void stop(){
        clawv3claw.set(ControlMode.PercentOutput, 0);
    }

    public void spit(){
        clawv3claw.set(ControlMode.PercentOutput, -Constants.clawSpitSpeed);
        //swapped polarity
    }

}