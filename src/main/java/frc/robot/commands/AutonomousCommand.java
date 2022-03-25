package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MidtakeSubsystem;

public class AutonomousCommand extends CommandBase {

    private final DriveTrainSubsystem driveTrain;
    private final IntakeSubsystem intake;
    private final MidtakeSubsystem midtake;

    protected int TimerPartA;
    protected int TimerPartB;

    public AutonomousCommand (DriveTrainSubsystem driveTrain, IntakeSubsystem intake, MidtakeSubsystem midtake){

        this.intake = intake;
        this.driveTrain = driveTrain;
        this.midtake = midtake;


        TimerPartA = 0;
        TimerPartB = 0;


        addRequirements(driveTrain);
        addRequirements(intake);
        addRequirements(midtake);

    }
    public void execute (){

        while (driveTrain.getLeftEncoder() < -650){
            driveTrain.setMotorPowers(0.1, 0.1, "autonomous");
                }

            

                driveTrain.stop("Lol");

                }


        
    
            /*else if (TimerPartA < 3000){

                driveTrain.stop("end");

                intake.setMotorPower(0.5, "reason");
                //midtake.setMotorPower(0.5, "lmao");

                TimerPartA++;



        }

        else if (TimerPartB < 6969){

            midtake.setMotorPower(0.5, "Lol");

            TimerPartB++;



        }
        
        else {
            
            midtake.stop("xD");



        }
    
        

            
           }

        
           */
        
        
    

    public void stop(){
        driveTrain.stop("end");
    }
    
}


/* (intake.getCurrentEncoderValue() < - 500){
            intake.setMotorPower(0.5, "reason");

        }
                else if (midtake.getCurrentEncoderValue() < - 500){
                 midtake.setMotorPower(0.5, "lmao");
*/