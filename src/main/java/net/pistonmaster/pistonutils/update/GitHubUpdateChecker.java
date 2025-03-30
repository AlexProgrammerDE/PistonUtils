package net.pistonmaster.pistonutils.update;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused"})
public class GitHubUpdateChecker {
    public SemanticVersion getVersion(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) URI.create(url).toURL().openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "PistonUtils");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.setDoOutput(true);

        try (InputStream is = con.getInputStream();
             InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            var gson = new Gson();
            var response = gson.fromJson(isr, JsonObject.class);
            if (response == null) {
                throw new IOException("Failed to parse response");
            }

            var tagName = response.get("tag_name");
            if (tagName == null) {
                throw new IOException("Failed to get tag name from response");
            }

            return SemanticVersion.fromString(tagName.getAsString());
        }
    }
}