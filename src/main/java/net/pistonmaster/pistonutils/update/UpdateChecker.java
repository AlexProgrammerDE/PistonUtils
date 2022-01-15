package net.pistonmaster.pistonutils.update;

import net.pistonmaster.pistonutils.logging.ConsoleColor;
import net.pistonmaster.pistonutils.logging.PistonLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@SuppressWarnings({"unused"})
public class UpdateChecker {
    private final PistonLogger log;

    public UpdateChecker(PistonLogger log) {
        this.log = log;
    }

    public void getVersion(String url, final Consumer<String> consumer) {
        try {
            consumer.accept(readURL(url, 5000));
        } catch (IOException exception) {
            log.info(ConsoleColor.RED_BOLD + "Cannot look for updates: " + exception.getMessage() + ConsoleColor.RESET);
        }
    }

    private String readURL(String url, int timeout) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "PistonUtils");
        con.setConnectTimeout(timeout);
        con.setReadTimeout(timeout);
        con.setDoOutput(true);

        try (InputStream is = con.getInputStream()) {
            return new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining());
        }
    }
}