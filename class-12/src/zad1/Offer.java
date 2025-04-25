package zad1;

public class Offer {
    private String localization;
    private final String country;
    private final String departureDate;
    private final String returnDate;
    private final String place;
    private final String price;
    private final String currency;

    public Offer(String data, String splitter) {
        String[] dataArr = data.split(splitter);

        this.localization = dataArr[0];
        this.country = dataArr[1];
        this.departureDate = dataArr[2];
        this.returnDate = dataArr[3];
        this.place = dataArr[4];
        this.price = dataArr[5];
        this.currency = dataArr[6];
    }

    public String getInsertStatement(int index){ return "VALUES(" + index + ", '" + this.localization + "', '" + this.country + "', '" + this.departureDate + "', '" + this.returnDate + "', '" + this.place + "', '" + this.price + "', '" + this.currency + "')"; }

    public String toString(){ return  this.country + " " + this.departureDate + " " + this.returnDate + " " +  this.place + " " + this.price + " " + this.currency; }

    public String getLocalization() { return localization; }

    public String getCountry() { return country; }

    public String getDepartureDate() { return departureDate; }

    public String getReturnDate() { return returnDate; }

    public String getPlace() { return place; }

    public String getPrice() { return price; }

    public String getCurrency() { return currency; }

    public void setLocalization(String localization){
        this.localization = localization;
    }
}
