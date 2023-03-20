// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.BalanceAutoCommand;
import frc.robot.commands.ManualClawV3RunCommand;
import frc.robot.commands.ManualLed;
import frc.robot.commands.ManualTiltCommand;
import frc.robot.commands.SetArmCommand;
import frc.robot.commands.SetTiltCommand;
import frc.robot.commands.Spinjitsu;
import frc.robot.subsystems.ArmSubystem;
import frc.robot.subsystems.ClawV3Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LEDSSubsystem;
import frc.robot.subsystems.WristSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
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
  private final Command m_autoBalance = new SequentialCommandGroup(/*new SetArmCommand(m_armSubsystem, "high", operator), new SetTiltCommand(m_wristSubsystem, "high"), new SetSpitClawV2Command(m_clawV2Subsystem, 1), new SetTiltCommand(m_wristSubsystem, "home"),*/  new AutoDrive(m_driveTrain, 3.9, -0.5, 0), new AutoDrive(m_driveTrain, 4, 0, .3));
  private final Command m_autoNoBalance = new SequentialCommandGroup(/*new SetArmCommand(m_armSubsystem, "high", operator), new SetTiltCommand(m_wristSubsystem, "high"), new SetSpitClawV2Command(m_clawV2Subsystem, 1), new SetTiltCommand(m_wristSubsystem, "home"), new SetTiltCommand(m_wristSubsystem, "low"), new SetSpitClawV2Command(m_clawV2Subsystem, 1),*/ new AutoDrive(m_driveTrain, 3.8, 0.5, 0));
  private final Command m_autoBalanceMoreComplicated = new BalanceAutoCommand(m_driveTrain);

  //drive commands
  private final ArcadeDrive m_fastDrive = new ArcadeDrive(m_driveTrain, 1, 0.725, driver);
  private final ArcadeDrive m_slowDrive = new ArcadeDrive(m_driveTrain, 0.4, 0.4, driver);
  private final ArcadeDrive m_arcadeDefault = new ArcadeDrive(m_driveTrain, 0.8, 0.8, driver);
  private final Spinjitsu m_spinjitsu1 = new Spinjitsu(m_driveTrain, 1, driver);

  //command groups
  private final SequentialCommandGroup m_lowCommandGroup =  new SequentialCommandGroup(new SetTiltCommand(m_wristSubsystem, "home"), new SetArmCommand(m_armSubsystem, "low"), new WaitCommand(.5), new SetTiltCommand(m_wristSubsystem, "low"));
  private final SequentialCommandGroup m_midCommandGroup =  new SequentialCommandGroup(new SetTiltCommand(m_wristSubsystem, "home"), new WaitCommand(.5), new SetArmCommand(m_armSubsystem, "mid"), new WaitCommand(.5), new SetTiltCommand(m_wristSubsystem, "mid"));
  private final SequentialCommandGroup m_highCommandGroup =  new SequentialCommandGroup(new SetTiltCommand(m_wristSubsystem, "home"), new WaitCommand(.5), new SetArmCommand(m_armSubsystem, "high"), new WaitCommand(.5), new SetTiltCommand(m_wristSubsystem, "high"));
  private final SequentialCommandGroup m_homeCommandGroup =  new SequentialCommandGroup(new SetTiltCommand(m_wristSubsystem, "home"), new WaitCommand(.5), new SetArmCommand(m_armSubsystem, "home"));

  private final SequentialCommandGroup m_lowSequence = new SetTiltCommand(m_wristSubsystem, "home").beforeStarting(new SetArmCommand(m_armSubsystem, "low")).beforeStarting(new SetTiltCommand(m_wristSubsystem, "low"));
  private final SequentialCommandGroup m_midSequence = new SetTiltCommand(m_wristSubsystem, "home").beforeStarting(new SetArmCommand(m_armSubsystem, "mid")).beforeStarting(new SetTiltCommand(m_wristSubsystem, "mid"));
  private final SequentialCommandGroup m_highSequence = new SetTiltCommand(m_wristSubsystem, "home").beforeStarting(new SetArmCommand(m_armSubsystem, "high")).beforeStarting(new SetTiltCommand(m_wristSubsystem, "high"));
  private final SequentialCommandGroup m_homeSequence = new SetTiltCommand(m_wristSubsystem, "home").beforeStarting(new SetArmCommand(m_armSubsystem, "home"));
  private final SequentialCommandGroup m_fiddlingAround = new SequentialCommandGroup(new SetArmCommand(m_armSubsystem, "mid"), new WaitCommand(1), new SetArmCommand(m_armSubsystem, "high"), new WaitCommand(1), new SetArmCommand(m_armSubsystem, "mid"), new WaitCommand(1), new SetArmCommand(m_armSubsystem, "home"));

  public RobotContainer() {
    //default commands
    m_driveTrain.setDefaultCommand(m_arcadeDefault);
    //m_armSubsystem.setDefaultCommand(new ManualArmCommand(m_armSubsystem, operator));
    m_clawV3Subsystem.setDefaultCommand(new ManualClawV3RunCommand(m_clawV3Subsystem, operator));
    m_wristSubsystem.setDefaultCommand(new ManualTiltCommand(m_wristSubsystem, operator));
    m_ledsSubsystem.setDefaultCommand(new ManualLed(m_ledsSubsystem, operator));
    
    //choosable auto
    m_chooser.setDefaultOption("no balance", m_autoNoBalance);
    m_chooser.addOption("balance", m_autoBalance);
    m_chooser.addOption("balance gyro", m_autoBalanceMoreComplicated);
    SmartDashboard.putData("autos", m_chooser);
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
    spinjitsuButton1.whileHeld(m_spinjitsu1);
  

    armHome.whileHeld(new SetArmCommand(m_armSubsystem, "home"));

    armLow.whileHeld(new SetArmCommand(m_armSubsystem, "low"));

    armMid.whileHeld(new SetArmCommand(m_armSubsystem, "mid"));

    armHigh.whileHeld(new SetArmCommand(m_armSubsystem, "high"));

    /*armHome.whileHeld(new SetTiltCommand(m_wristSubsystem, "home"));
    
    armLow.whileHeld(new SetTiltCommand(m_wristSubsystem, "low"));

    armMid.whileHeld(new SetTiltCommand(m_wristSubsystem, "mid"));

    armHigh.whileHeld(new SetTiltCommand(m_wristSubsystem, "high"));*/
    

    //TODO: switch to wrist manual default control
    
    
    /*
    command groups: .whenPressed
    */
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }
}
