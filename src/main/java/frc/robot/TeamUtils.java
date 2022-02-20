package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

import frc.robot.constants.Scalar;

public class TeamUtils {
  public static double getCurrentTime() {
    return 1.0 * System.nanoTime() / 1e9;
  }

  public static void sendToNetworkTable(String tableName, String key, Object value) {
    NetworkTable table = NetworkTableInstance.getDefault().getTable(tableName);
    if (table == null) {
      return;
    }
    NetworkTableEntry entry = table.getEntry(key);
    if (entry == null) {
      return;
    }
    entry.setValue(value);
  }

  public static Object getFromNetworkTable(String tableName, String key) {
    NetworkTable table = NetworkTableInstance.getDefault().getTable(tableName);
    if (table == null) {
      return null;
    }
    NetworkTableEntry entry = table.getEntry(key);
    if (entry == null) {
      return null;
    }
    NetworkTableValue value = entry.getValue();
    if (value == null) {
      return null;
    }
    return value.getValue();
  }

  public static boolean checkTolerance(double currentValue, double targetValue, double epsilon) {
    if (Math.abs(currentValue - targetValue) <= epsilon) return true;
    else {
      return false;
    }
  }

  public static double map(double value,
        double start1, double end1,
        double start2, double end2) {

    if (Math.abs(end1 - start1) < Scalar.EPSILON) {
        throw new ArithmeticException("/ 0");
    }

    double offset = start2;
    double ratio = (end2 - start2) / (end1 - start1);
    return ratio * (value - start1) + offset;
  }

}