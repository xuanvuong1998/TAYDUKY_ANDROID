package vuong.hx.tayduky.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vuong.hx.tayduky.helpers.LogHelper;

public class FirebaseHelper {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();

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

    public static String getData(String refKey){
        getRef(refKey).
    }
}
