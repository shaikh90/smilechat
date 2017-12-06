package traffic.firebase.com;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by hshaikh on 11/29/2017.
 */

public  class Chat_Room extends AppCompatActivity {

    private TextView user_text;
    private Button send;
    private EditText send_text;

    private String user_name,room_name,user_pics;
    private DatabaseReference root;
    private String temp_key;
    ImageView emojiButton;
    ScrollView scrollView;

    View rootView;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_room);

        user_text = findViewById(R.id.user_text);
        send = findViewById(R.id.send);
        send_text = findViewById(R.id.send_text);
        scrollView = findViewById(R.id.scrollView);
        emojiButton = (ImageView) findViewById(R.id.emoji_btn);
        scrollView.fullScroll(View.FOCUS_DOWN);

        user_name = getIntent().getExtras().get("user_name").toString();
        room_name = getIntent().getExtras().get("room_name").toString();
//        user_pics = getIntent().getExtras().get("user_pic").toString();


        setTitle("Room - "+room_name);
        root = FirebaseDatabase.getInstance().getReference().child(room_name);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,Object> map = new HashMap<String, Object>();
                temp_key =root.push().getKey();
                root.updateChildren(map);


                DatabaseReference message_root = root.child(temp_key);
                //Toast.makeText(Chat_Room.this, "Key^^^^^"+ message_root , Toast.LENGTH_SHORT).show();

                Map<String,Object> map2 = new HashMap<String, Object>();

                map2.put("name",user_name);
                map2.put("msg",send_text.getText().toString());
                message_root.updateChildren(map2);

                send_text.setText(null);
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);
                scrollView.fullScroll(View.FOCUS_DOWN);

              //  dataSnapshot.getChildrenCount();
                //Toast.makeText(Chat_Room.this, "Count is"  +   dataSnapshot.getChildrenCount() , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);
                scrollView.fullScroll(View.FOCUS_DOWN);

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

    private String chat_msg,chat_user_name;
    private void append_chat_conversation(DataSnapshot dataSnapshot) {


        Iterator i = dataSnapshot.getChildren().iterator();
        while (i.hasNext()){

            chat_msg =(String) ((DataSnapshot)i.next()).getValue();
            chat_user_name =(String) ((DataSnapshot)i.next()).getValue();

            user_text.append(chat_user_name + ":"   +chat_msg + "\n");

            scrollView.fullScroll(View.FOCUS_DOWN);


        }
    }




}
