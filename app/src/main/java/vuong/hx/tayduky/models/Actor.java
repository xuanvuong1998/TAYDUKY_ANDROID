package vuong.hx.tayduky.models;

public class Actor {
    private String username;
    private String name;
    private String phone;
    private String email;
    private String image;
    private String description;
    private String address;
    private String joinDate;
    private String password;

    public Actor() {

    }

    public Actor(String username, String name, String phone, String email, String image, String description, String address, String joinDate, String password) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.description = description;
        this.address = address;
        this.joinDate = joinDate;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", joinDate='" + joinDate + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
