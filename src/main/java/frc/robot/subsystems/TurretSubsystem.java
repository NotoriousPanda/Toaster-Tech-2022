package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PidParameters;
import frc.robot.TeamSparkMAX;
import frc.robot.TeamUtils;
import frc.robot.constants.Ports;

public class TurretSubsystem extends SubsystemBase{
    private TeamSparkMAX turretMotor;
    private VisionSubsystem visionSubsystem;

    public TurretSubsystem(TeamSparkMAX turretMotor){
        this.turretMotor = turretMotor;
        setupTurret();
    }

    public static TurretSubsystem Create(VisionSubsystem visionSubsystem){
        TeamSparkMAX turretMotor = new TeamSparkMAX("Subsystems.Turret.Motor", Ports.TurretMotorCAN);
        return new TurretSubsystem(turretMotor);
    }

    private void setupTurret(){
        
    }

    @Override
    public void periodic(){
        this.setMotorPower(0.15, "!@#$%^&");
    }

    private double getCappedPower(double desired){
        return Math.max(Math.min(1, desired), -1);
    }

    public void setMotorPower(double power, String reason){
        turretMotor.set(getCappedPower(power), reason);
    }

    public void stop(String reason){
        setMotorPower(0, reason);
    }

}
