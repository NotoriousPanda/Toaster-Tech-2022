package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class AutonomousCommand extends CommandBase {

    private final DriveTrainSubsystem driveTrain;

    public AutonomousCommand (DriveTrainSubsystem driveTrain){
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
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
