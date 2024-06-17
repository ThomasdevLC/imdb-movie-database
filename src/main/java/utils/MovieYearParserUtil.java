package utils;

public class MovieYearParserUtil {

	
	public static int parseYear(String yearStr) {
        String[] parts = yearStr.split("\\D+");
        return Integer.parseInt(parts[parts.length - 1]);
    }
	
}
