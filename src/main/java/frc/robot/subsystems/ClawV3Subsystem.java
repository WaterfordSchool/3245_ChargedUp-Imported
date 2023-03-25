package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawV3Subsystem extends SubsystemBase{
    private final TalonFX clawv3claw;
    
      public ClawV3Subsystem() {
        //clawv2flange = new CANSparkMax(Constants.clawV2flangeMotorID, MotorType.kBrushless);
        clawv3claw = new TalonFX(Constants.clawV2clawMotorID);
      }
      //TODO command
    @Override
    public void periodic() {
        /*SmartDashboard.putNumber("Claw Run Left Encoder Position", spinLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("Claw Run Right Encoder Position", spinRight.getSelectedSensorPosition());*/
    }
    
    public void manualSpin(XboxController controller){
        //claw in
        if(controller.getRawAxis(3)>0){
            clawv3claw.set(ControlMode.PercentOutput, -0.4);
       }
        //claw out
        if(controller.getRawAxis(2)>0){
            clawv3claw.set(ControlMode.PercentOutput, 0.45);
        }
        
        //inactive claw
        if(controller.getRawAxis(3)==0 && controller.getRawAxis(2)==0){
            clawv3claw.set(ControlMode.PercentOutput, Constants.clawConstantSpeedVal);
        }

    }

    public void stop(){
        clawv3claw.set(ControlMode.PercentOutput, 0);
    }

    public void spit(){
        clawv3claw.set(ControlMode.PercentOutput, .35);
    }

}