package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClawV1CloseSubsystem extends SubsystemBase{
    public final CANSparkMax clawCloseOpenMotor;
    public final SparkMaxPIDController clawCloseOpenPID;
    public final RelativeEncoder clawcloseOpenEnc;
    public final DigitalInput limSwitch;
    /*TODO
    track encoder values in time based, tune appropriately
    tune PID
    think about motion magic
    */

    //motors

    //init stuff
    public ClawV1CloseSubsystem(){
        //claw motor/encoder
        clawCloseOpenMotor = new CANSparkMax(Constants.clawCloseMotorID, MotorType.kBrushless);
        clawcloseOpenEnc = clawCloseOpenMotor.getEncoder();
        clawCloseOpenPID = clawCloseOpenMotor.getPIDController();
        
        //config PID
        clawCloseOpenPID.setFF(Constants.clawClosekF);
        clawCloseOpenPID.setP(Constants.clawClosekP);
        clawCloseOpenPID.setI(Constants.clawClosekI);
        clawCloseOpenPID.setD(Constants.clawClosekD);

        //config max output and ramping
        clawCloseOpenMotor.setClosedLoopRampRate(Constants.clawCloseClosedRampRate);
        clawCloseOpenPID.setOutputRange(-Constants.clawCloseMaxVal, Constants.clawCloseMaxVal);

        limSwitch = new DigitalInput(0);
    }

    @Override
    public void periodic() {
        //put encoder positions
        
        //put current value
        
    }
    
    public void resetArmEncoder(){
        //reset arm encoders (use in loading, low position)
        clawcloseOpenEnc.setPosition(0);
    }

    public void setCone(){
        //set arm to low encoder positions
    }

    public void setCube(){
        //setarm to mid encoder positions
    }

    public void moveManual(XboxController controller){
        //move arm manually
        clawCloseOpenMotor.set(Constants.clawCloseMaxVal*controller.getRawAxis(3));
    }

    public void drop(){
        //set arms to open position
    }

    public double getCurrentOutput(){
        return clawCloseOpenMotor.getOutputCurrent();
    }

    public void limSwitchMode(){}

    public boolean getLimSwitch(){
        return limSwitch.get();
    }
}
