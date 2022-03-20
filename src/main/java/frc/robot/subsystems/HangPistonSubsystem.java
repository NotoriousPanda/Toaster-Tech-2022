package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PidParameters;
import frc.robot.TeamUtils;
import frc.robot.commands.tilthangCommand;
import frc.robot.constants.Ports;

public class HangPistonSubsystem extends SubsystemBase{
    private Solenoid hangPiston;
    public boolean isExtended;

    public HangPistonSubsystem(Solenoid hangPiston){
        this.hangPiston = hangPiston;
        setuphangPiston();
    }

    public static HangPistonSubsystem Create(){
        Solenoid hangPiston = new Solenoid(PneumaticsModuleType.REVPH, Ports.hangpistonport);
        return new HangPistonSubsystem(hangPiston);
    }

    private void setuphangPiston(){

        isExtended = false;

    }

    @Override
    public void periodic(){


    }


    public void stop(String reason){
    }

    public void tiltHang(){
        isExtended = false;
        hangPiston.set(true);
        System.out.print("lowerworks");
    }
    public void untiltHang(){
        isExtended = true;
          hangPiston.set(false);
        System.out.print("raise works");
    }
}
