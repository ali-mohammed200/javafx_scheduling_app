package Helper;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.*;

public class DateConverterTest {

    @Test
    public void testWithinBusinessHours_ValidTimeAndDay() {
        OffsetDateTime estTime = OffsetDateTime.of(
                LocalDate.of(2025, 5, 28), // Wednesday
                LocalTime.of(9, 0),        // 9 AM
                ZoneOffset.of("-05:00")    // EST
        );
        assertTrue(DateConverter.withinBusinessHours(estTime));
    }

    @Test
    public void testWithinBusinessHours_InvalidTime() {
        OffsetDateTime estTime = OffsetDateTime.of(
                LocalDate.of(2025, 5, 28), // Wednesday
                LocalTime.of(23, 0),       // 11 PM
                ZoneOffset.of("-05:00")
        );
        assertFalse(DateConverter.withinBusinessHours(estTime));
    }

    @Test
    public void testWithinBusinessHours_Weekend() {
        OffsetDateTime estTime = OffsetDateTime.of(
                LocalDate.of(2025, 5, 31), // Saturday
                LocalTime.of(10, 0),
                ZoneOffset.of("-05:00")
        );
        assertFalse(DateConverter.withinBusinessHours(estTime));
    }

    @Test
    public void testBuildTimeString_AM() {
        String result = DateConverter.buildTimeString("8", "30", "AM");
        assertEquals("8:30:00", result);
    }

    @Test
    public void testBuildTimeString_PM() {
        String result = DateConverter.buildTimeString("3", "15", "PM");
        assertEquals("15:15:00", result);
    }

    @Test
    public void testBuildTimeString_Midnight() {
        String result = DateConverter.buildTimeString("12", "00", "AM");
        assertEquals("00:00:00", result);
    }

    @Test
    public void testBuildTimeString_Noon() {
        String result = DateConverter.buildTimeString("12", "00", "PM");
        assertEquals("12:00:00", result);
    }

    @Test
    public void testBuildOffsetDateTimeObject() {
        String time = "14:45:00";
        LocalDate date = LocalDate.of(2025, 5, 28);
        OffsetDateTime odt = DateConverter.buildOffsetDateTimeObject(time, date);

        assertEquals(LocalDate.of(2025, 5, 28), odt.toLocalDate());
        assertEquals(LocalTime.of(14, 45), odt.toLocalTime());
    }

    @Test
    public void testConvertFromUTCtoLocal() {
        OffsetDateTime utc = OffsetDateTime.of(
                2025, 5, 28, 12, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime local = DateConverter.convertFromUTCtoLocal(utc);

        assertEquals(utc.toInstant(), local.toInstant());
        assertNotEquals(utc.getOffset(), local.getOffset());
    }

    @Test
    public void testConvertFromLocaltoUTC() {
        ZoneOffset localOffset = ZonedDateTime.now().getOffset();
        OffsetDateTime local = OffsetDateTime.of(
                2025, 5, 28, 12, 0, 0, 0, localOffset);
        OffsetDateTime utc = DateConverter.convertFromLocaltoUTC(local);

        assertEquals(local.toInstant(), utc.toInstant());
        assertEquals(ZoneOffset.UTC, utc.getOffset());
    }
}
