package me.alexprogrammerde.pistonutils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

@SuppressWarnings({"unused"})
public class UpdateChecker {
    private final PistonLogger log;

    public UpdateChecker(PistonLogger log) {
        this.log = log;
    }

    public void getVersion(String url, final Consumer<String> consumer) {
        try (InputStream inputStream = new URL(url).openStream(); Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNext()) {
                consumer.accept(scanner.next());
            }
        } catch (IOException exception) {
            log.warning(ConsoleColor.RED_BOLD + "Cannot look for updates: " + exception.getMessage() + ConsoleColor.RESET);
        }
    }
}