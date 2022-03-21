// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HoodSubsystem;

public class HoodMoveCommand extends CommandBase {
  /** Creates a new HoodMoveCommand. */
  private final HoodSubsystem hood;
  private final DoubleSupplier leftStick;
  private final double maxSpeed;
  private double currentPosition;
  public HoodMoveCommand(HoodSubsystem hood, DoubleSupplier leftStick, double maxSpeed) {
    this.hood = hood;
    this.leftStick = leftStick;
    this.maxSpeed = maxSpeed;
    addRequirements(hood);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    currentPosition = hood.getPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentPosition += maxSpeed*leftStick.getAsDouble();
    currentPosition = Math.max(0, Math.min(1, currentPosition));
    //hood.setPosition(currentPosition);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   // hood.setPosition(hood.getPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
