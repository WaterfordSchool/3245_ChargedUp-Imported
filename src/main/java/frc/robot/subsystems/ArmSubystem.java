package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubystem extends SubsystemBase{
    public final TalonFX armMotorShoulderMaster;
    public final TalonFX armMotorShoulderSlave;

    public final TalonFXSensorCollection shoulderIntegratedEnc;
    public final PIDController shoulderPID;
    public double encoderDistance;
    public double encoderSetPoint;

    //init stuff
    public ArmSubystem(){
        //arm motors/encoders
        armMotorShoulderMaster = new TalonFX(Constants.armMotorShoulderMasterID);
        armMotorShoulderSlave = new TalonFX(Constants.armMotorShoulderSlaveID);
        shoulderPID = new PIDController(Constants.armBasekP, Constants.armBasekI, Constants.armBasekD);
        shoulderIntegratedEnc = new TalonFXSensorCollection(armMotorShoulderMaster);
        encoderDistance = 0;
        encoderSetPoint = 0;

        //yellow in port 3
        //blue in port 2
        //armShoulderEnc = new TalonFXSensorCollection(armMotorShoulderMaster);

        //pid
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
        armMotorShoulderSlave.setInverted(true);
        armMotorShoulderSlave.follow(armMotorShoulderMaster);
    }

    @Override
    public void periodic() {
        //SmartDashboard.putNumber("Shoulder Arm Joint Encoder Absolute Position", armShoulderMasterEnc.get());
        //SmartDashboard.putNumber("Shoulder Arm Joint Encoder Position", armShoulderMasterEnc.get());
        SmartDashboard.putNumber("Shoulder integrated encoder value", shoulderIntegratedEnc.getIntegratedSensorPosition());
    }
    
    public void resetEncoders(){
        //reset arm encoders (use in loading, low position)
        //armShoulderMasterEnc.reset();
        shoulderIntegratedEnc.setIntegratedSensorPosition(0, 25);
    }

    public void moveLow(){
        //set arm to low encoder positions
       armMotorShoulderMaster.set(ControlMode.Position, -Constants.shoulderDownPos);
    }

    public void moveMid(){
        //setarm to mid encoder positions
        armMotorShoulderMaster.set(ControlMode.Position, -Constants.shoulderMidPos);
    }

    public void moveHigh(){
        //set arm to high encoder positions
        armMotorShoulderMaster.set(ControlMode.Position, -Constants.shoulderUpPos);  
    }

    public void moveHome(){
        //set arm to 0 encoder positions
        armMotorShoulderMaster.set(ControlMode.Position, 0);
    }


    public void moveManual(XboxController controller){
        //move arm manually
        //armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*controller.getRawAxis(Constants.manualShoulderAxis));
    }
}
