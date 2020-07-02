package vuong.hx.tayduky.models;

public class Character {
    private int id;
    private String name;
    private String image;
    private String defaultActor;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Character() {
    }

    public Character(int id, String name, String image, String defaultActor, int status) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.defaultActor = defaultActor;
        this.status = status;
    }
}
