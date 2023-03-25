package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

    private CANSparkMax left1;
    private CANSparkMax left2;
    private CANSparkMax right1;
    private CANSparkMax right2;

    private MotorControllerGroup leftDrive; 
    private MotorControllerGroup rightDrive; 

    private DifferentialDrive dT; 

    private AHRS navx;
    
  /** Creates a new ExampleSubsystem. */
  public DriveTrain() {
    //4 drive neos
    left1 = new CANSparkMax(Constants.driveleft1ID, MotorType.kBrushless);
    left2 = new CANSparkMax(Constants.driveleft2ID, MotorType.kBrushless);
    right1 = new CANSparkMax(Constants.driveright1ID, MotorType.kBrushless);
    right2 = new CANSparkMax(Constants.driveright2ID, MotorType.kBrushless);


    leftDrive = new MotorControllerGroup(left1, left2);
    rightDrive = new MotorControllerGroup(right1, right2);

    dT = new DifferentialDrive(leftDrive, rightDrive);

    navx = new AHRS();
    
     //ramping
     final double t = 0.5;
     left1.setOpenLoopRampRate(t);
     left2.setOpenLoopRampRate(t);
     right1.setOpenLoopRampRate(t);
     right2.setOpenLoopRampRate(t);
 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("pitch", -navx.getPitch() + 1);
    SmartDashboard.putNumber("yaw", navx.getYaw());

  }

  public void drive(XboxController driveController, double kSpeed, double kTurnSpeed) {
    dT.arcadeDrive(driveController.getRawAxis(0) * kTurnSpeed, -driveController.getRawAxis(3) * kSpeed);
    if(driveController.getRawAxis(2) > 0){
    dT.arcadeDrive(driveController.getRawAxis(0) * kTurnSpeed, driveController.getRawAxis(2) * kSpeed);
  }
}

  public void driveSlowAuto(){
    dT.arcadeDrive(0, .3);
  }

  public void driveMidAuto(){
    dT.arcadeDrive(0, .5);

  }
  public void driveAuto(double speed, double rotation){
    dT.arcadeDrive(rotation, speed);
  }

  public void spinjitsu(int direction){
    dT.arcadeDrive(direction*1, 0);
  }
  
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void driveNoController(double kSpeed, double time){
    dT.arcadeDrive(kSpeed, 0);
  }

  public void stop(){
    dT.arcadeDrive(0, 0);
  }
  

  public double getPitch(){
    return -navx.getPitch() + 1;
  }
  public double getYaw(){
    return navx.getYaw();
  }

  public void resetStuff(){
    navx.calibrate();
    navx.reset();
  }
//TODO: gyro drive straight
  public void balance(double pitch, int count){
    SmartDashboard.putNumber("count", count);
    if(pitch<Constants.angleThresh1 && count == 0){
      dT.arcadeDrive(0, -Constants.autoSpeed1);
    }
    if(pitch>Constants.angleThresh1 && count !=2){
      count = 1;
      dT.arcadeDrive(0, -Constants.autoSpeed2);
    }
    if(pitch<Constants.angleThreshStop && count > 0 ){
      count = 2;
      dT.arcadeDrive(0, 0);
    }
  }

  public void closedArcadeDrive(double forwardSpeed, double turnSpeed){
    dT.arcadeDrive(turnSpeed, forwardSpeed);
  }

}