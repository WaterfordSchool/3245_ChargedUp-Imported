package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubystem extends SubsystemBase{
    public final TalonFX armMotorShoulderMaster;
    public final TalonFX armMotorWristJoint;

    public final Encoder armShoulderMasterEnc;
    public final TalonFXSensorCollection armWristJointEnc;
    public final PIDController shoulderPID;
    public double m_shoulderSetpoint;

    //init stuff
    public ArmSubystem(){
        //arm motors/encoders
        armMotorShoulderMaster = new TalonFX(Constants.armMotorShoulderMasterID);
        armMotorWristJoint = new TalonFX(Constants.armMotorWristJointID);
        shoulderPID = new PIDController(Constants.armBasekP, Constants.armBasekI, Constants.armBasekD);
        m_shoulderSetpoint = 0;
        armShoulderMasterEnc = new Encoder(2, 3);

        //yellow in port 3
        //blue in port 2
        //armShoulderEnc = new TalonFXSensorCollection(armMotorShoulderMaster);
        armWristJointEnc = new TalonFXSensorCollection(armMotorWristJoint);
        
        //config PID
        armMotorShoulderMaster.config_kF(0, Constants.armBasekF);
        armMotorShoulderMaster.config_kP(0, Constants.armBasekP);
        armMotorShoulderMaster.config_kI(0, Constants.armBasekI);
        armMotorShoulderMaster.config_kD(0, Constants.armBasekD);

        armMotorWristJoint.config_kF(0, Constants.armWristkF);
        armMotorWristJoint.config_kP(0, Constants.armWristkP);
        armMotorWristJoint.config_kI(0, Constants.armWristkI);
        armMotorWristJoint.config_kD(0, Constants.armWristkD);

        //config max output
        armMotorShoulderMaster.configClosedLoopPeakOutput(0, Constants.armBaseClosedMaxOutput);
        armMotorShoulderMaster.configPeakOutputForward(0.05);
        armMotorShoulderMaster.configPeakOutputReverse(-0.05);
        armMotorShoulderMaster.configOpenloopRamp(0.7);
        armMotorShoulderMaster.configClosedloopRamp(Constants.armBaseClosedRampRate);

        armMotorWristJoint.configClosedLoopPeakOutput(0, Constants.armWristClosedMaxOutput);
        armMotorWristJoint.configClosedloopRamp(Constants.armWristClosedRampRate);
    }

    @Override
    public void periodic() {
        //SmartDashboard.putNumber("Shoulder Arm Joint Encoder Absolute Position", armShoulderMasterEnc.get());
        SmartDashboard.putNumber("Wrist Arm Joint Encoder Absolute Position", armWristJointEnc.getIntegratedSensorAbsolutePosition());
        SmartDashboard.putNumber("Shoulder Arm Joint Encoder Position", armShoulderMasterEnc.get());
        SmartDashboard.putNumber("Wrist Arm Joint Encoder Position", armWristJointEnc.getIntegratedSensorPosition());
        SmartDashboard.putNumber("whatever the shoulder is reading", armMotorShoulderMaster.getSelectedSensorPosition());
    }
    
    public void resetEncoders(){
        //reset arm encoders (use in loading, low position)
        armShoulderMasterEnc.reset();
        armWristJointEnc.setIntegratedSensorPosition(0, 15);
    }

    public void moveLow(){
        //set arm to low encoder positions
        //armMotorWristJoint.set(ControlMode.Position, Constants.wristDownPos);
        m_shoulderSetpoint = Constants.shoulderDownPos;
        armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*shoulderPID.calculate(armShoulderMasterEnc.getDistance(), m_shoulderSetpoint));
    }

    public void moveMid(){
        //setarm to mid encoder positions
        //armMotorWristJoint.set(ControlMode.Position, Constants.wristMidPos);
        m_shoulderSetpoint = Constants.shoulderMidPos;
        armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*shoulderPID.calculate(armShoulderMasterEnc.getDistance(), m_shoulderSetpoint));
    }

    public void moveHigh(){
        //set arm to high encoder positions
        //armMotorWristJoint.set(ControlMode.Position, Constants.wristUpPos);
        m_shoulderSetpoint = Constants.shoulderUpPos;
        armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*shoulderPID.calculate(armShoulderMasterEnc.getDistance(), m_shoulderSetpoint));
    }

    public void moveHome(){
        //set arm to 0 encoder positions
        //tryingMyGoddamnBestPID.setSetpoint(Constants.shoulderHomePos);

        //armMotorShoulderMaster.set(ControlMode.Position, tryingMyGoddamnBestPID.getSetpoint());
        //armMotorWristJoint.set(ControlMode.Position, Constants.wristHomePos);
        m_shoulderSetpoint = Constants.shoulderHomePos;
        armMotorShoulderMaster.set(ControlMode.PercentOutput, shoulderPID.calculate(armShoulderMasterEnc.getDistance(), m_shoulderSetpoint));
    }


    public void moveManual(XboxController controller){
        //move arm manually
        armMotorShoulderMaster.set(ControlMode.PercentOutput, -0.2*controller.getRawAxis(Constants.manualShoulderAxis));
        armMotorWristJoint.set(ControlMode.PercentOutput, 0.2*controller.getRawAxis(Constants.manualWristAxis));
    }
}
