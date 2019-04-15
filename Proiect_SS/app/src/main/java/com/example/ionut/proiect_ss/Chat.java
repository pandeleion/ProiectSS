package com.example.ionut.proiect_ss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Chat extends AppCompatActivity {

    private Button butonSend;
    private String numeChat;
    private ListView listView;
    private DatabaseReference root;
    private FirebaseListAdapter<Mesaj> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        numeChat = getIntent().getExtras().get("numeChat").toString();
        setTitle(getString(R.string.chat_room) + " " + numeChat);

        root = FirebaseDatabase.getInstance().getReference().child(numeChat);

        listView = (ListView) findViewById(R.id.listView_mesaje);

        butonSend = (Button)findViewById(R.id.button_send);
        butonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputMesaj = (EditText)findViewById(R.id.editText_inputMesaj);
                root.push().setValue(new Mesaj(inputMesaj.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                inputMesaj.setText("");
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                afiseazaMesaj(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                afiseazaMesaj(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void afiseazaMesaj(DataSnapshot dataSnapshot) {
        adapter = new FirebaseListAdapter<Mesaj>(this, Mesaj.class, R.layout.list_item, root) {
            @Override
            protected void populateView(View v, Mesaj model, int position) {
                TextView mesajText, mesajUser, mesajTimp;
                mesajText = (TextView)v.findViewById(R.id.mesaj_text);
                mesajUser = (TextView)v.findViewById(R.id.mesaj_user);
                mesajTimp = (TextView)v.findViewById(R.id.mesaj_timp);
                mesajText.setText(model.getMesajText());
                mesajUser.setText(model.getMesajUser());
                mesajTimp.setText(DateFormat.format("dd-MM-yyyy HH:mm:ss", model.getMesajTimp()));
            }
        };
        listView.setAdapter(adapter);
    }

}
