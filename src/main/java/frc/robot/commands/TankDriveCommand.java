// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainSubsystem;

public class TankDriveCommand extends CommandBase {
  DriveTrainSubsystem driveTrain;
  DoubleSupplier rightStick;
  DoubleSupplier leftStick;

  final double deadZoneConstant = 0.02;

  /** Creates a new TankDriveCommand. */
  public TankDriveCommand(DriveTrainSubsystem driveTrain, DoubleSupplier leftStick, DoubleSupplier rightStick) {
    this.driveTrain = driveTrain;
    this.rightStick = rightStick;
    this.leftStick = leftStick;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveTrain.setMotorPowers(deadZone(this.leftStick.getAsDouble()), deadZone(this.rightStick.getAsDouble()), "Joysticks Said So");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  double deadZone(double input) {
    if (Math.abs(input) <= deadZoneConstant) return 0;
    return input;
  }
}
