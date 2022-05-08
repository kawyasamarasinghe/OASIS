package extended.ui.oasistravelplanner;

public class my_bookingsModel {
    String restaurantName,date,time,type,meal,table;

    my_bookingsModel(){

    }

    public my_bookingsModel(String restaurantName, String date, String time, String type, String meal, String table) {
        this.restaurantName = restaurantName;
        this.date = date;
        this.time = time;
        this.type = type;
        this.meal = meal;
        this.table = table;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
