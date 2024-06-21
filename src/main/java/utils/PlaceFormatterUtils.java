package utils;

public class PlaceFormatterUtils {
	
	   public static String processCountryName(String countryPart) {
	        // Handle the case where "now" is present in the country name
	        if (countryPart.contains("now")) {
	            // Extract country name after "now" and remove brackets
	            String[] parts = countryPart.split("now")[1].trim().split(",");
	            return parts[parts.length - 1].trim().replaceAll("\\[|\\]", "");
	        }
	        return countryPart;
	    }

}
