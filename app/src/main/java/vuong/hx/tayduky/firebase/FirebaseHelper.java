package vuong.hx.tayduky.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseHelper {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private static DatabaseReference messRef = database.getReference("message");

    public static void initEvents(){

        messRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("FIREBASE", dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void setData(String data){
        messRef.setValue(data);
    }

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
