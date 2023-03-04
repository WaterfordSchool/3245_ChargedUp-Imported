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
    //public final TalonFX armMotorWristJoint;

    //public final Encoder armShoulderMasterEnc;
    //public final TalonFXSensorCollection armWristJointEnc;
    public final TalonFXSensorCollection shoulderIntegratedEnc;
    public final PIDController shoulderPID;
    public double encoderDistance;
    public double encoderSetPoint;

    //init stuff
    public ArmSubystem(){
        //arm motors/encoders
        armMotorShoulderMaster = new TalonFX(Constants.armMotorShoulderMasterID);
        //armMotorWristJoint = new TalonFX(Constants.armMotorWristJointID);
        shoulderPID = new PIDController(Constants.armBasekP, Constants.armBasekI, Constants.armBasekD);
        //armShoulderMasterEnc = new Encoder(2, 3);
        shoulderIntegratedEnc = new TalonFXSensorCollection(armMotorShoulderMaster);
        encoderDistance = 0;
        encoderSetPoint = 0;

        //yellow in port 3
        //blue in port 2
        //armShoulderEnc = new TalonFXSensorCollection(armMotorShoulderMaster);
        //armWristJointEnc = new TalonFXSensorCollection(armMotorWristJoint);

        //pid
        /*armMotorWristJoint.config_kF(0, Constants.armWristkF);
        armMotorWristJoint.config_kP(0, Constants.armWristkP);
        armMotorWristJoint.config_kI(0, Constants.armWristkI);
        armMotorWristJoint.config_kD(0, Constants.armWristkD);*/

        armMotorShoulderMaster.config_kF(0, 0);
        armMotorShoulderMaster.config_kP(0, 0.15);
        armMotorShoulderMaster.config_kI(0, 0);
        armMotorShoulderMaster.config_kD(0, 0);

        //config max output
        armMotorShoulderMaster.configClosedLoopPeakOutput(0, Constants.armBaseClosedMaxOutput);
        armMotorShoulderMaster.configPeakOutputForward(0.25);
        armMotorShoulderMaster.configPeakOutputReverse(-0.25);
        //armMotorShoulderMaster.configOpenloopRamp(0.7);
        //armMotorShoulderMaster.configClosedloopRamp(Constants.armBaseClosedRampRate);

        armMotorShoulderMaster.setInverted(true);
        //armMotorWristJoint.configClosedLoopPeakOutput(0, Constants.armWristClosedMaxOutput);
        //armMotorWristJoint.configClosedloopRamp(Constants.armWristClosedRampRate);
    }

    @Override
    public void periodic() {
        //SmartDashboard.putNumber("Shoulder Arm Joint Encoder Absolute Position", armShoulderMasterEnc.get());
        //SmartDashboard.putNumber("Wrist Arm Joint Encoder Absolute Position", armWristJointEnc.getIntegratedSensorAbsolutePosition());
        //SmartDashboard.putNumber("Shoulder Arm Joint Encoder Position", armShoulderMasterEnc.get());
        SmartDashboard.putNumber("Shoulder integrated encoder value", shoulderIntegratedEnc.getIntegratedSensorPosition());
        //SmartDashboard.putNumber("Wrist Arm Joint Encoder Position", armWristJointEnc.getIntegratedSensorPosition());
        //encoderDistance = shoulderPID.calculate(armShoulderMasterEnc.getDistance(), encoderSetPoint);
    }
    
    public void resetEncoders(){
        //reset arm encoders (use in loading, low position)
        //armShoulderMasterEnc.reset();
        shoulderIntegratedEnc.setIntegratedSensorPosition(0, 25);
        //armWristJointEnc.setIntegratedSensorPosition(0, 25);
       // armWristJointEnc.setIntegratedSensorPosition(0, 15);
    }

    public void moveLow(){
        //set arm to low encoder positions
       //armMotorWristJoint.set(ControlMode.Position, Constants.wristDownPos);
       encoderSetPoint = Constants.shoulderDownPos;
       armMotorShoulderMaster.set(ControlMode.Position, -Constants.shoulderDownPos);
       //armMotorShoulderMaster.set(ControlMode.PercentOutput, encoderDistance);
       // armMotorShoulderMaster.set(ControlMode.PercentOutput, shoulderPID.calculate(armShoulderMasterEnc.getDistance(), Constants.shoulderDownPos));

        //armMotorShoulderMaster.set(ControlMode.Position, -16000);
       /* if(controllerOverride.getRawAxis(Constants.manualShoulderAxis)!=0){
            armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*controllerOverride.getRawAxis(Constants.manualShoulderAxis));
        }
        if(controllerOverride.getRawAxis(Constants.manualShoulderAxis)==0){
            armMotorShoulderMaster.set(ControlMode.Position, -16000);
        }
        if(controllerOverride.getRawAxis(Constants.manualWristAxis)!=0){
            armMotorWristJoint.set(ControlMode.PercentOutput, 0.2*controllerOverride.getRawAxis(Constants.manualWristAxis));
        }
        if(controllerOverride.getRawAxis(Constants.manualWristAxis)==0){
            armMotorWristJoint.set(ControlMode.Position, Constants.wristDownPos);
        }*/

    }

    public void moveMid(){
        //setarm to mid encoder positions
        //armMotorWristJoint.set(ControlMode.Position, Constants.wristMidPos);

        //armMotorShoulderMaster.set(ControlMode.PercentOutput, shoulderPID.calculate(armShoulderMasterEnc.getDistance(), Constants.shoulderMidPos));
        armMotorShoulderMaster.set(ControlMode.Position, -Constants.shoulderMidPos);

        /*if(controllerOverride.getRawAxis(Constants.manualShoulderAxis)!=0){
            armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*controllerOverride.getRawAxis(Constants.manualShoulderAxis));
        }
        if(controllerOverride.getRawAxis(Constants.manualShoulderAxis)==0){
            armMotorShoulderMaster.set(ControlMode.Position, -36000);
        }
        if(controllerOverride.getRawAxis(Constants.manualWristAxis)!=0){
            armMotorWristJoint.set(ControlMode.PercentOutput, 0.2*controllerOverride.getRawAxis(Constants.manualWristAxis));
        }
        if(controllerOverride.getRawAxis(Constants.manualWristAxis)==0){
            armMotorWristJoint.set(ControlMode.Position, Constants.wristMidPos);
        }*/

    }

    public void moveHigh(){
        //set arm to high encoder positions
        //armMotorWristJoint.set(ControlMode.Position, Constants.wristUpPos);
        
        //armMotorShoulderMaster.set(ControlMode.PercentOutput, shoulderPID.calculate(armShoulderMasterEnc.getDistance(), Constants.shoulderUpPos));
        //trying something new
        armMotorShoulderMaster.set(ControlMode.Position, -Constants.shoulderUpPos);
        /*if(controllerOverride.getRawAxis(Constants.manualShoulderAxis)!=0){
            armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*controllerOverride.getRawAxis(Constants.manualShoulderAxis));
        }
        if(controllerOverride.getRawAxis(Constants.manualShoulderAxis)==0){
            armMotorShoulderMaster.set(ControlMode.Position, -45000);
        }
        if(controllerOverride.getRawAxis(Constants.manualWristAxis)!=0){
            armMotorWristJoint.set(ControlMode.PercentOutput, 0.2*controllerOverride.getRawAxis(Constants.manualWristAxis));
        }
        if(controllerOverride.getRawAxis(Constants.manualWristAxis)==0){
            armMotorWristJoint.set(ControlMode.Position, Constants.wristUpPos);
        }*/

    }

    public void moveHome(){
        //set arm to 0 encoder positions
        //armMotorWristJoint.set(ControlMode.Position, Constants.wristHomePos);
       
        //armMotorShoulderMaster.set(ControlMode.PercentOutput, shoulderPID.calculate(armShoulderMasterEnc.getDistance(), Constants.shoulderHomePos));
        armMotorShoulderMaster.set(ControlMode.Position, 0);

        //trying something new
        /*if(controllerOverride.getRawAxis(Constants.manualShoulderAxis)!=0){
            armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*controllerOverride.getRawAxis(Constants.manualShoulderAxis));
        }
        if(controllerOverride.getRawAxis(Constants.manualShoulderAxis)==0){
            armMotorShoulderMaster.set(ControlMode.Position, 0);
        }
        if(controllerOverride.getRawAxis(Constants.manualWristAxis)!=0){
            armMotorWristJoint.set(ControlMode.PercentOutput, 0.2*controllerOverride.getRawAxis(Constants.manualWristAxis));
        }
        if(controllerOverride.getRawAxis(Constants.manualWristAxis)==0){
            armMotorWristJoint.set(ControlMode.Position, Constants.wristHomePos);
        }*/

    }


    public void moveManual(XboxController controller){
        //move arm manually
        //armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*controller.getRawAxis(Constants.manualShoulderAxis));
        //armMotorWristJoint.set(ControlMode.PercentOutput, 0.2*controller.getRawAxis(Constants.manualWristAxis));
    }
}
