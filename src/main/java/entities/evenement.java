package entities;

import java.util.Date;

public class evenement {
    private static int event_id;
    private String event_name;
    private String event_theme;

    public evenement(int event_id, String event_name, String event_theme,Date event_date) {
        this.event_id = event_id;
        this.event_name = event_name;
        this.event_theme = event_theme;
    }

    public evenement(String event_name, String event_theme) {
        this.event_name = event_name;
        this.event_theme = event_theme;

    }

    public evenement() {

    }

    public static int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_theme() {
        return event_theme;
    }

    public void setEvent_theme(String event_theme) {
        this.event_theme = event_theme;
    }



    @Override
    public String toString() {
        return "evenement{" +
                "event_id=" + event_id +
                ", event_name='" + event_name + '\'' +
                ", event_theme='" + event_theme  +
                '}';
    }
}
