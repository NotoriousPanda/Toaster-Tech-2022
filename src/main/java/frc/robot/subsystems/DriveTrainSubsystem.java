// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Objects;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.ITeamTalon;
import frc.robot.TeamTalonFX;
import frc.robot.constants.Ports;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

public class DriveTrainSubsystem extends SubsystemBase {
  ITeamTalon rightDriveFalconMain;
  ITeamTalon leftDriveFalconMain;
  ITeamTalon rightDriveFalconSub;
  ITeamTalon leftDriveFalconSub;


  public DriveTrainSubsystem() {
    throw new IllegalArgumentException(
        "not allowed! ctor must provide parameters for all dependencies");
  }

  public DriveTrainSubsystem(
      ITeamTalon rightDriveFalconMain,
      ITeamTalon leftDriveFalconMain,
      ITeamTalon rightDriveFalconSub,
      ITeamTalon leftDriveFalconSub) {
    this.rightDriveFalconMain =
        Objects.requireNonNull(rightDriveFalconMain, "rightDriveFalconMain must not be null");
    this.leftDriveFalconMain =
        Objects.requireNonNull(leftDriveFalconMain, "leftDriveFalconMain must not be null");
    this.rightDriveFalconSub =
        Objects.requireNonNull(rightDriveFalconSub, "rightDriveFalconSub must not be null");
    this.leftDriveFalconSub =
        Objects.requireNonNull(leftDriveFalconSub, "leftDriveFalconSub must not be null");

    setupDrivetrain();
  }

  private void setupDrivetrain() {
    // This configures the falcons to use their internal encoders
    TalonFXConfiguration configs = new TalonFXConfiguration();
    configs.primaryPID.selectedFeedbackSensor = FeedbackDevice.IntegratedSensor;
    rightDriveFalconMain.configBaseAllSettings(configs);
    leftDriveFalconMain.configBaseAllSettings(configs);

    leftDriveFalconSub.follow(leftDriveFalconMain);
    rightDriveFalconSub.follow(rightDriveFalconMain);

    rightDriveFalconMain.setInverted(false);
    rightDriveFalconSub.setInverted(false);
    
    leftDriveFalconMain.setInverted(true);
    leftDriveFalconSub.setInverted(true);
  }

  public static DriveTrainSubsystem Create() {
    ITeamTalon rightDriveFalconMainCAN =
        new TeamTalonFX("Subsystems.DriveTrain.RightMain", Ports.RightDriveFalconMainCAN);
    ITeamTalon leftDriveFalconMainCAN =
        new TeamTalonFX("Subsystems.DriveTrain.LeftMain", Ports.LeftDriveFalconMainCAN);
    ITeamTalon rightDriveFalconSubCAN =
        new TeamTalonFX("Subsystems.DriveTrain.RightSub", Ports.RightDriveFalconSubCAN);
    ITeamTalon leftDriveFalconSub =
        new TeamTalonFX("Subsystems.DriveTrain.LeftSub", Ports.LeftDriveFalconSubCAN);
    return new DriveTrainSubsystem(
        rightDriveFalconMainCAN,
        leftDriveFalconMainCAN,
        rightDriveFalconSubCAN,
        leftDriveFalconSub);
  }

  @Override
  public void periodic() {
    printDrivetrainData();
  }

  private void printDrivetrainData() {
    SmartDashboard.putNumber("Subsystems.DriveTrain.leftPower", leftDriveFalconMain.get());
    SmartDashboard.putNumber("Subsystems.DriveTrain.rightPower", rightDriveFalconMain.get());
  }

  private double getCappedPower(double desired) {
    return Math.max(Math.min(1, desired), -1);
  }

  public void setMotorPowers(double leftPowerDesired, double rightPowerDesired, String reason) {
    leftPowerDesired = getCappedPower(leftPowerDesired);
    rightPowerDesired = getCappedPower(rightPowerDesired);
    leftDriveFalconMain.set(leftPowerDesired, reason);
    rightDriveFalconMain.set(rightPowerDesired, reason);
  }

  public double getLeftEncoder() {
    return leftDriveFalconMain.getSelectedSensorPosition(0);
  }

  public double getRightEncoder() {
    return rightDriveFalconMain.getSelectedSensorPosition(0);
  }

  public void stop(String reason) {
    setMotorPowers(0, 0, reason);
  }

  
}
