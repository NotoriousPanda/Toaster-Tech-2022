package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MidtakeSubsystem;

public class AutonomousCommand extends CommandBase {

    private final DriveTrainSubsystem driveTrain;
    private final IntakeSubsystem intake;
    private final MidtakeSubsystem midtake;

    public AutonomousCommand (DriveTrainSubsystem driveTrain, IntakeSubsystem intake, MidtakeSubsystem midtake){

        this.intake = intake;
        this.driveTrain = driveTrain;
        this.midtake = midtake;
        addRequirements(driveTrain);
        addRequirements(intake);
        addRequirements(midtake);

    }
    public void execute (){

        while (driveTrain.getLeftEncoder() < -500){
            driveTrain.setMotorPowers(0.1, 0.1, "autonomous");

           while (intake.getCurrentEncoderValue() < - 500){
            intake.setMotorPower(0.5, "reason");

                while (midtake.getCurrentEncoderValue() < - 500){
                 midtake.setMotorPower(0.5, "lmao");


            }
           }

        }

        driveTrain.stop("end");

        
        


        

        
    }

    public void stop(){
        driveTrain.stop("end");
    }
    
}
