package utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class LocalDateUtils {
    public static LocalDate convertStringToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(dateString, formatter);
    }
    
    public static String convertLocalDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return localDate.format(formatter);
    }

    public static boolean isCurrentDateInRange(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();
        return !currentDate.isBefore(startDate) && !currentDate.isAfter(endDate);
    }
    
    public static String convertDateFormat(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return date.format(outputFormatter);
    }
}