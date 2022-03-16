package frc.robot.subsystems;

import java.util.Objects;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Ports;

public class HangSubsystem extends SubsystemBase{

    private final WPI_TalonFX leftHanger;
    private final WPI_TalonFX rightHanger;
    private final DigitalInput leftLimit;
    private final DigitalInput rightLimit;
    private final DigitalInput leftHookLimit;
    private final DigitalInput rightHookLimit;
    public HangSubsystem() {
        throw new IllegalArgumentException(
            "not allowed! ctor must provide parameters for all dependencies");
    }

    public HangSubsystem(
      WPI_TalonFX leftHanger,
      WPI_TalonFX rightHanger,
      DigitalInput leftLimit,
      DigitalInput rightLimit,
      DigitalInput leftHookLimit, 
      DigitalInput rightHookLimit) {
        this.leftHanger = Objects.requireNonNull(leftHanger, "leftHanger must not be null");
        this.rightHanger = Objects.requireNonNull(rightHanger, "rightHanger must not be null");
        this.leftLimit = Objects.requireNonNull(leftLimit, "leftLimit must not be null");
        this.rightLimit = Objects.requireNonNull(rightLimit, "rightLimit must be null");
        this.leftHookLimit = Objects.requireNonNull(leftHookLimit, "leftHookLimit must be null");
        this.rightHookLimit = Objects.requireNonNull(rightHookLimit, "rightHookLimit must not be null");
        leftHanger.setNeutralMode(NeutralMode.Brake);
        rightHanger.setNeutralMode(NeutralMode.Coast);

      }

      public static HangSubsystem Create() {
          WPI_TalonFX leftHanger = new WPI_TalonFX(Ports.LeftHangCAN);
          WPI_TalonFX rightHanger = new WPI_TalonFX(Ports.RightHangCAN);
          DigitalInput leftLimit = new DigitalInput(Ports.Climber_LeftLimitDIO);
          DigitalInput rightLimit = new DigitalInput(Ports.Climber_RightLimitDIO);
          DigitalInput leftLimitHook = new DigitalInput(Ports.Hook_LeftDIO);
          DigitalInput rightHookLimit = new DigitalInput(Ports.Hook_RightDIO);
          
        return new HangSubsystem(leftHanger,rightHanger, leftLimit, rightLimit, leftLimitHook, rightHookLimit);
      }

        @Override
        public void periodic() {
        SmartDashboard.putNumber("Subsystems.hanger.leftPow", leftHanger.get());
        SmartDashboard.putNumber("Subsystems.hanger.rightPow", rightHanger.get());
        SmartDashboard.putBoolean("Subsystem.Hanger.leftLimit", leftLimit.get());
        SmartDashboard.putBoolean("Subsystems.Hanger.rightLimit", rightLimit.get());

        SmartDashboard.putBoolean("Subsystems.Hanger.leftHookLimit", leftHookLimit.get());
        SmartDashboard.putBoolean("Subsystems.Hanger.rightHookLimit", rightHookLimit.get());


        }
        
    public void stop() {
        leftHanger.set(0);
        rightHanger.set(0);
      }
    
      public void setHang(double power) {
        leftHanger.set(-power);
        rightHanger.set((power) * .9);
      }
    
      public void setLeftHanger(double power) {
        leftHanger.set(-power);
      }
    
      public void setRightHanger(double power) {
        //rightHanger.set(power);
      }
}   
