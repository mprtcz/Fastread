package com.mprtcz.fastread.logger;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * @author mprtcz
 */
public class FastreadGameLogger {
    private final static Logger logger = Logger.getLogger(FastreadGameLogger.class.getName());
    private static Handler handler = null;

    public static void initializeLogger() {
        try {
            handler = new FileHandler("Fastread.log", false);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Could not create file");
            e.printStackTrace();
        }
        Logger newLogger = Logger.getLogger("");
        handler.setFormatter(new MyFormatter());
        newLogger.addHandler(handler);
        newLogger.setLevel(Level.CONFIG);
    }


    private static class MyFormatter extends Formatter {
        private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");

        public String format(LogRecord record) {
            StringBuilder builder = new StringBuilder();
            builder.append(df.format(new Date(record.getMillis())))
                    .append(" - [")
                    .append(record.getLevel())
                    .append("][")
                    .append(getClassNameOnly(record.getSourceClassName()))
                    .append(": ")
                    .append(record.getSourceMethodName())
                    .append("]");
            if (!formatMessage(record).equals("")) {
                builder.append("\n").append(formatMessage(record));
            }
            builder.append("\n");
            return builder.toString();
        }

        private static String getClassNameOnly(String string) {
            String[] parts = string.split("\\.");
            return parts[parts.length - 1];
        }
    }
}
