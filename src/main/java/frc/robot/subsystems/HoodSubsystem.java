// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
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
  }

  public void setPosition(double location) {
    actuator.set(Math.max(1, Math.min(0, location)));
  }

  public double getPosition() {
    return actuator.get();
  }
}
