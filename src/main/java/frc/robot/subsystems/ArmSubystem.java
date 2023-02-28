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
    //public final TalonFXSensorCollection armShoulderEnc;
    public final TalonFXSensorCollection armWristJointEnc;
    public final TalonFXConfiguration shoulderConfig;
    public final PIDController tryingMyGoddamnBestPID;
    public double shoulderError;
    public double allowedShoulderError;
    public double m_shoulderSetpoint;
    private double kP;

    //init stuff
    public ArmSubystem(){
        //arm motors/encoders
        kP = 0.0001;
        shoulderConfig = new TalonFXConfiguration();
        armMotorShoulderMaster = new TalonFX(Constants.armMotorShoulderMasterID);
        armMotorWristJoint = new TalonFX(Constants.armMotorWristJointID);
        tryingMyGoddamnBestPID = new PIDController(0.15, 0, 0);
        m_shoulderSetpoint = 0;
        armShoulderMasterEnc = new Encoder(2, 3);
        shoulderError = m_shoulderSetpoint-armShoulderMasterEnc.get();
        allowedShoulderError = 0.1;

        //yellow in port 3
        //blue in port 2
        //armShoulderEnc = new TalonFXSensorCollection(armMotorShoulderMaster);
        armWristJointEnc = new TalonFXSensorCollection(armMotorWristJoint);
        armMotorShoulderMaster.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0, 0, 15);
       
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
        armMotorShoulderMaster.configClosedloopRamp(Constants.armBaseClosedRampRate);
        armMotorShoulderMaster.configPeakOutputForward(0.3);

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
        if(shoulderError < allowedShoulderError){
            shoulderError = 0;
        }
    }
    
    public void resetEncoders(){
        //reset arm encoders (use in loading, low position)
        armShoulderMasterEnc.reset();
        armWristJointEnc.setIntegratedSensorPosition(0, 15);
        //armMotorShoulderMaster.set(ControlMode.PercentOutput, error*);
    }

    public void moveLow(){
        //set arm to low encoder positions
        //armMotorShoulderMaster.set(ControlMode.Position, Constants.shoulderDownPos);
        //tryingMyGoddamnBestPID.setSetpoint(Constants.shoulderDownPos);
        //armMotorShoulderMaster.set(ControlMode.Position, tryingMyGoddamnBestPID.getSetpoint());
        //armMotorWristJoint.set(ControlMode.Position, Constants.wristDownPos);
        m_shoulderSetpoint = Constants.shoulderDownPos;
        armMotorShoulderMaster.set(ControlMode.PercentOutput, tryingMyGoddamnBestPID.calculate(armShoulderMasterEnc.getDistance(), m_shoulderSetpoint));
    }

    public void moveMid(){
        //setarm to mid encoder positions
       // tryingMyGoddamnBestPID.setSetpoint(Constants.shoulderMidPos);
        //armMotorShoulderMaster.set(ControlMode.Position, tryingMyGoddamnBestPID.getSetpoint());
        //armMotorWristJoint.set(ControlMode.Position, Constants.wristMidPos);
        m_shoulderSetpoint = Constants.shoulderMidPos;
        armMotorShoulderMaster.set(ControlMode.PercentOutput, tryingMyGoddamnBestPID.calculate(armShoulderMasterEnc.getDistance(), m_shoulderSetpoint));
    }

    public void moveHigh(){
        //set arm to high encoder positions
       // tryingMyGoddamnBestPID.setSetpoint(Constants.shoulderUpPos);

        armMotorWristJoint.set(ControlMode.Position, Constants.wristUpPos);
        m_shoulderSetpoint = Constants.shoulderUpPos;
        armMotorShoulderMaster.set(ControlMode.PercentOutput, tryingMyGoddamnBestPID.calculate(armShoulderMasterEnc.getDistance(), m_shoulderSetpoint));
    }

    public void moveHome(){
        //set arm to 0 encoder positions
        //tryingMyGoddamnBestPID.setSetpoint(Constants.shoulderHomePos);

       // armMotorShoulderMaster.set(ControlMode.Position, tryingMyGoddamnBestPID.getSetpoint());
        armMotorWristJoint.set(ControlMode.Position, Constants.wristHomePos);
        m_shoulderSetpoint = Constants.shoulderHomePos;
        armMotorShoulderMaster.set(ControlMode.PercentOutput, tryingMyGoddamnBestPID.calculate(armShoulderMasterEnc.getDistance(), m_shoulderSetpoint));
    }


    public void moveManual(XboxController controller){
        //move arm manually
        armMotorShoulderMaster.set(ControlMode.PercentOutput, -0.2*controller.getRawAxis(Constants.manualShoulderAxis));
        armMotorWristJoint.set(ControlMode.PercentOutput, 0.2*controller.getRawAxis(Constants.manualWristAxis));
    }
}
