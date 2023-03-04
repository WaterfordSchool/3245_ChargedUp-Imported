package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristSubsystem extends SubsystemBase{
    public final TalonFX wrist;
    public final TalonFXSensorCollection wristEnc;
    public boolean axisEngaged;
    //even need this??? have wrist joint on arm
    
    //init stuff
    public WristSubsystem(){
        //arm motors/encoders
        wrist = new TalonFX(Constants.armMotorWristJointID);
        wristEnc = new TalonFXSensorCollection(wrist);
        boolean axisEngaged = false; 

        //config PID
        wrist.config_kF(0, Constants.armWristkF);
        wrist.config_kP(0, Constants.armWristkP);
        wrist.config_kI(0, Constants.armWristkI);
        wrist.config_kD(0, Constants.armWristkD);

        //config max output
        wrist.configClosedLoopPeakOutput(0, Constants.armWristClosedMaxOutput);
        wrist.configClosedloopRamp(Constants.armWristClosedRampRate);
    
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("wrist Encoder Absolute Position", wristEnc.getIntegratedSensorAbsolutePosition());
        SmartDashboard.putNumber("wrist Encoder Position", wristEnc.getIntegratedSensorPosition());
       // wrist.set(ControlMode.PercentOutput, 0.03);
    }
    
    public void resetEncoder(){
        //reset arm encoders (use in loading, low position)
        wristEnc.setIntegratedSensorPosition(0, 15);
        wristEnc.setIntegratedSensorPosition(0, 15);
    }

    public void wristUp(){
        //set to up encoder position
        wrist.set(ControlMode.Position, Constants.wristUpPos);
    }

    public void wristMid(){
        wrist.set(ControlMode.Position, Constants.wristMidPos);
    }

    public void wristDown(){
        //set to down encoder position
        wrist.set(ControlMode.Position, Constants.wristDownPos);

    }

    public void wristHome(){
        //set to home encoder position
        wrist.set(ControlMode.Position, Constants.wristHomePos);
    }

    public void stay(double posToStayAt){
        wrist.set(ControlMode.Position, posToStayAt);
    }
    
    public boolean isAxisEngaged(XboxController controller){
        return controller.getRawAxis(Constants.manualTiltAxis) != 0;
    }

    public double getCurrentPosition(){
        return wristEnc.getIntegratedSensorPosition();
    }

    public void moveManual(XboxController controller){
        //wrist manually
        //boolean axisengaged controller.getrawaxis == 0;
        //false = 1
        //false * 0.1
            wrist.set(ControlMode.PercentOutput, Constants.tiltMaxOutput*controller.getRawAxis(Constants.manualWristAxis));
        
    }
}
