package extended.ui.oasistravelplanner;

public class DistanceCalculations {
    protected double rad2deg(double distance) {
        return(distance * 180.0/Math.PI);
    }

    //Convert degree to radian
    protected double deg2rad(double lat1) {
        return(lat1*Math.PI/180.0);
    }
}
