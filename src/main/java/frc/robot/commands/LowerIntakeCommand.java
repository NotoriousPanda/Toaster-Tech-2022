package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RealIntakeSubsystem;

public class LowerIntakeCommand extends CommandBase{

    private RealIntakeSubsystem intake;
    public LowerIntakeCommand(RealIntakeSubsystem intake){
        this.intake = intake;
        addRequirements(intake);
    }

    public void init(){
        intake.lowerIntake();

    }
}