package sample;

import javafx.beans.property.SimpleStringProperty;

public class TableViewObject {

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getProfessor() {
        return professor.get();
    }

    public SimpleStringProperty professorProperty() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor.set(professor);
    }

    public String getDay() {
        return day.get();
    }

    public SimpleStringProperty dayProperty() {
        return day;
    }

    public void setDay(String day) {
        this.day.set(day);
    }

    private SimpleStringProperty title;
    private SimpleStringProperty professor;
    private SimpleStringProperty day;

    public TableViewObject(String title, String professor, String day) {
        this.title = new SimpleStringProperty(title);
        this.professor = new SimpleStringProperty(professor);
        this.day = new SimpleStringProperty(day);
    }


}
