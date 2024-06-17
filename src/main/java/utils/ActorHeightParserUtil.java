package utils;

public class ActorHeightParserUtil {

    /**
     * Parses a height string to a double, handling specific formats.
     *
     * @param heightStr The height string to parse.
     * @return The parsed height as a double in meters, or 0.0 if parsing fails.
     */
	public static double parseHeight(String heightStr) {
	    if (heightStr == null || heightStr.trim().isEmpty()) {
	        return 0.0;
	    }

	    // Remove any non-breaking space characters (like ' ')
	    heightStr = heightStr.replace("\u202F", " ");

	    // Convert commas to dots for decimal separation consistency
	    heightStr = heightStr.replace(",", ".");

	    // Remove unwanted characters (non-numeric, non-dot)
	    heightStr = heightStr.replaceAll("[^\\d.]", "");

	    // Check if the string is now a valid numeric format
	    if (!heightStr.isEmpty()) {
	        try {
	            // Parse as double
	            return Double.parseDouble(heightStr);
	        } catch (NumberFormatException e) {
	            System.err.println("Unable to parse height: " + heightStr);
	        }
	    }

	    // Return 0.0 if parsing fails or format doesn't match
	    System.err.println("Invalid height format: " + heightStr);
	    return 0.0;
	}
	  public static void main(String[] args) {
	        // Test parsing height
	        String heightStr = "6′ 2½″ (1.89 m)";
	        double height = parseHeight(heightStr);
	        System.out.println("Parsed height: " + height + " meters");
	    }

}

