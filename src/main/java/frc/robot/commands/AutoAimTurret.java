// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class AutoAimTurret extends CommandBase {
  TurretSubsystem turret;
  VisionSubsystem vision;
  double maxPower;
  double kP;
  /** Creates a new AutoAimTurret. */
  public AutoAimTurret(TurretSubsystem turret, VisionSubsystem vision, double maxPower, double kP) {
    this.turret = turret;
    this.vision = vision;
    this.maxPower = maxPower;
    this.kP = kP;
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (vision.isValid()) {
      turret.setMotorPower(Math.max(Math.min(kP * -vision.getLastSeenTx(), maxPower), -maxPower), "Auto aiming");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.stop("Stopped auto aiming");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
