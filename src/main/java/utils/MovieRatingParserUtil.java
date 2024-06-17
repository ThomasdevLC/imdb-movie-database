package utils;

public class MovieRatingParserUtil {

	/**
	 * Parses a rating string to a double, handling specific formats.
	 *
	 * @param ratingStr The rating string to parse.
	 * @return The parsed rating as a double, or 0.0 if parsing fails.
	 */
	public static double parseRating(String ratingStr) {
		if (ratingStr == null || ratingStr.trim().isEmpty()) {
			return 0.0;
		}

		// Replace commas with dots for decimal consistency
		String normalizedRatingStr = ratingStr.replace(',', '.');

		try {
			return Double.parseDouble(normalizedRatingStr);
		} catch (NumberFormatException e) {
			System.err.println("Unable to parse rating: " + ratingStr);
			return 0.0;
		}
	}
}
