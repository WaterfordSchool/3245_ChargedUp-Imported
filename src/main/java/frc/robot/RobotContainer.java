// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ManualClawV3RunCommand;
import frc.robot.commands.ManualLed;
import frc.robot.commands.ManualTiltCommand;
import frc.robot.commands.SetArmCommand;
import frc.robot.commands.Spinjitsu;
import frc.robot.commands.auto.AutoBalanceCommandGroup;
import frc.robot.commands.auto.AutoDrive;
import frc.robot.commands.auto.AutoFunny;
import frc.robot.commands.auto.AutoScoreHighLeave;
import frc.robot.commands.auto.AutoScoreLowLeave;
import frc.robot.commands.auto.BalancePIDCommand;
import frc.robot.subsystems.ArmSubystem;
import frc.robot.subsystems.ClawV3Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LEDSSubsystem;
import frc.robot.subsystems.WristSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class RobotContainer {

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  XboxController driver = new XboxController(0);
  XboxController operator = new XboxController(1);

  //subsystems
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final ClawV3Subsystem m_clawV3Subsystem = new ClawV3Subsystem();
  private final ArmSubystem m_armSubsystem = new ArmSubystem();
  private final WristSubsystem m_wristSubsystem = new WristSubsystem();
  private final LEDSSubsystem m_ledsSubsystem = new LEDSSubsystem();
  //commands
  //auto command
  //private final Command m_autoNoBalance = new SequentialCommandGroup(/*new SetArmCommand(m_armSubsystem, "high", operator), new SetTiltCommand(m_wristSubsystem, "high"), new SetSpitClawV2Command(m_clawV2Subsystem, 1), new SetTiltCommand(m_wristSubsystem, "home"), new SetTiltCommand(m_wristSubsystem, "low"), new SetSpitClawV2Command(m_clawV2Subsystem, 1),*/ new AutoDrive(m_driveTrain, 3.8, 0.5, 0));
  private final AutoDrive m_autoNoBalance = new AutoDrive(m_driveTrain, 3.8, 0.5, 0);
  private final BalancePIDCommand m_pidBalanceNoDrive = new BalancePIDCommand(m_driveTrain);
  private final AutoScoreHighLeave m_autoScoreHighLeave = new AutoScoreHighLeave(m_driveTrain, m_armSubsystem, m_wristSubsystem, m_clawV3Subsystem);
  private final AutoScoreLowLeave m_autoScoreLowLeave = new AutoScoreLowLeave(m_driveTrain, m_armSubsystem, m_wristSubsystem, m_clawV3Subsystem);
  //private final AutoFunny m_autoFunny = new AutoFunny(m_driveTrain, m_armSubsystem, m_wristSubsystem, m_clawV3Subsystem);

  //drive commands
  private final ArcadeDrive m_fastDrive = new ArcadeDrive(m_driveTrain, Constants.fastDriveSpeed, Constants.fastTurnSpeed, driver);
  private final ArcadeDrive m_slowDrive = new ArcadeDrive(m_driveTrain, Constants.slowDriveSpeed, Constants.slowTurnSpeed, driver);
  private final ArcadeDrive m_arcadeDefault = new ArcadeDrive(m_driveTrain, Constants.regDriveSpeed, Constants.regTurnSpeed, driver);
  private final Spinjitsu m_spinjitsu1 = new Spinjitsu(m_driveTrain, 1, driver);

  private final AutoBalanceCommandGroup m_pidbalance = new AutoBalanceCommandGroup(m_driveTrain);
  public RobotContainer() {
    //default commands
    m_driveTrain.setDefaultCommand(m_arcadeDefault);
    m_clawV3Subsystem.setDefaultCommand(new ManualClawV3RunCommand(m_clawV3Subsystem, operator));
    m_wristSubsystem.setDefaultCommand(new ManualTiltCommand(m_wristSubsystem, operator));
    m_ledsSubsystem.setDefaultCommand(new ManualLed(m_ledsSubsystem, operator));
    
    //choosable auto
    m_chooser.setDefaultOption("leave, no balance, no score", m_autoNoBalance);
    m_chooser.addOption("drive forward, balance gyro [FACING AWAY FROM YOU]", m_pidbalance);
    m_chooser.addOption("score high and leave, no balance [FACING TOWARDS YOU]", m_autoScoreHighLeave);
    m_chooser.addOption("score low and leave, no balance [FACING TOWARD YOU]", m_autoScoreLowLeave);
    m_chooser.addOption("balance gyro no drive [TESTING ONLY]", m_pidBalanceNoDrive);
    //m_chooser.addOption("goofy ass shoot backwards then balance [FACING AWAY FROM YOU]", m_autoFunny);

    SmartDashboard.putData("autos", m_chooser);

    SmartDashboard.putString("message to tony", "tony im in your walls :P");
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    JoystickButton fastButton = new JoystickButton(driver, Constants.fastButton);
    JoystickButton slowButton = new JoystickButton(driver, Constants.slowButton);
    JoystickButton armHome = new JoystickButton(operator, Constants.armHomeButton);
    JoystickButton armLow = new JoystickButton(operator, Constants.armLowButton);
    JoystickButton armMid = new JoystickButton(operator, Constants.armMidButton);
    JoystickButton armHigh = new JoystickButton(operator, Constants.armHighButton);
    JoystickButton wristStay = new JoystickButton(operator, Constants.wristStayButton);

    POVButton spinjitsuButton1 = new POVButton(driver, 90);

    fastButton.whileHeld(m_fastDrive);
    slowButton.whileHeld(m_slowDrive);
    //spinjitsuButton1.whileHeld(m_spinjitsu1);
  

    armHome.whileHeld(new SetArmCommand(m_armSubsystem, "home"));

    armLow.whileHeld(new SetArmCommand(m_armSubsystem, "low"));

    armMid.whileHeld(new SetArmCommand(m_armSubsystem, "mid"));

    armHigh.whileHeld(new SetArmCommand(m_armSubsystem, "high"));

  }

  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
