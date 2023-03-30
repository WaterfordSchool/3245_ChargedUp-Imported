package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.SetArmCommand;
import frc.robot.commands.SetClawV3RunCommand;
import frc.robot.commands.SetTiltCommand;
import frc.robot.subsystems.ArmSubystem;
import frc.robot.subsystems.ClawV3Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.WristSubsystem;

public class AutoScoreHighLeave extends SequentialCommandGroup {
    public AutoScoreHighLeave(DriveTrain driveTrain, ArmSubystem armSubystem, WristSubsystem wristSubsystem, ClawV3Subsystem clawSubsystem){

        SetArmCommand setArmHigh = new SetArmCommand(armSubystem, "high");  
        WaitCommand wait = new WaitCommand(2);
        SetTiltCommand setWristHigh = new SetTiltCommand(wristSubsystem, "high");  
        SetTiltCommand setWristHomeInit = new SetTiltCommand(wristSubsystem, "home");
        SetClawV3RunCommand setClawRun = new SetClawV3RunCommand(clawSubsystem, 1);
        SetTiltCommand setWristHome = new SetTiltCommand(wristSubsystem, "home");
        SetArmCommand setArmHome = new SetArmCommand(armSubystem, "home");
        AutoDrive driveOut = new AutoDrive(driveTrain, 3.8, 0.5, 0);
        addCommands(
            setArmHigh,
            new WaitCommand(2),
            setWristHigh,
            new WaitCommand(3.5),
            setClawRun,
            new WaitCommand(.5),
            setWristHome,
            new WaitCommand(3.5),
            setArmHome
            //driveOut
        );
    }
}
