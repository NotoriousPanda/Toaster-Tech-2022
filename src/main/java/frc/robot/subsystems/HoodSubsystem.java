// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HoodSubsystem extends SubsystemBase {
  private final Servo actuator;
  /** Creates a new HoodSubsystem. */
  public HoodSubsystem() {
    actuator = new Servo(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Subsystems.Hood.Position", actuator.get());
  }

  public void setPosition(double location) {
    actuator.set(Math.min(1, Math.max(0, location)));
  }

  public double getPosition() {
    return actuator.get();
  }
}
