package com.example.contactdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button createButton;
    ListView ListViewAllUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<UserModel> listUser = db.getData();

        CustomAdapter customAdapter = new CustomAdapter(listUser);

        customAdapter.notifyDataSetInvalidated();
        customAdapter.notifyDataSetChanged();

        ListViewAllUser = findViewById(R.id.ListViewAllUser);
        ListViewAllUser.setAdapter(customAdapter);

//        ListViewAllUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(MainActivity.this, ItemDetail.class);
//                i.putExtra("id",id);
//                startActivity(i);
//            }
//        });


        createButton = (Button) findViewById(R.id.create);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateUser.class);
                startActivity(i);
            }
        });
    }
}