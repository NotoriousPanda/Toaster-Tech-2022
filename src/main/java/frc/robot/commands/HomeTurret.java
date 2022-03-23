// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class HomeTurret extends CommandBase {
  TurretSubsystem turret;
  double maxPower;
  double kP;
  /** Creates a new HomeTurret. */
  public HomeTurret(TurretSubsystem turret, double maxPower, double kP) {
    this.turret = turret;
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
      turret.setMotorPower(Math.max(Math.min(kP * -turret.getTurretEncoder(), maxPower), -maxPower), "Auto homing");
  }
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.stop("Stopped autohome");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
