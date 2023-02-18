package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class ElevatorSubsystem extends SubsystemBase{
    //falcon
    private final TalonFX elevatorMaster;
    private final TalonFXSensorCollection elevatorMasterEnc;
    private final TalonFX elevatorSlave;
    private final TalonFXSensorCollection elevatorSlaveEnc;
    private final DigitalInput elevatorLimSwitch;

    //init stuff
    public ElevatorSubsystem(){
        elevatorMaster = new TalonFX(Constants.elevatorMotorMasterID);
        elevatorMasterEnc = elevatorMaster.getSensorCollection();
        elevatorSlave = new TalonFX(Constants.elevatorMotorSlaveID);
        elevatorSlaveEnc = elevatorSlave.getSensorCollection();
        elevatorLimSwitch = new DigitalInput(1);
        //config PID
        elevatorMaster.config_kF(0, Constants.elevatorkF);
        elevatorMaster.config_kP(0, Constants.elevatorkF);
        elevatorMaster.config_kI(0, Constants.elevatorkF);
        elevatorMaster.config_kD(0, Constants.elevatorkF);

        
        //set max voltage motor limit
        elevatorMaster.configClosedloopRamp(Constants.elevatorClosedRampRate);
        elevatorMaster.configClosedLoopPeakOutput(0, Constants.elevatorClosedMaxVal);
        elevatorSlave.follow(elevatorMaster);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Elevator Encoder Absolute Position", elevatorMasterEnc.getIntegratedSensorAbsolutePosition());
        SmartDashboard.putNumber("Elevator Encoder Relative Position", elevatorMasterEnc.getIntegratedSensorPosition());
    }
    
    public void resetElevatorEncoder(){
        //set encoder value to 0
        elevatorMasterEnc.setIntegratedSensorPosition(0, 15);
    }

    public void moveDownElevator(){
        //set elevator to down encoder position
        elevatorMaster.set(ControlMode.Position, Constants.elevatorDownPos);
    }

    public void moveUpElevator(){
        //set elevator to up encoder position
        elevatorMaster.set(ControlMode.Position, Constants.elevatorUpPos);
    }

    public void moveManual(XboxController controller){
        //move elevator manually
        elevatorMaster.set(ControlMode.PercentOutput, 0.4*controller.getRawAxis(Constants.manualElevatorAxis));
    }

    public boolean getLimSwitch(){
        return elevatorLimSwitch.get();
    }

    public void stopElevator(){
        elevatorMaster.set(ControlMode.PercentOutput, 0);
    }

}
