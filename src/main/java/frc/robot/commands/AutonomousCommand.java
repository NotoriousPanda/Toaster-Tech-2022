package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class AutonomousCommand extends CommandBase {

    private final DriveTrainSubsystem driveTrain;
    private final IntakeSubsystem intake;

    public AutonomousCommand (DriveTrainSubsystem driveTrain, IntakeSubsystem intake){
        this.intake = intake;
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
        addRequirements(intake);
    }
    public void execute (){

        while (driveTrain.getLeftEncoder() < -500){
            driveTrain.setMotorPowers(0.1, 0.1, "autonomous");

        }

        driveTrain.stop("end");

        

        

        


        

        
    }

    public void stop(){
        driveTrain.stop("end");
    }
    
}
