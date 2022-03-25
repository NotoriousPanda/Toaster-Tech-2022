package frc.robot.subsystems;

import java.util.Objects;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ITeamTalon;
import frc.robot.PidParameters;
import frc.robot.TeamTalonFX;
import frc.robot.constants.Ports;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;


public class IntakeSubsystem extends SubsystemBase {
    //private static ITeamTalon motor;
    ITeamTalon intakeMotor;
    PidParameters pidParameters;
    int counter = 0;
    double maxSpeed = 6380;
    public IntakeSubsystem(){
        throw new IllegalArgumentException("provide parameters");
    }

    public IntakeSubsystem(ITeamTalon intakeMotor, PidParameters PidParameters){
        this.intakeMotor = Objects.requireNonNull(intakeMotor, "intakeMotor shouldn't be null");
        this.pidParameters = PidParameters;
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
        PidParameters pidParameters = new PidParameters(0.3, 0, 0, 0, 0, 1, 20000, 1500, 10);
        //motor.set(ControlMode.Velocity, 5000);
        return new IntakeSubsystem(intakeMotorCAN, pidParameters);
    }

    @Override
    public void periodic(){
        /*if(counter++ % 3 == 0){
            this.setMotorPower(0.1, "testing");
        } else {
            this.stop("aa");
        }*/
        //this.setMotorPower(0.5, "");
        pidParameters.periodic("Subsystems.Intake.Main", intakeMotor, 0);
        SmartDashboard.putNumber("Subsystems.Intake.Error", intakeMotor.getClosedLoopError(0));
        SmartDashboard.putNumber("Subsystems.Intake.Setpt", intakeMotor.getClosedLoopTarget(0));
        SmartDashboard.putNumber("Subsystems.Intake.Speed", intakeMotor.getSelectedSensorVelocity(0));
        
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

    public void setSpeedRatio(double power, String reason) {
        intakeMotor.set(ControlMode.Velocity, maxSpeed*Math.max(-1, Math.min(1, power)));
        intakeMotor.configureWithPidParameters(pidParameters, 0);
    }

    public double getCurrentEncoderValue(){
        return counter;
        
    }

}
