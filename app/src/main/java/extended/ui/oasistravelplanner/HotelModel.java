package extended.ui.oasistravelplanner;

public class HotelModel {

    String CheckInDate;
    String CheckOutDate;
    String HotelName;
    String Price;

    HotelModel(){

    }


    public HotelModel(String checkInDate, String checkOutDate, String hotelName, String price) {
        this.CheckInDate = checkInDate;
        this.CheckOutDate = checkOutDate;
        this.HotelName = hotelName;
        this.Price = price;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.CheckInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return CheckOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.CheckOutDate = checkOutDate;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        this.HotelName = hotelName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = price;
    }




}
