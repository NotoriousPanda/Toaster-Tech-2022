package frc.robot.subsystems;

import frc.robot.TeamUtils;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionSubsystem extends SubsystemBase {

  private double lastSeenTx = 4026;
  private boolean tv = false;
  private double lastSeenTy = 4026;
  private double lastTimeTargetSeen = -9999999;
  private double tvRecentTime =
      0.25; // maximum time before tvRecent is set back to false after not seeing any targets
  private boolean ballSeen = false;
  private double ballX = 0;
  private double ballY = 0;

  // If object dependencies are introduced, then refactor to provide
  // constructor that injects all its object dependencies and disallow
  // default constructor (see other classes for implementation)
  protected VisionSubsystem() {}

  public static VisionSubsystem Create() {
    return new VisionSubsystem();
  }

  @Override
  public void periodic() {
    try {
      boolean tv = (double) TeamUtils.getFromNetworkTable("limelight", "tv") >= 1;
      this.tv = tv;
      if (tv) {
        this.lastTimeTargetSeen = Timer.getFPGATimestamp();
        SmartDashboard.putNumber("Subsystems.Vision.LastTimeSeen", lastTimeTargetSeen);
        double lastSeenTx = (double) TeamUtils.getFromNetworkTable("limelight", "tx");
        double lastSeenTy = (double) TeamUtils.getFromNetworkTable("limelight", "ty");
        this.lastSeenTx = lastSeenTx;
        this.lastSeenTy = lastSeenTy;
      }
    } catch (Exception e) {

    }

    try {
      boolean ballSeen = (boolean) TeamUtils.getFromNetworkTable("ballVision", "hasTarget");
      this.ballSeen = ballSeen;
      if (ballSeen) {
        double ballX = (double) TeamUtils.getFromNetworkTable("ballVision", "targetX");
        double ballY = (double) TeamUtils.getFromNetworkTable("ballVision", "targetY");
        this.ballX = ballX;
        this.ballY = ballY;
      }
    } catch (Exception e) {

    }
  }

  public double getLastSeenTx() {
    return this.lastSeenTx;
  }

  public double getLastSeenTy() {
    return this.lastSeenTy;
  }

  public boolean getTv() {
    return this.tv;
  }

  public boolean isValid() {
    return !((Timer.getFPGATimestamp() - this.lastTimeTargetSeen) > this.tvRecentTime);
  }

  /*
   * -1 is far left, +1 is far right, 0 is the center
   */
  public double getBallX() {
    return this.ballX;
  }

  /*
   * -1 is the bottom, +1 is the top, 0 is the center
   */
  public double getBallY() {
    return this.ballY;
  }
  /*
   * returns if the pi sees a ball or not
   */
  public boolean getBallSeen() {
    return this.ballSeen;
  }
  public void targetPosition() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");  }
}