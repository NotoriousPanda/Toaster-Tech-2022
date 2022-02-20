// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


// subsystem is called by commands

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PidParameters;
import frc.robot.TeamSparkMAX;
import frc.robot.TeamUtils;
import frc.robot.constants.Ports;

public class ShooterSubsystem extends SubsystemBase {

  private TeamSparkMAX shooter_main;
  private TeamSparkMAX shooter_follow;
  public final double maximumRPM = 5600;
  public final double minimumRPM = 0;
  public boolean ready;
  private double power = 0D; // 0, 1
  private final double spinUpTolerance = 200;

  private static double kP = 0.0001, //0.0005
      kI = 0.000, //0
      kD = 0.000, //0.00015
      kF = 0.0003, //0.00018
      kIZone = 0,
      kPeakOutput = 1,
      maxVel = 20000,
      maxAcc = 1500;
  private static int errorTolerance = 10;

  private PidParameters pidParameters;

  /** Creates a new ExampleSubsystem. */
  public ShooterSubsystem(TeamSparkMAX motor, TeamSparkMAX motor2, PidParameters pidParameters) {
    shooter_main = motor;
    shooter_follow = motor2;
    this.pidParameters = pidParameters;

    shooter_main.setInverted(true);
    shooter_follow.setInverted(false);

    stop("Stopping at object construction");
  }

  public static ShooterSubsystem Create() {
    return new ShooterSubsystem(new TeamSparkMAX("Subsystems.Shooter.Main", Ports.ShooterMotorMain), new TeamSparkMAX("Subsystems.Shooter.Follow", Ports.ShooterMotorSub), new PidParameters(kP, kI, kD, kF, kIZone, kPeakOutput, maxVel, maxAcc, errorTolerance));
  }

  @Override
  public void periodic() {
    shooter_main.periodic();
    shooter_follow.periodic();
    pidParameters.periodic("Subsystems.Shooter.Main", shooter_main, 0);
    pidParameters.periodic("Subsystems.Shooter.Follow", shooter_follow, 0);

    dashboardUpdate();
    //shootDumb(0.1, "aaaaa");
  }


  private void dashboardUpdate() {
    SmartDashboard.putBoolean("subsystems.ShooterSubsystem.ready", ready);
    SmartDashboard.putNumber("subsystems.ShooterSubsystem.power", power);
    SmartDashboard.putNumber("subsystems.ShooterSubsystem.speed (RPM)", getSpeed());
    SmartDashboard.putNumber("subsystem.ShooterSubsystem.maximumRPM", maximumRPM);
    SmartDashboard.putNumber("subsystem.ShooterSubsystem.minimumRPM", minimumRPM);
  }

  @Override
  public void simulationPeriodic() {
    dashboardUpdate();
  }

  public void setSpeed(double value, String reason) {
    shooter_main.setSmartMotionVelocity(value, reason);
    shooter_follow.setSmartMotionVelocity(value, reason);
  }

  public void setSpeedRatio(double power, String reason) {
    
    shooter_main.setSmartMotionVelocity(this.powerToRpm(power), reason);
    shooter_follow.setSmartMotionVelocity(this.powerToRpm(power), reason);
    shooter_main.configureWithPidParameters(pidParameters, 0);
    shooter_follow.configureWithPidParameters(pidParameters, 0);
  }

  public double powerToRpm(double power) {
    power = Math.max(-1, Math.min(1, power));
    return power*maximumRPM;
  }

  //returns speed in rpm;
  public double getSpeed() {
    return shooter_main.canEncoder.getVelocity();
  }

  public void stop(String reason) {
    shooter_main.setSmartMotionVelocity(0, reason);
    shooter_main.set(0, reason);
    shooter_follow.setSmartMotionVelocity(0, reason);
    shooter_follow.set(0, reason);
  }

  public void shootDumb(double power, String reason) {
    shooter_main.set(power, reason);
    shooter_follow.set(power, reason);
  }

  public boolean isReady() {
    return (Math.abs(shooter_main.getVelocityError()) <= spinUpTolerance) && (Math.abs(shooter_follow.getVelocityError()) <= spinUpTolerance);
  }
}
