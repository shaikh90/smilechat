package traffic.firebase.com;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button add_room;
    private EditText room_name;
   // private ListView listview;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list_of_room = new ArrayList<>();
    private String name;
    private TextView textView,textView2,count,textView3,textView4,textView5;
    FirebaseUser currentFirebaseUser;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference(".info/connected");

    private String user_name,user_pics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name = getIntent().getExtras().get("user_name").toString();
//        user_pics = getIntent().getExtras().get("user_pic").toString();
         currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
//        Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();

        add_room=findViewById(R.id.add_room);
        room_name=findViewById(R.id.room_name);
      //  listview=findViewById(R.id.listview);
        textView=findViewById(R.id.textView);
        textView2=findViewById(R.id.textView2);
        textView3=findViewById(R.id.textView3);
        textView4=findViewById(R.id.textView4);
        textView5=findViewById(R.id.textView5);
        count=findViewById(R.id.count);


        arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_of_room);
    //    listview.setAdapter(arrayAdapter);


        //request_user_name();

        add_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String,Object> map = new HashMap<String,Object>();
                map.put(room_name.getText().toString(),"");
                root.updateChildren(map);

                room_name.setText(null);


            }
        });



       root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



               /* Set<String> set =new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());

                }*/
               // list_of_room.clear();
             //   list_of_room.addAll(set);
               // arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("Listener was cancelled");
            }
        });

     /*   textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Chat_Room.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_pic",user_pics);
                startActivity(intent);

            }
        });*/
       /* textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Chat_Room.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_pic",user_pics);
                startActivity(intent);


            }
        });*/

      /*  listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),Chat_Room.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                intent.putExtra("user_name",name);
                startActivity(intent);
            }
        });*/


        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);

    }

    private void request_user_name() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Name");

        final EditText input_field = new EditText(this);
        builder.setView(input_field);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                 name = input_field.getText().toString();
             //   Toast.makeText(MainActivity.this, "Key^^^^^"+root.getKey(), Toast.LENGTH_SHORT).show();

                Log.d("###UserName####",name);
                count.setText(name);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
                request_user_name();
            }
        });
        builder.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textView: {
                Intent intent = new Intent(getApplicationContext(),Chat_Room.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_pic",user_pics);
                startActivity(intent);
            }
            break;


            case R.id.textView2: {
                Intent intent = new Intent(getApplicationContext(),Chat_Room.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_pic",user_pics);
                startActivity(intent);
            }
            break;


            case R.id.textView3: {
                Intent intent = new Intent(getApplicationContext(),Chat_Room.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_pic",user_pics);
                startActivity(intent);
            }
            break;


            case R.id.textView4: {
                Intent intent = new Intent(getApplicationContext(),Chat_Room.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_pic",user_pics);
                startActivity(intent);
            }
            break;

            case R.id.textView5: {
                Intent intent = new Intent(getApplicationContext(),Chat_Room.class);
                intent.putExtra("room_name",((TextView)view).getText().toString());
                intent.putExtra("user_name",user_name);
                intent.putExtra("user_pic",user_pics);
                startActivity(intent);
            }
            break;
        }
    }
}
