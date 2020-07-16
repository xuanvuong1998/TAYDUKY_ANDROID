package vuong.hx.tayduky.models;

import java.io.Serializable;

public class Challenge implements Serializable {
    private long id;
    private String name;
    private String description;
    private String location;
    private String startDate;
    private String endDate;
    private long shootTimes;

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", shootTimes=" + shootTimes +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getShootTimes() {
        return shootTimes;
    }

    public void setShootTimes(long shootTimes) {
        this.shootTimes = shootTimes;
    }

    public Challenge() {
    }

    public Challenge(long id, String name, String description, String location, String startDate, String endDate, long shootTimes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.shootTimes = shootTimes;
    }


}
