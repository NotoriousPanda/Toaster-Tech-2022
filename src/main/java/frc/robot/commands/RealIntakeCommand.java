package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RealIntakeSubsystem;

public class RealIntakeCommand extends CommandBase{
    RealIntakeSubsystem realIntake;
    double power;
    public RealIntakeCommand(RealIntakeSubsystem realIntake, double power){
        this.realIntake = realIntake;
        this.power = power;
    }

    @Override
    public void initialize(){

    }

    @Override
    public void execute(){
        realIntake.setMotorPower(power, "just kinda feeling it");
    }

    @Override
    public void end(boolean interrupted){
        realIntake.stop("AAAAAAAAAAAAAA!!!!!!!!!!");
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
