package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;

public class AutoBalanceCommandGroup extends SequentialCommandGroup {
    public AutoBalanceCommandGroup(DriveTrain driveTrain){
        AutoBalanceDriveCommand balanceDriveForward = new AutoBalanceDriveCommand(driveTrain, -0.7, 0);
        BalancePIDCommand balancePID = new BalancePIDCommand(driveTrain);
        
        addCommands(
            balanceDriveForward,
            balancePID
        );
    }
}
