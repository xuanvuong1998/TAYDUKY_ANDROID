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

public class NotificationHelper {
    public static DatabaseReference actorNotifRef = FirebaseHelper.getRef(TempDataHelper.getUserId());

    public static void onHaveNewNotif(final NotifCallBack callBack){
        actorNotifRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SceneRoleFullInfo role = dataSnapshot.getValue(SceneRoleFullInfo.class);

                if (role.getAssignedActor().getUsername() == TempDataHelper.getUserId()){
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
        }
    }
}
