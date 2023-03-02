// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.sound.midi.Sequence;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoCommandBalance;
import frc.robot.commands.AutoCommandNoBalance;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.ManualArmCommand;
import frc.robot.commands.ManualClawV2RunCommand;
import frc.robot.commands.ManualTiltCommand;
import frc.robot.commands.SetArmCommand;
import frc.robot.commands.SetSpitClawV2Command;
import frc.robot.commands.SetTiltCommand;
import frc.robot.commands.Spinjitsu;
import frc.robot.subsystems.ArmSubystem;
import frc.robot.subsystems.ClawV2Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.TiltSubsystemIDK;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class RobotContainer {

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  XboxController driver = new XboxController(0);
  XboxController operator = new XboxController(1);

  //subsystems
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final ClawV2Subsystem m_clawV2Subsystem = new ClawV2Subsystem();
  private final ArmSubystem m_armSubsystem = new ArmSubystem();
  private final TiltSubsystemIDK m_wristSubsystem = new TiltSubsystemIDK();
  //commands
  //auto command
  private final Command m_autoBalance = new SequentialCommandGroup(/*new SetArmCommand(m_armSubsystem, "high", operator), new SetTiltCommand(m_wristSubsystem, "high"), new SetSpitClawV2Command(m_clawV2Subsystem, 1), new SetTiltCommand(m_wristSubsystem, "home"),*/ new AutoDrive(m_driveTrain, 3.6, -0.5, 0));
  private final Command m_autoNoBalance = new SequentialCommandGroup(/*new SetArmCommand(m_armSubsystem, "high", operator), new SetTiltCommand(m_wristSubsystem, "high"), new SetSpitClawV2Command(m_clawV2Subsystem, 1), new SetTiltCommand(m_wristSubsystem, "home"),*/ new AutoDrive(m_driveTrain, 3.8, 0.5, 0));


  //private final AutoNoBalanceGroup m_autoGroup = new SequentialCommandGroup(new SetArmCommand(m_armSubsystem, null), new AutoDrive(2, .3, 0),);
  private final AutoCommandNoBalance m_autonomousNoBalanceCommand = new AutoCommandNoBalance(m_driveTrain);

  //drive commands
  private final ArcadeDrive m_fastDrive = new ArcadeDrive(m_driveTrain, 1, 0.8, driver);
  private final ArcadeDrive m_slowDrive = new ArcadeDrive(m_driveTrain, 0.3, 0.4, driver);
  private final ArcadeDrive m_arcadeDefault = new ArcadeDrive(m_driveTrain, 0.8, 0.8, driver);
  private final Spinjitsu m_spinjitsu1 = new Spinjitsu(m_driveTrain, 1, driver);

  //arm commands

  //elevator commands

  //claw commands

  //tilt commands

  //command groups
  private final SequentialCommandGroup m_lowCommandGroup =  new SequentialCommandGroup(new SetTiltCommand(m_wristSubsystem, "home"), new SetArmCommand(m_armSubsystem, "low", operator), new SetTiltCommand(m_wristSubsystem, "low"));
  private final SequentialCommandGroup m_midCommandGroup =  new SequentialCommandGroup(new SetTiltCommand(m_wristSubsystem, "home"), new SetArmCommand(m_armSubsystem, "mid", operator), new SetTiltCommand(m_wristSubsystem, "mid"));
  private final SequentialCommandGroup m_highCommandGroup =  new SequentialCommandGroup(new SetTiltCommand(m_wristSubsystem, "home"), new SetArmCommand(m_armSubsystem, "high", operator), new SetTiltCommand(m_wristSubsystem, "high"));
  private final SequentialCommandGroup m_homeCommandGroup =  new SequentialCommandGroup(new SetTiltCommand(m_wristSubsystem, "home"), new SetArmCommand(m_armSubsystem, "home", operator));

  private final SequentialCommandGroup m_lowSequence = new SetTiltCommand(m_wristSubsystem, "home").andThen(new SetArmCommand(m_armSubsystem, "low", operator)).andThen(new SetTiltCommand(m_wristSubsystem, "low"));
  private final SequentialCommandGroup m_midSequence = new SetTiltCommand(m_wristSubsystem, "home").andThen(new SetArmCommand(m_armSubsystem, "mid", operator)).andThen(new SetTiltCommand(m_wristSubsystem, "mid"));
  private final SequentialCommandGroup m_highSequence = new SetTiltCommand(m_wristSubsystem, "home").andThen(new SetArmCommand(m_armSubsystem, "high", operator)).andThen(new SetTiltCommand(m_wristSubsystem, "high"));
  private final SequentialCommandGroup m_homeSequence = new SetTiltCommand(m_wristSubsystem, "home").andThen(new SetArmCommand(m_armSubsystem, "home", operator));


  public RobotContainer() {
    //default commands
    m_driveTrain.setDefaultCommand(m_arcadeDefault);
    //m_armSubsystem.setDefaultCommand(new ManualArmCommand(m_armSubsystem, operator));
    m_clawV2Subsystem.setDefaultCommand(new ManualClawV2RunCommand(m_clawV2Subsystem, operator));
    //m_wristSubsystem.setDefaultCommand(new ManualTiltCommand(m_wristSubsystem, operator));
    
    //choosable auto
    m_chooser.setDefaultOption("no balance", m_autoNoBalance);
    m_chooser.addOption("balance", m_autoBalance);
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
    JoystickButton manualOverride = new JoystickButton(operator, 8);
    JoystickButton zeroButton = new JoystickButton(operator, 7);

    POVButton spinjitsuButton1 = new POVButton(driver, 90);


    fastButton.whileHeld(m_fastDrive);
    slowButton.whileHeld(m_slowDrive);
    spinjitsuButton1.whileHeld(m_spinjitsu1);

    //armHome.whileHeld(new SetArmCommand(m_armSubsystem, "home", operator));
    armHome.onTrue(m_homeSequence);
    armLow.onTrue(m_lowSequence);
    //armLow.whileHeld(new SetArmCommand(m_armSubsystem, "low", operator));
    armMid.onTrue(m_midSequence);
    //armMid.whileHeld(new SetArmCommand(m_armSubsystem, "mid", operator));
    armHigh.onTrue(m_highSequence);
    //armHigh.whileHeld(new SetArmCommand(m_armSubsystem, "high", operator));
    //manualOverride.toggleOnTrue(new ManualArmCommand(m_armSubsystem, operator));
    
    //zeroButton.whenPressed(new SetArmCommand(m_armSubsystem, "reset", operator));

    //TODO: switch to wrist manual default control
    
    //armHome.whileHeld(new SetArmCommand(m_armSubsystem, "low"));
    
    /*
    command groups: .whenPressed
    */

    /*
    position sets: .whileHeld, .whenInactive
    armup.whileheld(new up)
    armdown.whileheld(new down)
    armup.wheninactive(new manual)
    armdown.wheninactive(new manual)
    */
    
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //TODO: make auto work
    return m_chooser.getSelected();
    //return null;
  }
}
