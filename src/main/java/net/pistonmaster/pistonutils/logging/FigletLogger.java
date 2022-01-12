package net.pistonmaster.pistonutils.logging;

import com.github.dtmo.jfiglet.FigFontResources;
import com.github.dtmo.jfiglet.FigletRenderer;

import java.io.IOException;

@SuppressWarnings({"unused"})
public class FigletLogger {
    private FigletLogger() {}

    public static void logInBig(PistonLogger logger, String text) {
        try {
            final FigletRenderer figletRenderer = new FigletRenderer(FigFontResources.loadFigFontResource(FigFontResources.STANDARD_FLF));

            figletRenderer.setSmushMode(100);

            logger.info(figletRenderer.renderText(text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
