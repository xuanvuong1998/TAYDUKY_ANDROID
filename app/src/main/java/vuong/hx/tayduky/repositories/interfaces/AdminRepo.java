package vuong.hx.tayduky.repositories.interfaces;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Admin;

public interface AdminRepo {
    void getInfoById(String token, String adminId, ApiCallBack<Admin> callBack);
}
