package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PidParameters;
import frc.robot.TeamSparkMAX;
import frc.robot.TeamUtils;
import frc.robot.constants.Ports;

public class TurretSubsystem extends SubsystemBase{
    private TeamSparkMAX turretMotor;
    private VisionSubsystem visionSubsystem;
    private boolean isTurretCalibrating = false;
    private boolean hasBeenCalibrated = true;
    private long minEncoderRange = -8621;
    private int rotationSpeed = 500;

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
    private boolean isPowerOkay(double powerToCheck) {
        if (powerToCheck == 0) return true;

        // Check safety limits if turret is not running TurretToLimit calibration
        if (isTurretCalibrating) {
          return true;
        }
    
        if (!hasBeenCalibrated) {
          System.err.println("Turret has not been calibrated! Press home on controller 2!");
          return false;
        }
    
        if (turretMotor.getCurrentEncoderValue() > 0 && powerToCheck > 0) {
          return false;
        }
    
        if (turretMotor.getCurrentEncoderValue() < minEncoderRange && powerToCheck < 0) {
          return false;
    }

    return true;
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
    @Override
    public void periodic(){
        //this.setMotorPower(0.15, "!@#$%^&");
        Command cmd = getCurrentCommand();
        if (!isPowerOkay(turretMotor.getMotorOutputPercent())) {
            turretMotor.noteEmergencyStop();
            stop("Lol");
            if (cmd != null) cmd.cancel();
      
            stop("Lol"); }

            
    }

    
    public boolean isReadyToHang() {
        return turretMotor.getCurrentEncoderValue() < 8000;

    }

    
    }
