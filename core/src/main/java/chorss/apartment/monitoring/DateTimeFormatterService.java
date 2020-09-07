package chorss.apartment.monitoring;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateTimeFormatterService {

    private DateTimeFormatterService() {
    }

    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", new Locale("PL"));
    }
}