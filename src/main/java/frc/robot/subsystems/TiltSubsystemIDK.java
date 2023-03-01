package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TiltSubsystemIDK extends SubsystemBase{
    public final TalonFX tilt;
    public final TalonFXSensorCollection tiltEnc;
    //even need this??? have wrist joint on arm
    
    //init stuff
    public TiltSubsystemIDK(){
        //arm motors/encoders
        tilt = new TalonFX(Constants.armMotorWristJointID);
        tiltEnc = new TalonFXSensorCollection(tilt);

        //config PID
        tilt.config_kF(0, Constants.tiltkF);
        tilt.config_kP(0, Constants.tiltkP);
        tilt.config_kI(0, Constants.tiltkI);
        tilt.config_kD(0, Constants.tiltkD);

        //config max output
        tilt.configClosedLoopPeakOutput(0, Constants.tiltMaxOutput);
        tilt.configClosedloopRamp(Constants.tiltClosedRampRate);
    
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Tilt Encoder Absolute Position", tiltEnc.getIntegratedSensorAbsolutePosition());
        SmartDashboard.putNumber("Tilt Encoder Position", tiltEnc.getIntegratedSensorPosition());
    }
    
    public void resetEncoder(){
        //reset arm encoders (use in loading, low position)
        tiltEnc.setIntegratedSensorPosition(0, 15);
        tiltEnc.setIntegratedSensorPosition(0, 15);
    }

    public void tiltUp(){
        //set to up encoder position
    }

    public void tiltDown(){
        //set to down encoder position
    }

    public void tiltHome(){
        //set to home encoder position
    }

    public void moveManual(XboxController controller){
        //tilt manually
        tilt.set(ControlMode.PercentOutput, Constants.tiltMaxOutput*controller.getRawAxis(Constants.manualWristAxis));
    }
}
