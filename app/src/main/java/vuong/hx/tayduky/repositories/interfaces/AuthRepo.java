package vuong.hx.tayduky.repositories.interfaces;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.AuthenticatedUser;

public interface AuthRepo {
    void authenticate(String username, String password, ApiCallBack<AuthenticatedUser> callBack);
}
