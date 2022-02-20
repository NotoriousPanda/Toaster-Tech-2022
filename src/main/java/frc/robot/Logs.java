package frc.robot;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import edu.wpi.first.wpilibj.Filesystem;
public class Logs {
    private static final String DIR = "/logs/";
    private static Logger logger;
    private static FileHandler file;
    private static SimpleFormatter formatter;

    public static void Init() {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.SEVERE);

        try {
            String local = Filesystem.getDeployDirectory().toPath().toString();

            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String filename = local + DIR + timeFormat.format(date) + ".log";

            file = new FileHandler(filename);
            formatter = new SimpleFormatter();
            file.setFormatter(formatter);  
            logger.addHandler(file);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Logger getLogger() {
        if (logger == null) {
            Logs.Init();
        }
        return logger;
    }

    public static String format(String msg) {
        return "[" + TeamUtils.getCurrentTime() + "]: " + msg;
    }

    public static void info(String msg) {
        getLogger().info(format(msg));
    }

    public static void warning(String msg) {
        getLogger().warning(msg);
    }

    public static void severe(String msg) {
        getLogger().severe(msg);
    }

    public static void fine(String msg) {
        getLogger().fine(msg);
    }

    public static void finer(String msg) {
        getLogger().finer(msg);
    }

    public static void finest(String msg) {
        getLogger().finest(msg);
    }
}
