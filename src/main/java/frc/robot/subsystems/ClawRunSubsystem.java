package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawRunSubsystem extends SubsystemBase{
    public final TalonSRX spinLeft;
    public final TalonSRX spinRight;
    /*public final TalonFXSensorCollection spinLeftEnc;
    public final TalonFXSensorCollection spinRightEnc;*/
    
      public ClawRunSubsystem() {
        spinLeft = new TalonSRX(Constants.clawRunLeftMotorID);
        spinRight = new TalonSRX(Constants.clawRunRightMotorID);
        spinLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        spinRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        
        spinLeft.config_kF(0, Constants.clawRunkF);
        spinLeft.config_kP(0, Constants.clawRunkP);
        spinLeft.config_kI(0, Constants.clawRunkI);
        spinLeft.config_kD(0, Constants.clawRunkD);

        spinRight.config_kF(0, Constants.clawRunkF);
        spinRight.config_kP(0, Constants.clawRunkP);
        spinRight.config_kI(0, Constants.clawRunkI);
        spinRight.config_kD(0, Constants.clawRunkD);

        spinLeft.configAllowableClosedloopError(0, 10, 15);
        spinRight.configAllowableClosedloopError(0, 10, 15);

        spinLeft.configClosedLoopPeakOutput(0, Constants.clawDriveClosedMaxOutput);
        spinLeft.configClosedloopRamp(Constants.clawDriveClosedrampRate);
        spinRight.configClosedLoopPeakOutput(0, Constants.clawDriveClosedMaxOutput);
        spinRight.configClosedloopRamp(Constants.clawDriveClosedrampRate);
      }
      
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Claw Run Left Encoder Position", spinLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("Claw Run Right Encoder Position", spinRight.getSelectedSensorPosition());
    }
    public void spinLeft(){
        spinLeft.set(ControlMode.Position, spinLeft.getSelectedSensorPosition()-200);
        //spinRight.set(ControlMode.PercentOutput, 0);
    }

    public void spinRight(){
        spinLeft.set(ControlMode.Position, spinRight.getSelectedSensorPosition()+200);
        //spinLeft.set(ControlMode.PercentOutput, 0);
    }

    public void spit(){
        spinLeft.set(ControlMode.Position, spinLeft.getSelectedSensorPosition()+200);
        spinLeft.set(ControlMode.Position, spinRight.getSelectedSensorPosition()-200);
    }
    
    public void manualSpin(XboxController controller){
        //todo: need other motor to spin in other direction for spin methods?
        if(controller.getRawButton(Constants.manualClawInButton)){
            spinLeft.set(ControlMode.PercentOutput, -0.25);
            spinRight.set(ControlMode.PercentOutput, 0.25);
        }
    
        if(controller.getRawButton(Constants.manualClawSpitButton)){
            spinLeft.set(ControlMode.PercentOutput, 0.25);
            spinRight.set(ControlMode.PercentOutput, -0.25);
        }
        if(!controller.getRawButton(Constants.manualClawInButton) && !controller.getRawButton(Constants.manualClawSpitButton)){
            spinLeft.set(ControlMode.PercentOutput, 0);
            spinRight.set(ControlMode.PercentOutput, 0);
        }
    }

    public void stopSpin(){
        spinLeft.set(ControlMode.PercentOutput, 0);
        spinRight.set(ControlMode.PercentOutput, 0);
    }

}