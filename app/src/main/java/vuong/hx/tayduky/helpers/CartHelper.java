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
import vuong.hx.tayduky.firebase.FirebaseHelper;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.models.SceneTool;

public class CartHelper {
    private static CollectionReference cartRoles = FirebaseHelper.getCollection("cartRoles");
    private static CollectionReference cartTools = FirebaseHelper.getCollection("cartTools");

    public static void addNewRole(int challengeId,  String joinDate,
                                  String assignedActor, int character, String desc){

        Map<String, Object> role = new HashMap<>();

        role.put("challenge", (long) challengeId);
        role.put("actor", assignedActor);
        role.put("character", character);
        role.put("desc", desc);
        role.put("joinedDate", joinDate);

        cartRoles.document().set(role);
    }


    public static void addNewSceneTool(int challengeId, int toolId, int quantity){
        Map<String, Object> sceneTool = new HashMap<>();

        sceneTool.put("challenge", challengeId);
        sceneTool.put("tool", toolId);
        sceneTool.put("quantity", quantity);

        cartTools.document().set(sceneTool);
    }

    public static List<SceneTool> getTools(){
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
            }
        });

        return list;
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
