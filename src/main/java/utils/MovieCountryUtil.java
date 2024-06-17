package utils;

public class MovieCountryUtil {

	public static String checkCountryFormat(String str) {
        if (str == null || str.trim().isEmpty() || str.trim().length() > 15) {
            return "";
        } else {
            return str.trim();
        }
    }
}
