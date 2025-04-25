package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TravelData {
    private final List<Offer> offerList;

    public TravelData(File dataDir) {
        this.offerList = new ArrayList<>();
        String[] files = dataDir.list((dir, name) -> name.endsWith(".txt"));

        if(files != null) {
            for (String file : files) {
                try (Stream<String> lines =  Files.lines(Paths.get(dataDir + "/" + file))){
                    lines.forEach(line -> this.offerList.add(new Offer(line.trim(), "\t")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<Offer> getOfferList(){
        return this.offerList;
    }

    public List<String> getOffersDescriptionsList(String localeString, String dateFormatString) {
        return offerList.stream().map(e -> getOfferDescription(e, localeString, dateFormatString, " ", "")).collect(Collectors.toList());
    }

    public static String getOfferDescription(Offer offer, String localeString, String dateFormatString, String divider, String newLoc){
        Locale locale = Locale.forLanguageTag(localeString.replace('_', '-'));
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString, locale);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("zad1.info", locale);
        NumberFormat numberFormat = NumberFormat.getInstance(locale);

        try {
            String translatedDepartureDate = dateFormat.format(new SimpleDateFormat("yyyy-MM-dd").parse(offer.getDepartureDate()));
            String translatedReturnDate = dateFormat.format(new SimpleDateFormat("yyyy-MM-dd").parse(offer.getReturnDate()));
            String translatedPlace = resourceBundle.getString(offer.getPlace());

            NumberFormat offerNumberFormat = NumberFormat.getInstance(Locale.forLanguageTag(offer.getLocalization().replace('_', '-')));
            double price = offerNumberFormat.parse(offer.getPrice()).doubleValue();
            String translatedPrice = numberFormat.format(price);

            Locale offerLocale = Locale.forLanguageTag(offer.getLocalization().replace('_', '-'));
            String translatedLocation = "";

            for (String isoCountry : Locale.getISOCountries()) {
                Locale localeA = new Locale("", isoCountry);
                if (localeA.getDisplayCountry(offerLocale).equalsIgnoreCase(offer.getCountry())) {
                    translatedLocation = localeA.getDisplayCountry(locale);
                    break;
                }
            }

            if(!newLoc.isEmpty()){
                return newLoc + divider + translatedLocation + divider + translatedDepartureDate + divider +
                        translatedReturnDate + divider + translatedPlace + divider + translatedPrice + divider + offer.getCurrency();
            } else {
                return translatedLocation + divider + translatedDepartureDate + divider +
                        translatedReturnDate + divider + translatedPlace + divider + translatedPrice + divider + offer.getCurrency();
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
