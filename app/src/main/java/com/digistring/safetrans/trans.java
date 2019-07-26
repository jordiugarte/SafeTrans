package com.digistring.safetrans;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class trans extends AppCompatActivity {

    // information = what should be introduced on the database
    private EditText information;
    FirebaseDatabase database;
    DatabaseReference reference;

    Transaction transactionSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);
        // information = findViewById(R.id. (...) );
        initialitationDatabase();
    }

    // in case for use a menu activity, other wise implement the case from the switch
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(...);*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String textInformation = information.getText().toString();
        Transaction t = new Transaction();
        switch (item.getItemId()) {
            //ToDo do not forget
            case (1): {
                if (textInformation.equals("")) {
                    validacion(textInformation);
                } else {
                    t.setId(UUID.randomUUID().toString());
                    t.setInfomation(textInformation);
                    reference.child("Infomation").child(t.getId()).setValue(t).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(trans.this, "Se agrego correctamente", Toast.LENGTH_SHORT).show();
                            } else {
                                task.getException().printStackTrace();
                            }
                        }
                    });
                    Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
                    limparcaja();
                }
                break;
            }
            default:
                break;
        }
        return true;
    }
//
    private void limparcaja() {
        information.setText("");
    }

    private void validacion(String txtCommemt) {
        if (txtCommemt.equals("")) {
            information.setError("Hey!, ingrese su comentario");
        }
    }

    private void initialitationDatabase() {
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        reference = database.getReference();
    }
}
