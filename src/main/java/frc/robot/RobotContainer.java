// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.constants.LogitechControllerButtons;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.RealIntakeSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem driveTrain = DriveTrainSubsystem.Create();
  private final ShooterSubsystem shooter = ShooterSubsystem.Create();
  private final IntakeSubsystem intake = IntakeSubsystem.Create();
  private final VisionSubsystem visionSubsystem = VisionSubsystem.Create();
  private final TurretSubsystem turretSubsystem = TurretSubsystem.Create(visionSubsystem);
  private final RealIntakeSubsystem realIntake = RealIntakeSubsystem.Create();
  private final MidtakeSubsystem midtake = MidtakeSubsystem.Create();
  private final HangSubsystem hanger = HangSubsystem.Create();
  private final HoodSubsystem hood = new HoodSubsystem();
  private final HangPistonSubsystem tilthang = HangPistonSubsystem.Create();

  public static final Joystick driveController = new Joystick(0);
  public static final Joystick secondaryJoystick = new Joystick(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. 
   * @throws IOException
   * @throws SecurityException */
  public RobotContainer() throws SecurityException {
    Logs.Init();
    // Configure the button bindings
    configurePrimaryController();
    configureSecondaryController();
  }


  private void configurePrimaryController() {
    driveTrain.setDefaultCommand(new TankDriveCommand(driveTrain, () -> driveController.getY(), () -> driveController.getThrottle()));
  }

  private void configureSecondaryController() {
    JoystickButton x = new JoystickButton(secondaryJoystick, LogitechControllerButtons.x);
    JoystickButton y = new JoystickButton(secondaryJoystick, LogitechControllerButtons.y);
    JoystickButton a = new JoystickButton(secondaryJoystick, LogitechControllerButtons.a);
    JoystickButton b = new JoystickButton(secondaryJoystick, LogitechControllerButtons.b);
    JoystickButton leftTrigger = new JoystickButton(secondaryJoystick, LogitechControllerButtons.triggerLeft);
    JoystickButton rightTrigger = new JoystickButton(secondaryJoystick, LogitechControllerButtons.triggerRight);
    POVButton leftDPad = new POVButton(secondaryJoystick, LogitechControllerButtons.left);
    POVButton rightDPad = new POVButton(secondaryJoystick, LogitechControllerButtons.right);
    POVButton dPadUp = new POVButton(secondaryJoystick, LogitechControllerButtons.up);
    POVButton dPadDown = new POVButton(secondaryJoystick, LogitechControllerButtons.down);
    JoystickButton start = new JoystickButton(secondaryJoystick, LogitechControllerButtons.start);
    JoystickButton home = new JoystickButton(secondaryJoystick, LogitechControllerButtons.home);
    JoystickButton mode = new JoystickButton(secondaryJoystick, LogitechControllerButtons.bumperRight);
    JoystickButton bumperleft = new JoystickButton(secondaryJoystick, LogitechControllerButtons.bumperLeft);

    //x.whileHeld(new ShooterCommand(shooter, 0.3);
    x.whileHeld(new MidtakeCommand(midtake, 0.25));
   // b.whenPressed(new LowerIntakeCommand(realIntake));
    a.whileHeld(new RealIntakeCommand(realIntake, -1));
    y.whileHeld(new IntakeCommand(intake, -0.69));
    mode.whileHeld(new IntakeCommand(intake, -0.81));
    b.whenPressed(new ToggleIntake(realIntake));
    leftTrigger.whileHeld(new HangDownCommand(hanger, 0.9));
    rightTrigger.whileHeld(new HangUpCommand(hanger, 1));
    leftDPad.whileHeld(new TurretLeftCommand(turretSubsystem, 0.05));
    rightDPad.whileHeld(new TurretRightCommand(turretSubsystem, 0.05));

    start.whileHeld(new AutoAimTurret(turretSubsystem, visionSubsystem, 0.1, 0.005));
    bumperleft.whileHeld(new HomeTurret(turretSubsystem, 0.1, 0.005));
    home.whenPressed(new tilthangCommand(tilthang));

    dPadUp.whenPressed(new HoodMoveUp(hood));
    dPadDown.whenPressed(new HoodMoveDown(hood));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous+

  
    //return new IntakeCommand(intake, -0.66) ;
    return new AutonomousCommand(driveTrain, intake);
    
    }
}
