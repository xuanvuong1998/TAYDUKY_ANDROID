package vuong.hx.tayduky.enums;

public enum UserRole {
    ADMIN(0),
    ACTOR(1);

    private final int val;
    UserRole(int i) {
        val = i;
    }

    public int getVal(){
        return val;
    }
}
