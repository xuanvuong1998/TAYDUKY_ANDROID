package vuong.hx.tayduky.models;

import java.io.Serializable;

public class Character implements Serializable {
    
    private long id;
    private String name;
    private String image;
    private String defaultActor;
    private long status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", defaultActor='" + defaultActor + '\'' +
                ", status=" + status +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDefaultActor() {
        return defaultActor;
    }

    public void setDefaultActor(String defaultActor) {
        this.defaultActor = defaultActor;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Character() {
    }

    public Character(long id, String name, String image, String defaultActor, long status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.defaultActor = defaultActor;
        this.status = status;
    }
}
