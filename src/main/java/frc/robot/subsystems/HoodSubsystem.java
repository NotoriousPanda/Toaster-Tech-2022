// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.print.attribute.standard.PresentationDirection;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HoodSubsystem extends SubsystemBase {
  private final Servo actuator;
  private final double[] presets = {0, 0.33, 0.66, 1};
  private int currentPreset = 0;
  /** Creates a new HoodSubsystem. */
  public HoodSubsystem() {
    actuator = new Servo(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Subsystems.Hood.Position", actuator.get());
  }

  public void nextPreset() {
    currentPreset++;
    if (currentPreset >= presets.length) {
      currentPreset = 0;
    }
    actuator.set(presets[currentPreset]);
  }

  public void lastPreset() {
    currentPreset--;
    if (currentPreset < 0) {
      currentPreset = presets.length - 1;
    }
    actuator.set(presets[currentPreset]);
  }

  public double getPosition() {
    return actuator.get();
  }
}
