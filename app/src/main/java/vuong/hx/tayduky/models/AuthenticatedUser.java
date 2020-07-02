package vuong.hx.tayduky.models;

public class AuthenticatedUser {
    private String token;
    private int role;

    public AuthenticatedUser(String token, int role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public AuthenticatedUser() {
    }
}
