package Helper;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    private static ZoneId zoneId = ZoneId.systemDefault();
    private static ZonedDateTime zdt = ZonedDateTime.now(zoneId);
    private static ZoneOffset z_offset = zdt.getOffset();

    public static OffsetDateTime convertFromUTCtoLocal(OffsetDateTime ofdt){
        return ofdt.toInstant().atOffset(z_offset);
    }


    public static OffsetDateTime convertFromLocaltoUTC(OffsetDateTime ofdt){
        return ofdt.toInstant().atOffset(ZoneOffset.of("+00:00"));
    }

    public static String formatForTimestamp(OffsetDateTime ofdt) {
        return ofdt.format((DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    }

    public static void main(String[] args){
        System.out.println(OffsetDateTime.now());
        System.out.println(OffsetDateTime.now().toInstant().atOffset(ZoneOffset.of("+00:00")));
        System.out.println(ZoneOffset.of("+00:00"));
    }
}



//        System.out.println(OffsetDateTime.now().toString());
//
//        System.out.println(zdt);
//
//        OffsetDateTime ts = rs.getObject("Create_Date", OffsetDateTime.class);
//        System.out.println(ts.toInstant().atOffset(z_offset));