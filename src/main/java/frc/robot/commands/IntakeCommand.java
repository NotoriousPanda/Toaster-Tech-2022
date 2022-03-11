package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase{
    IntakeSubsystem intake;
    double power;
    public IntakeCommand(IntakeSubsystem intake, double power){
        this.intake = intake;
        this.power = power;
    }

    @Override
    public void initialize(){

    }
    @Override 
    public void execute(){
        intake.setSpeedRatio(power, "button said to");
    }

    @Override
    public void end(boolean interrupted){
        intake.stop("stopped intake command");
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
