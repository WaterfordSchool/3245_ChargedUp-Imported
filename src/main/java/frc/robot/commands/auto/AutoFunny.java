package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.SetArmCommand;
import frc.robot.commands.SetClawV3RunCommand;
import frc.robot.commands.SetClawV3SpitHard;
import frc.robot.commands.SetWristCommand;
import frc.robot.subsystems.ArmSubystem;
import frc.robot.subsystems.ClawV3Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.WristSubsystem;

public class AutoFunny extends SequentialCommandGroup {
    public AutoFunny(DriveTrain driveTrain, ArmSubystem armSubystem, WristSubsystem wristSubsystem, ClawV3Subsystem clawSubsystem){

        SetArmCommand setArm = new SetArmCommand(armSubystem, "funny auto");  
        SetWristCommand setWrist = new SetWristCommand(wristSubsystem, "funny auto");  
        SetClawV3SpitHard setClawRun = new SetClawV3SpitHard(clawSubsystem, 1);
        SetWristCommand setWristHome = new SetWristCommand(wristSubsystem, "home");
        SetArmCommand setArmHome = new SetArmCommand(armSubystem, "home");
        AutoBalanceDriveCommand balanceDriveForward = new AutoBalanceDriveCommand(driveTrain, -0.7, 0);
        BalancePIDCommand balancePID = new BalancePIDCommand(driveTrain);
        addCommands(
            setArm,
            new WaitCommand(2),
            setWrist,
            new WaitCommand(1),
            setClawRun,
            new WaitCommand(.3),
            setWristHome,
            new WaitCommand(1),
            setArmHome
            //balanceDriveForward,
            //balancePID
        );
    }
}
