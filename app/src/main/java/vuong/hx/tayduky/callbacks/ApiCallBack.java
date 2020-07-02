package vuong.hx.tayduky.callbacks;

public interface ApiCallBack<T> {
    void onSuccess(T t);

    void onFail(String message);
}
