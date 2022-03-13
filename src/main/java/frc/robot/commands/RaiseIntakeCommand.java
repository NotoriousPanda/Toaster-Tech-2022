package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.RealIntakeSubsystem;

public class RaiseIntakeCommand extends CommandBase{

    private RealIntakeSubsystem intake;
    public RaiseIntakeCommand(RealIntakeSubsystem intake){
        this.intake = intake;
        addRequirements(intake);
    }

    public void start(){
        intake.raiseIntake();

    }
    
}
