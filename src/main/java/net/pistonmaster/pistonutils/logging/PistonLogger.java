package net.pistonmaster.pistonutils.logging;

import java.util.function.Consumer;

/**
 * Alternative logger that wraps quickly around loggers using consumers!
 */
@SuppressWarnings({"unused"})
public class PistonLogger {
    private final Consumer<String> info;
    private final Consumer<String> warning;

    public PistonLogger(Consumer<String> info, Consumer<String> warning) {
        this.info = info;
        this.warning = warning;
    }

    public void info(String message) {
        info.accept(message);
    }

    public void warning(String message) {
        warning.accept(message);
    }
}
