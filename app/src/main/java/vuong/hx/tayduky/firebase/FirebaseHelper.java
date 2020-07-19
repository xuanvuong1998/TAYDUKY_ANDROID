package vuong.hx.tayduky.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseHelper {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public static DatabaseReference getRef(String refKey){
        return database.getReference(refKey);
    }

    public static CollectionReference getCollection(String id){
        return firestore.collection(id);
    }

    public static DocumentReference getDocument(CollectionReference col, String docId){
        return col.document(docId);
    }

}
