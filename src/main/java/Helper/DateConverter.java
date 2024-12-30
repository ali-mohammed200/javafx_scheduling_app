package Helper;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    private static final ZoneId zoneId = ZoneId.systemDefault();
    private static final ZonedDateTime zdt = ZonedDateTime.now(zoneId);
    private static final ZoneOffset z_offset = zdt.getOffset();

    public static Boolean withinBusinessHours(OffsetDateTime odt) {
        OffsetDateTime estOdt = odt.toInstant().atOffset(ZoneOffset.of("-05:00")); // EST
        LocalTime startTime = LocalTime.of(8, 0);  // 8:00 AM
        LocalTime endTime = LocalTime.of(22, 0);  // 10:00 PM

        DayOfWeek day = estOdt.getDayOfWeek();
        Boolean validDay = day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;

        LocalTime time = estOdt.toLocalTime();
        Boolean validTime = !time.isBefore(startTime) && !time.isAfter(endTime);

        System.out.println("estTime" + estOdt);
        System.out.println("estTime local" + time);
        return validTime && validDay;
    }

    public static String buildTimeString(String hour, String min, String unit) {
        Integer intHour = Integer.parseInt(hour);
        if (intHour == 12 && unit == "AM") {
            hour = "00";
        } else if (intHour == 12 && unit == "PM") {
            hour = "12";
        } else if (unit == "PM") {
            hour = Integer.toString(intHour + 12);
        }
        return hour + ":" + min + ":" + "00";
    }

    public static OffsetDateTime buildOffsetDateTimeObject(String timeString, LocalDate localDate) {
        LocalTime localTime = LocalTime.parse(timeString);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZonedDateTime now = ZonedDateTime.now();
        ZoneOffset localOffset = now.getOffset();
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, localOffset);

        return offsetDateTime;
    }

    public static OffsetDateTime convertFromUTCtoLocal(OffsetDateTime ofdt) {
        return ofdt.toInstant().atOffset(z_offset);
    }


    public static OffsetDateTime convertFromLocaltoUTC(OffsetDateTime ofdt) {
        return ofdt.toInstant().atOffset(ZoneOffset.of("+00:00"));
    }

    public static String readableDateFormat(OffsetDateTime ofdt) {
        return ofdt.format((DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")));
    }
}
