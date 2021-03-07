package net.pistonmaster.pistonutils.logging;

import com.google.common.base.Preconditions;

import java.util.logging.Logger;

/**
 * Alternative logger that allows the usage of the Java Utils logger and the slf4 logger!
 */
@SuppressWarnings({"unused"})
public class PistonLogger {
    private Logger log1 = null;
    private org.slf4j.Logger log2 = null;

    public PistonLogger(Object log) {
        Preconditions.checkNotNull(log, "Logger can't be null!");
        Preconditions.checkArgument(log instanceof Logger || log instanceof org.slf4j.Logger, "The object isn't a supported logger!");

        if (log instanceof Logger) {
            log1 = (Logger) log;
        } else {
            log2 = (org.slf4j.Logger) log;
        }
    }

    public void info(String message) {
        if (log1 != null) {
            log1.info(message);
        } else {
            log2.info(message);
        }
    }

    public void warning(String message) {
        if (log1 != null) {
            log1.warning(message);
        } else {
            log2.info(message);
        }
    }
}
