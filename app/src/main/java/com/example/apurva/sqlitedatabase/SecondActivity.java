package com.example.apurva.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ListView mylist;
    List<CriminalRecord> list;
    ArrayList<String> myarray;
    String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mylist=findViewById(R.id.mylist);

        list=MainActivity.db.getAllCriminalList();
        myarray=new ArrayList<>();

        for (CriminalRecord rec:list){

            msg="ID: "+rec.getId()+"\nname: "+rec.getName()+"\ndesp: "+rec.getDesp();

            myarray.add(msg);

        }

        ArrayAdapter<String> adapter=new ArrayAdapter<>(SecondActivity.this,android.R.layout.simple_list_item_1,myarray);
        mylist.setAdapter(adapter);

    }
}
