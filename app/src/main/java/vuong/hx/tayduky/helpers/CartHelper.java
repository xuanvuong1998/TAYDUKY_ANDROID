package vuong.hx.tayduky.helpers;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import vuong.hx.tayduky.callbacks.QuerySnapshotCallBack;
import vuong.hx.tayduky.callbacks.SetDocumentCallBack;
import vuong.hx.tayduky.firebase.FirebaseHelper;
import vuong.hx.tayduky.models.SceneRoleFullInfo;
import vuong.hx.tayduky.models.SceneToolFullInfo;

public class CartHelper {
    private static CollectionReference cartRoles = FirebaseHelper.getCollection("cart-roles");
    private static CollectionReference cartTools = FirebaseHelper.getCollection("cart-tools");

    public static void addNewRole(SceneRoleFullInfo newRole, final SetDocumentCallBack callBack) {

        cartRoles.document().set(newRole).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callBack.onSuccess();
                } else {
                    callBack.onFail(task.getException().getMessage());
                }
            }
        });
    }

    public static void getRoles(final QuerySnapshotCallBack callBack) {

        final List<SceneRoleFullInfo> list = new ArrayList<>();

        cartRoles.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot doc : task.getResult()) {
                    SceneRoleFullInfo role = doc.toObject(SceneRoleFullInfo.class);
                    list.add(role);
                }
                callBack.onComplete(list);
            }
        });


    }

    public static void addNewSceneTool(SceneToolFullInfo newTool, final SetDocumentCallBack callBack) {

        cartTools.document().set(newTool).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callBack.onSuccess();
                } else {
                    callBack.onFail(task.getException().getMessage());
                }
            }
        });
    }

    public static void getTools(final QuerySnapshotCallBack callBack) {

        final List<SceneToolFullInfo> list = new ArrayList<>();

        cartTools.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot doc : task.getResult()) {
                    SceneToolFullInfo tool = doc.toObject(SceneToolFullInfo.class);

                    list.add(tool);
                }
                callBack.onComplete(list);
            }
        });


    }

}
