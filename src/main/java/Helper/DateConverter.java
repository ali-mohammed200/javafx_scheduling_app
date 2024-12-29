package Helper;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    private static ZoneId zoneId = ZoneId.systemDefault();
    private static ZonedDateTime zdt = ZonedDateTime.now(zoneId);
    private static ZoneOffset z_offset = zdt.getOffset();

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
    public static String buildTimeString(String hour, String min, String unit){
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
    public static OffsetDateTime buildOffsetDateTimeObject(String timeString, LocalDate localDate){
        LocalTime localTime = LocalTime.parse(timeString);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZonedDateTime now = ZonedDateTime.now();
        ZoneOffset localOffset = now.getOffset();
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, localOffset);

        return offsetDateTime;
    }
    public static OffsetDateTime convertFromUTCtoLocal(OffsetDateTime ofdt){
        return ofdt.toInstant().atOffset(z_offset);
    }


    public static OffsetDateTime convertFromLocaltoUTC(OffsetDateTime ofdt){
        return ofdt.toInstant().atOffset(ZoneOffset.of("+00:00"));
    }

    public static String formatForTimestamp(OffsetDateTime ofdt) {
        return ofdt.format((DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    public static void main(String[] args){
        String t = buildTimeString("05", "30", "PM");
        LocalDate localDate = LocalDate.of(2024, 12, 21);
        System.out.println(buildOffsetDateTimeObject(t, localDate));


        System.out.println(OffsetDateTime.now());
        System.out.println(OffsetDateTime.now().toInstant().atOffset(ZoneOffset.of("+00:00")));
        System.out.println(ZoneOffset.of("+00:00"));
        System.out.println(formatForTimestamp(convertFromLocaltoUTC(OffsetDateTime.now())));
        System.out.println(formatForTimestamp(OffsetDateTime.now()));
        System.out.println(formatForTimestamp(convertFromUTCtoLocal(OffsetDateTime.now())));
    }
}



//        System.out.println(OffsetDateTime.now().toString());
//
//        System.out.println(zdt);
//
//        OffsetDateTime ts = rs.getObject("Create_Date", OffsetDateTime.class);
//        System.out.println(ts.toInstant().atOffset(z_offset));

//        import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.LocalDateTime;
//import java.time.OffsetDateTime;
//import java.time.ZoneOffset;
//
//        public class Main {
//            public static void main(String[] args) {
//                // Example LocalDate and String
//                LocalDate localDate = LocalDate.of(2024, 12, 21); // Your LocalDate
//                String timeString = "14:30:00"; // Your time string (e.g., HH:mm:ss)
//
//                // Parse the string into LocalTime
//                LocalTime localTime = LocalTime.parse(timeString);
//
//                // Combine LocalDate and LocalTime into LocalDateTime
//                LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
//
//                // Use a ZoneOffset to create OffsetDateTime
//                ZoneOffset offset = ZoneOffset.of("+00:00"); // Change as needed
//                OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, offset);
//
//                // Print the result
//                System.out.println("OffsetDateTime: " + offsetDateTime);
//            }
//        }