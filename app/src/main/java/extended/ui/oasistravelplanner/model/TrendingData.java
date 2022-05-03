package extended.ui.oasistravelplanner.model;

public class TrendingData {

    String placeName;
    String city;
    String country;

    public TrendingData(String placeName, String city, String country) {
        this.placeName = placeName;
        this.city = city;
        this.country = country;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
