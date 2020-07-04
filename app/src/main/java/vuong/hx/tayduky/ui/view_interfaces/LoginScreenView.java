package vuong.hx.tayduky.ui.view_interfaces;

public interface LoginScreenView extends ToastView{
    void goToHomeActivity(String username, String token, int role);
}
