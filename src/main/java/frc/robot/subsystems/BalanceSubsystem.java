package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BalanceSubsystem extends SubsystemBase{
    ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    //motors

    //init stuff
    public BalanceSubsystem(){}

    @Override
    public void periodic() {

        //make motor?
        //config gyro
        //config PID
        //config sensor phase
        //set max voltage drive motor limit
    }
    
    public double getGyro(){
        return gyro.getAngle();
    }
    public void reset(){
        gyro.reset();
    }
    public void calibrate(){
        gyro.calibrate();
    }
    public boolean onTarget(){
        return gyro.getAngle()>0;
    }
    //not entirely sure yet
}