package vuong.hx.tayduky.helpers;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import vuong.hx.tayduky.callbacks.NotifCallBack;
import vuong.hx.tayduky.firebase.FirebaseHelper;
import vuong.hx.tayduky.models.SceneRoleFullInfo;


/*
To hide this warning and ensure your app does not break, you need to add the following code to your app before calling any other Cloud Firestore methods:

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
        .setTimestampsInSnapshotsEnabled(true)
        .build();
    firestore.setFirestoreSettings(settings);

    With this change, timestamps stored in Cloud Firestore will be read back as com.google.firebase.Timestamp objects instead of as system java.util.Date objects. So you will also need to update code expecting a java.util.Date to instead expect a Timestamp. For example:

    // Old:
    java.util.Date date = snapshot.getDate("created_at");
    // New:
    Timestamp timestamp = snapshot.getTimestamp("created_at");
    java.util.Date date = timestamp.toDate();

    Please audit all existing usages of java.util.Date when you enable the new behavior. In a future release, the behavior will be changed to the new behavior, so if you do not follow these steps, YOUR APP MAY BREAK.
*/

public class NotificationHelper {
    public static DatabaseReference actorNotifRef = FirebaseHelper.getRef(TempDataHelper.getUserId());

    public static void onHaveNewNotif(final NotifCallBack callBack){
        actorNotifRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SceneRoleFullInfo role = dataSnapshot.getValue(SceneRoleFullInfo.class);

                if (role != null
                        && role.getAssignedActor().getUsername().equals(TempDataHelper.getUserId())){
                    callBack.onHaveNewAssignedRole(role);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void updateRole(List<SceneRoleFullInfo> roles){

        for(SceneRoleFullInfo role: roles){
            DatabaseReference actorNotifRef = FirebaseHelper.getRef(role.getAssignedActor().getUsername());
            actorNotifRef.setValue(role);
            //actorNotifRef.setValue(role);
        }
    }
}
