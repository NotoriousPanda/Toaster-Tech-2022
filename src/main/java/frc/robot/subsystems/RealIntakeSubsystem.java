package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PidParameters;
import frc.robot.TeamSparkMAX;
import frc.robot.TeamUtils;
import frc.robot.constants.Ports;

public class RealIntakeSubsystem extends SubsystemBase{
    private TeamSparkMAX realIntakeMotor;

    public RealIntakeSubsystem(TeamSparkMAX realIntakeMotor){
        this.realIntakeMotor = realIntakeMotor;
        setupRealIntake();
    }

    public static RealIntakeSubsystem Create(){
        TeamSparkMAX realIntakeMotor = new TeamSparkMAX("Subsystems.RealIntake.Motor", Ports.RealIntakeMotorCan);
        return new RealIntakeSubsystem(realIntakeMotor);
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
        realIntakeMotor.set(getCappedPower(power), reason);
    }

    public void stop(String reason){
        setMotorPower(0, reason);
    }

}
