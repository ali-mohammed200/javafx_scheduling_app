package Helper;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * DateConverter is a helper class used for data conversions
 */
public class DateConverter {
    private static final ZoneId zoneId = ZoneId.systemDefault();
    private static final ZonedDateTime zdt = ZonedDateTime.now(zoneId);
    private static final ZoneOffset z_offset = zdt.getOffset();

    /**
     * Function to check if an offsetdatetime is within business hours
     * - 8am - 10pm EST on Weekdays
     * @param odt
     * @return Boolean
     */
    public static Boolean withinBusinessHours(OffsetDateTime odt) {
        OffsetDateTime estOdt = odt.toInstant().atOffset(ZoneOffset.of("-05:00")); // EST
        LocalTime startTime = LocalTime.of(8, 0);  // 8:00 AM
        LocalTime endTime = LocalTime.of(22, 0);  // 10:00 PM

        DayOfWeek day = estOdt.getDayOfWeek();
        Boolean validDay = day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;

        LocalTime time = estOdt.toLocalTime();
        Boolean validTime = !time.isBefore(startTime) && !time.isAfter(endTime);

        return validTime && validDay;
    }

    /**
     * Function to build a time string
     * Uses 24 format
     * @param hour
     * @param min
     * @param unit
     * @return String
     */
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

    /**
     * Function to build offsetdatetime object
     * @param timeString
     * @param localDate
     * @return OffsetDateTime
     */
    public static OffsetDateTime buildOffsetDateTimeObject(String timeString, LocalDate localDate) {
        LocalTime localTime = LocalTime.parse(timeString);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZonedDateTime now = ZonedDateTime.now();
        ZoneOffset localOffset = now.getOffset();
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, localOffset);

        return offsetDateTime;
    }

    /**
     * Function to convert offsetdatetime from UTC to Local time
     * @param ofdt
     * @return OffsetDateTime
     */
    public static OffsetDateTime convertFromUTCtoLocal(OffsetDateTime ofdt) {
        return ofdt.toInstant().atOffset(z_offset);
    }


    /**
     * Function to convert offsetdatetime from local time to UTC
     * @param ofdt
     * @return OffsetDateTime
     */
    public static OffsetDateTime convertFromLocaltoUTC(OffsetDateTime ofdt) {
        return ofdt.toInstant().atOffset(ZoneOffset.of("+00:00"));
    }

    /**
     * Function to convert offsetdatetime to a readable format
     * @param ofdt
     * @return String
     */
    public static String readableDateFormat(OffsetDateTime ofdt) {
        return ofdt.format((DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a")));
    }
}
