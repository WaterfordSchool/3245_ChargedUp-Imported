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

public class AutoScoreLowLeave extends SequentialCommandGroup {
    public AutoScoreLowLeave(DriveTrain driveTrain, ArmSubystem armSubystem, WristSubsystem wristSubsystem, ClawV3Subsystem clawSubsystem){

        SetArmCommand setArmLow = new SetArmCommand(armSubystem, "low");  
        SetArmCommand setArmHome = new SetArmCommand(armSubystem, "home");
        SetTiltCommand setWristLow = new SetTiltCommand(wristSubsystem, "low");  
        SetClawV3RunCommand setClawRun = new SetClawV3RunCommand(clawSubsystem, 1);
        SetTiltCommand setWristHome = new SetTiltCommand(wristSubsystem, "home");
        AutoDrive driveOut = new AutoDrive(driveTrain, 3.8, 0.5, 0);
        addCommands(
            setArmLow,
            new WaitCommand(1.5),
            setWristLow,
            new WaitCommand(2),
            setClawRun,
            new WaitCommand(1),
            setWristHome,
            new WaitCommand(2),
            driveOut
        );
    }
}
