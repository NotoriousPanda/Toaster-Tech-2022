package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PidParameters;
import frc.robot.TeamSparkMAX;
import frc.robot.TeamUtils;
import frc.robot.commands.RealIntakeCommand;
import frc.robot.constants.Ports;




public class RealIntakeSubsystem extends SubsystemBase{
    private TeamSparkMAX realIntakeMotor;
    private Solenoid leftPiston;
    // private DoubleSolenoid intakepiston;
    public boolean isExtended;

    public RealIntakeSubsystem(TeamSparkMAX realIntakeMotor, Solenoid leftPiston){
        this.realIntakeMotor = realIntakeMotor;
        this.leftPiston = leftPiston;
        setupRealIntake();
    }

    public static RealIntakeSubsystem Create(){
        TeamSparkMAX realIntakeMotor = new TeamSparkMAX("Subsystems.RealIntake.Motor", Ports.RealIntakeMotorCan);
        Solenoid leftPiston = new Solenoid(PneumaticsModuleType.CTREPCM, Ports.intakepistonport);
        return new RealIntakeSubsystem(realIntakeMotor, leftPiston );
    }

    private void setupRealIntake(){

        isExtended = false;

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

    public void lowerIntake(){
        isExtended = false;
        leftPiston.set(true);
        //rightPiston.set(false);
        System.out.print("lowerworks");
    }
    public void raiseIntake(){
        isExtended = true;
       
          leftPiston.set(false);
        //rightPiston.set(true);
        System.out.print("raise works");
    }
}
