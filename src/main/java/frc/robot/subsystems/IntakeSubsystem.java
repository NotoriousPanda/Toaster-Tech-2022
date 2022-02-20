package frc.robot.subsystems;

import java.util.Objects;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ITeamTalon;
import frc.robot.TeamTalonFX;
import frc.robot.constants.Ports;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;


public class IntakeSubsystem extends SubsystemBase {
    ITeamTalon intakeMotor;
    int counter = 0;
    public IntakeSubsystem(){
        throw new IllegalArgumentException("provide parameters");
    }

    public IntakeSubsystem(ITeamTalon intakeMotor){
        this.intakeMotor = Objects.requireNonNull(intakeMotor, "intakeMotor shouldn't be null");
        setupIntake();
    }

    private void setupIntake(){
        TalonFXConfiguration configs = new TalonFXConfiguration();
        configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
        intakeMotor.configBaseAllSettings(configs);
        intakeMotor.setNeutralMode(NeutralMode.Brake);
        intakeMotor.set(0, "");
    }

    public static IntakeSubsystem Create(){
        ITeamTalon intakeMotorCAN = new TeamTalonFX("Subsystems.Intake.Motor", Ports.IntakeMotorCAN);
        return new IntakeSubsystem(intakeMotorCAN);
    }

    @Override
    public void periodic(){
        /*if(counter++ % 3 == 0){
            this.setMotorPower(0.1, "testing");
        } else {
            this.stop("aa");
        }*/
        //this.setMotorPower(0.5, "");
    }

    private double getCappedPower(double desired){
        return Math.max(Math.min(1, desired), -1);
    }

    public void setMotorPower(double power, String reason){
        intakeMotor.set(getCappedPower(power), reason);
    }

    public void stop(String reason){
        setMotorPower(0, reason);
    }
}
