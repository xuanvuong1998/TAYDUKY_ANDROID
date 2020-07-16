package vuong.hx.tayduky.models;

import java.io.Serializable;

public class Tool implements Serializable {
    private int id;
    private String name;
    private String description;
    private String image;
    private int quantity;

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Tool() {
    }

    public Tool(int id, String name, String description, String image, int quantity, int status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.status = status;
    }

    private int status;
}
