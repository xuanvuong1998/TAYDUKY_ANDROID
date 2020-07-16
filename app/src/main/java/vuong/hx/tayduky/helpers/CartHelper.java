package vuong.hx.tayduky.helpers;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vuong.hx.tayduky.callbacks.QuerySnapshotCallBack;
import vuong.hx.tayduky.callbacks.SetDocumentCallBack;
import vuong.hx.tayduky.firebase.FirebaseHelper;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.models.SceneTool;

public class CartHelper {
    private static CollectionReference cartRoles = FirebaseHelper.getCollection("cart-roles");
    private static CollectionReference cartTools = FirebaseHelper.getCollection("cart-tools");

    public static void addNewRole(SceneRole newRole, final SetDocumentCallBack callBack){

        Map<String, Object> role = new HashMap<>();

        role.put("challenge", (long) newRole.getChallengeId());
        role.put("actor", newRole.getAssignedActor());
        role.put("character", newRole.getCharacterId());
        role.put("desc", newRole.getDescription());
        role.put("joinedDate", newRole.getParticipatedDate());

        cartRoles.document().set(role).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    callBack.onSuccess();
                }else{
                    callBack.onFail(task.getException().getMessage());
                }
            }
        });
    }


    public static void addNewSceneTool(SceneTool newTool, final SetDocumentCallBack callBack){
        Map<String, Object> sceneTool = new HashMap<>();

        sceneTool.put("challenge", newTool.getChallengeId());
        sceneTool.put("tool", newTool.getToolId());
        sceneTool.put("quantity", newTool.getQuantity());

        cartTools.document().set(sceneTool).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    callBack.onSuccess();
                }else{
                    callBack.onFail(task.getException().getMessage());
                }
            }
        });
    }

    public static void getTools(final QuerySnapshotCallBack callBack){

        final List<SceneTool> list = new ArrayList<>();

        cartTools.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot doc : task.getResult()){
                    Map<String, Object> data = doc.getData();

                    int challengeId = (int) (long) data.get("challenge");
                    int toolId = (int) (long) data.get("tool");
                    int quantity = (int) (long) data.get("quantity");

                    SceneTool tool = new SceneTool();

                    tool.setChallengeId(challengeId);
                    tool.setQuantity(quantity);
                    tool.setToolId(toolId);

                    list.add(tool);
                }
                callBack.onComplete(list);
            }
        });


    }

    public static void getRoles(final QuerySnapshotCallBack callBack){

        final List<SceneRole> list = new ArrayList<>();

        cartRoles.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot doc : task.getResult()){

                    Map<String, Object> data = doc.getData();
                    int challengeId = (int) (long) data.get("challenge");
                    int character = (int) (long) data.get("character") ;
                    String actor = (String) data.get("actor");
                    String desc = (String) data.get("desc");
                    String joinedDate = (String) data.get("joinedDate");

                    SceneRole role = new SceneRole();
                    role.setChallengeId(challengeId);
                    role.setDescription(desc);
                    role.setAssignedActor(actor);
                    role.setCharacterId(character);
                    role.setParticipatedDate(joinedDate);

                    list.add(role);
                    LogHelper.printAssert(challengeId + ", " + character + ", "
                            + actor + ", " + desc + ", " + joinedDate);
                }


                callBack.onComplete(list);
            }
        });



    }
}
