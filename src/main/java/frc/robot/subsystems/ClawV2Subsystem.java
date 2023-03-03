package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawV2Subsystem extends SubsystemBase{
    //public final CANSparkMax clawv2flange;
    public final CANSparkMax clawv2claw;
    public final DigitalInput limSwitchClaw;

    
      public ClawV2Subsystem() {
        //clawv2flange = new CANSparkMax(Constants.clawV2flangeMotorID, MotorType.kBrushless);
        clawv2claw = new CANSparkMax(Constants.clawV2clawMotorID, MotorType.kBrushless);
        limSwitchClaw = new DigitalInput(0);
      }
      
    @Override
    public void periodic() {
        /*SmartDashboard.putNumber("Claw Run Left Encoder Position", spinLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("Claw Run Right Encoder Position", spinRight.getSelectedSensorPosition());*/
    }
    
    public void manualSpin(XboxController controller){
        //claw in
        if(controller.getRawAxis(3)>0){
            clawv2claw.set(-0.3);
       }
        //claw out
        if(controller.getRawAxis(2)>0){
            clawv2claw.set(0.3);
        }
        //flange in
        /*if(controller.getRawButton(Constants.manualFlangeInButton)){
            clawv2flange.set(-0.25);
       }
        //flange out
        if(controller.getRawButton(Constants.manualFlangeOutButton)){
            clawv2flange.set(0.25);
        }*/
        //inactive claw
        if(controller.getRawAxis(3)==0 && controller.getRawAxis(2)==0){
            clawv2claw.set(-.07);
        }
        //inactive flange
        /*if(!controller.getRawButton(Constants.manualFlangeInButton) && !controller.getRawButton(Constants.manualFlangeOutButton)){
            clawv2flange.set(0);
        }*/

        //clawv2claw.set(0.6*controller.getRawAxis(Constants.manualClawCloseAxis));

    }
    public void closeWithLimSwitch(){
        if(!limSwitchClaw.get()){
            clawv2claw.set(-Constants.clawContactCurrentValue);
        }
        if(limSwitchClaw.get()){
            clawv2claw.set(0);
        }
    }

    public boolean getLimSwitch(){
        return limSwitchClaw.get();
    }

    public void stop(){
        clawv2claw.set(0);
    }

    public void spit(){
        clawv2claw.set(.35);
    }

}