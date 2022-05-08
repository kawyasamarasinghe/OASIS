package extended.ui.oasistravelplanner.model;

public class MemoriesModel {
    String date, title, place, description;

    MemoriesModel(){

    }

    public MemoriesModel(String title, String place, String date, String description){
        this.title = title;
        this.place = place;
        this.date = date;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
