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
        shoulderPID = new PIDController(Constants.armShoulderkP, Constants.armShoulderkI, Constants.armShoulderkD);
        shoulderIntegratedEnc = new TalonFXSensorCollection(armMotorShoulderMaster);
        encoderDistance = 0;
        encoderSetPoint = 0;

        //pid
        armMotorShoulderMaster.config_kF(0, Constants.armShoulderkF);
        armMotorShoulderMaster.config_kP(0, Constants.armShoulderkP);
        armMotorShoulderMaster.config_kI(0, Constants.armShoulderkI);
        armMotorShoulderMaster.config_kD(0, Constants.armShoulderkD);

        //config max output
        armMotorShoulderMaster.configClosedLoopPeakOutput(0, Constants.armShoulderClosedMaxOutput);
        armMotorShoulderMaster.configPeakOutputForward(Constants.armShoulderPeakOutputFor);
        armMotorShoulderMaster.configPeakOutputReverse(-Constants.armShoulderPeakOutputFor);
        armMotorShoulderSlave.configClosedLoopPeakOutput(0, Constants.armShoulderClosedMaxOutput);
        armMotorShoulderSlave.configPeakOutputForward(Constants.armShoulderPeakOutputFor);
        armMotorShoulderSlave.configPeakOutputReverse(-Constants.armShoulderPeakOutputFor);
        //armMotorShoulderMaster.configOpenloopRamp(0.7);
        //armMotorShoulderMaster.configClosedloopRamp(Constants.armShoulderClosedRampRate);

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

    public void moveHighAutoCone(){
        armMotorShoulderMaster.set(ControlMode.Position, -Constants.shoulderUpPos);
    }

    public void moveManual(XboxController controller){
        //move arm manually
        armMotorShoulderMaster.set(ControlMode.PercentOutput, 0.2*controller.getRawAxis(Constants.manualShoulderAxis));
        armMotorShoulderSlave.follow(armMotorShoulderMaster);
    }
}