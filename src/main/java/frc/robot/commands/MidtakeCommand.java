package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MidtakeSubsystem;

public class MidtakeCommand extends CommandBase{
    MidtakeSubsystem midtake;
    double power;
    public MidtakeCommand(MidtakeSubsystem midtake, double power){
        this.midtake = midtake;
        this.power = power;
    }

    @Override
    public void initialize(){

    }
    @Override 
    public void execute(){
        midtake.setMotorPower(power, "button said to");
    }

    @Override
    public void end(boolean interrupted){
        midtake.stop("stopped intake command");
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
