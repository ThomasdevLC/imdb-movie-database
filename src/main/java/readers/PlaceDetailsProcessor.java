package readers;

public class PlaceDetailsProcessor {

    public static void processPlaceDetails(String placeInfo) {
        String[] placeDetails = placeInfo.split(",\\s*", 3);
        String city = null;
        String state = null;
        String countryName = null;

        if (placeDetails.length == 3) {
            city = placeDetails[0].trim();
            state = placeDetails[1].trim();
            countryName = processCountryName(placeDetails[2].trim());
        } else if (placeDetails.length == 2) {
            city = placeDetails[0].trim();
            countryName = processCountryName(placeDetails[1].trim());
        }

        System.out.println("City: " + city);
        if (state != null) {
            System.out.println("State: " + state);
        }
        System.out.println("Country: " + countryName);
        System.out.println("-----------------------");
    }

    private static String processCountryName(String countryPart) {
        // Handle the case where "now" is present in the country name
        if (countryPart.contains("now")) {
            // Extract country name after "now" and remove brackets
            String[] parts = countryPart.split("now")[1].trim().split(",");
            return parts[parts.length - 1].trim().replaceAll("\\[|\\]", "");
        }
        return countryPart;
    }

    public static void main(String[] args) {
        // Example data
        String[] placeInfos = {
            "Guangdong, China",
            "Genoa, Liguria, Italy",
            "Glace Bay, Nova Scotia, Canada",
            "Krakau, Galicia, Austria-Hungary [now Kraków, Malopolskie, Poland]",
            "Krakau, Galicia, Austria-Hungary [now test, Test]",

            "Budapest, Austria-Hungary [now Hungary]"
        };

        // Process each place info
        for (String placeInfo : placeInfos) {
            processPlaceDetails(placeInfo);
        }
    }
}
