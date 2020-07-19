package vuong.hx.tayduky.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import vuong.hx.tayduky.helpers.LogHelper;

public class FirebaseMessService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        LogHelper.printAssert("MESSAGE  FIREBASE" + remoteMessage.getNotification().getBody());
    }

    private void showNotification(String title, String mess){

    }
}
