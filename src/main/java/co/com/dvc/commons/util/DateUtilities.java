package co.com.dvc.commons.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtilities {

    private static final String FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

    public static Date formatStringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DD_MM_YYYY);
        if (date != null) {
            LocalDate localDate = LocalDate.parse(date, formatter);
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            return null;
        }
    }

    public static String formatDateString(String inputDate) {
        if (inputDate == null) {
            return null;
        }

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(FORMAT_DD_MM_YYYY);

        try {
            LocalDateTime dateTime = LocalDateTime.parse(inputDate, inputFormatter);
            return dateTime.format(outputFormatter);
        } catch (Exception e) {
            try {
                LocalDate date = LocalDate.parse(inputDate.substring(0, 10));
                return date.format(outputFormatter);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public static int numberOfDaysTwoDates(String fechaInicial, String fechaFinal) {
        LocalDate startDate = parseAnyDateFormat(fechaInicial);
        LocalDate endDate = parseAnyDateFormat(fechaFinal);

        if (startDate == null || endDate == null) {
            return -1;
        }

        return Integer.parseInt(String.valueOf(Math.abs(ChronoUnit.DAYS.between(startDate, endDate))));
    }

    private static LocalDate parseAnyDateFormat(String dateString) {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern(FORMAT_DD_MM_YYYY),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy"),
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_OFFSET_DATE
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(dateString, formatter);
            } catch (Exception e) {
            }
        }

        return null;
    }

    public static String formatDateToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DD_MM_YYYY);
        return formatter.format(date);
    }
}
