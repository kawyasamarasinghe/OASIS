package extended.ui.oasistravelplanner.model;

public class Mybookings_vehicles_model {

    String vehicleName,startingLocation, arrivalLocation, date, noOfPassengers;

    Mybookings_vehicles_model()
    {

    }

    public Mybookings_vehicles_model(String vehicleName, String startingLocation, String arrivalLocation, String date, String noOfPassengers) {
        this.vehicleName = vehicleName;
        this.startingLocation = startingLocation;
        this.arrivalLocation = arrivalLocation;
        this.date = date;
        this.noOfPassengers = noOfPassengers;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getStartingLocation() {
        return startingLocation;
    }

    public void setStartingLocation(String startingLocation) {
        this.startingLocation = startingLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(String noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }
}
