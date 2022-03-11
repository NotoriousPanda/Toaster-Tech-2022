package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HangSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class HangCommand extends CommandBase{

    private HangSubsystem hanger;

    private TurretSubsystem turret;
  
    private DoubleSupplier leftPower;
    private DoubleSupplier rightPower;
  
    private BooleanSupplier dpadUp;
  
    boolean overridden = false;
  
    public HangCommand(
        HangSubsystem hanger,
        DoubleSupplier leftPower,
        DoubleSupplier rightPower,
        TurretSubsystem turret,
        BooleanSupplier dpadUp) {
      this.hanger = hanger;
      this.leftPower = leftPower;
      this.rightPower = rightPower;
      this.turret = turret;
      this.dpadUp = dpadUp;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(hanger);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}
  
    // Called every time the scheduler runs while the command is scheduled.
    // Yesn't
    @Override
    public void execute() {
      if (dpadUp.getAsBoolean()) {
        overridden = true;
      }
      if (turret.isReadyToHang() || overridden) {
        this.hanger.setLeftHanger(this.leftPower.getAsDouble());
        this.hanger.setRightHanger(this.rightPower.getAsDouble());
      } else {
        this.hanger.stop();
      }
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
  }
