package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

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

    //init stuff
    public ArmSubystem(){
        //arm motors/encoders
        armMotorShoulderMaster = new TalonFX(Constants.armMotorShoulderMasterID);
        armMotorWristJoint = new TalonFX(Constants.armMotorWristJointID);

        armShoulderMasterEnc = new Encoder(0, 1);
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
        armMotorShoulderMaster.configClosedloopRamp(Constants.armBaseClosedRampRate);
        armMotorWristJoint.configClosedLoopPeakOutput(0, Constants.armWristClosedMaxOutput);
        armMotorWristJoint.configClosedloopRamp(Constants.armWristClosedRampRate);
        
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Base Arm Joint Encoder Absolute Position", armShoulderMasterEnc.get());
        SmartDashboard.putNumber("Wrist Arm Joint Encoder Absolute Position", armWristJointEnc.getIntegratedSensorAbsolutePosition());
        SmartDashboard.putNumber("Base Arm Joint Encoder Position", armShoulderMasterEnc.get());
        SmartDashboard.putNumber("Wrist Arm Joint Encoder Position", armWristJointEnc.getIntegratedSensorPosition());
    }
    
    public void resetEncoders(){
        //reset arm encoders (use in loading, low position)
        //armShoulderMasterEnc.setIntegratedSensorPosition(0, 15);
        //armShoulderSlaveEnc.setIntegratedSensorPosition(0, 15);
        //armWristJointEnc.setIntegratedSensorPosition(0, 15);
    }

    public void moveLow(){
        //set arm to low encoder positions
        //armMotorShoulderMaster.set(ControlMode.Position, Constants.shoulderDownPos);
        armMotorWristJoint.set(ControlMode.Position, Constants.wristDownPos);
    }

    public void moveMid(){
        //setarm to mid encoder positions
        //armMotorShoulderMaster.set(ControlMode.Position, Constants.shoulderMidPos);
        armMotorWristJoint.set(ControlMode.Position, Constants.wristMidPos);
    }

    public void moveHigh(){
        //set arm to high encoder positions
        //armMotorShoulderMaster.set(ControlMode.Position, Constants.shoulderUpPos);
        armMotorWristJoint.set(ControlMode.Position, Constants.wristUpPos);
    }

    public void moveHome(){
        //set arm to 0 encoder positions
        //armMotorShoulderMaster.set(ControlMode.Position, Constants.shoulderHomePos);
        armMotorWristJoint.set(ControlMode.Position, Constants.wristHomePos);
    }


    public void moveManual(XboxController controller){
        //move arm manually
        armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*controller.getRawAxis(Constants.manualShoulderAxis));
        armMotorWristJoint.set(ControlMode.PercentOutput, 0.2*controller.getRawAxis(Constants.manualWristAxis));
    }
}
