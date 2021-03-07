package net.pistonmaster.pistonutils.logging;

import com.github.dtmo.jfiglet.FigFontResources;
import com.github.dtmo.jfiglet.FigletRenderer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

@SuppressWarnings({"unused"})
public class FigletLogger {
    private FigletLogger() {}

    public static void logInBig(JavaPlugin plugin) {
        try {
            final FigletRenderer figletRenderer = new FigletRenderer(FigFontResources.loadFigFontResource(FigFontResources.STANDARD_FLF));

            figletRenderer.setSmushMode(100);

            plugin.getLogger().info(figletRenderer.renderText(plugin.getDescription().getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
