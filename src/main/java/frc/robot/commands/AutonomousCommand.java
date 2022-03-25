package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MidtakeSubsystem;

public class AutonomousCommand extends CommandBase {

    private final DriveTrainSubsystem driveTrain;
    private final IntakeSubsystem intake;
    private final MidtakeSubsystem midtake;

    public int TimerPartA;
    public int TimerPartB;
    public int TimerPartC;

    public AutonomousCommand (DriveTrainSubsystem driveTrain, IntakeSubsystem intake, MidtakeSubsystem midtake){

        this.intake = intake;
        this.driveTrain = driveTrain;
        this.midtake = midtake;


        TimerPartA = 0;
        TimerPartB = 0;
        TimerPartC = 0;


        addRequirements(driveTrain);
        addRequirements(intake);
        addRequirements(midtake);

    }
    public void execute (){

        System.out.print(driveTrain.getLeftEncoder());

        while (TimerPartA < 7000){

            System.out.print(driveTrain.getLeftEncoder());

            driveTrain.setMotorPowers(0.2, 0.2, "autonomous");

            TimerPartA++;
        }
            
        driveTrain.stop("just do it");

        while (TimerPartB < 3000){


            intake.setMotorPower(0.5, "reason");
            //midtake.setMotorPower(0.5, "lmao");

            TimerPartB++;
        }

        while (TimerPartC < 6969){

            midtake.setMotorPower(0.5, "Lol");

            TimerPartC++;
        }

        midtake.stop("xD");
        midtake.stop("Hehehehhehehe");

    }

    
            /*while (TimerPartB < 3000){


                intake.setMotorPower(0.5, "reason");
                //midtake.setMotorPower(0.5, "lmao");

                TimerPartB++;



        }

         (TimerPartB < 6969){

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