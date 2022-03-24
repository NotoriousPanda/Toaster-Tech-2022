package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PidParameters;
import frc.robot.TeamSparkMAX;
import frc.robot.TeamUtils;
import frc.robot.constants.Ports;


public class MidtakeSubsystem extends SubsystemBase{

    double counter;
    private TeamSparkMAX midtakeMotor;

    public MidtakeSubsystem(TeamSparkMAX midtakeMotor){
        this.midtakeMotor = midtakeMotor;
        setupRealIntake();
    }

    public static MidtakeSubsystem Create(){
        TeamSparkMAX midtakeMotor = new TeamSparkMAX("Subsystems.RealIntake.Motor", Ports.midtake);
        return new MidtakeSubsystem(midtakeMotor);
    }

    private void setupRealIntake(){
        
    }

    @Override
    public void periodic(){

    }

    private double getCappedPower(double desired){
        return Math.max(Math.min(1, desired), -1);
    }

    public void setMotorPower(double power, String reason){
        midtakeMotor.set(getCappedPower(power), reason);
    }

    public void stop(String reason){
        setMotorPower(0, reason);

    }

    public double getCurrentEncoderValue(){
        return counter;

    }
}
