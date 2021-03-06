package com.example.apurva.sqlitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static DatabaseFile db;
    EditText id,name,disp;
    Button b1,b2,search,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new DatabaseFile(this);
        id=findViewById(R.id.id);
        name=findViewById(R.id.name);
        disp=findViewById(R.id.disp);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.btn_get);
        search=findViewById(R.id.search);
        update=findViewById(R.id.update);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int e1=Integer.parseInt(id.getText().toString());
                String e2=name.getText().toString();
                String e3=disp.getText().toString();
                
                CriminalRecord record=new CriminalRecord();
                record.setId(e1);
                record.setName(e2);
                record.setDesp(e3);
                
                db.addCriminalRecord(record);


                
                id.setText("");
                name.setText("");
                disp.setText("");
                Toast.makeText(MainActivity.this, "Data added", Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,SecondActivity.class);

                startActivity(intent);

                List<CriminalRecord> list=db.getAllCriminalList();

                for (CriminalRecord rec:list){
                    Toast.makeText(MainActivity.this, "ID: "+rec.getId()+"\nname: "+rec.getName()+"\ndesp: "+rec.getDesp(), Toast.LENGTH_LONG).show();
                }
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e1=id.getText().toString();

                if(e1.isEmpty()){
                    id.setError("Please enter id");
                }
                else {
                    CriminalRecord rec=db.getSingleRecord(Integer.parseInt(e1));
                    Toast.makeText(MainActivity.this, "ID: "+rec.getId()+"\nname: "+rec.getName()+"\ndesp: "+rec.getDesp(), Toast.LENGTH_LONG).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int e1=Integer.parseInt(id.getText().toString());
                String ds=disp.getText().toString();

                CriminalRecord rec=new CriminalRecord();

                rec.setId(e1);
                rec.setDesp(ds);

                db.updateRecord(rec);
                Toast.makeText(MainActivity.this, "Record is updated", Toast.LENGTH_SHORT).show();
                id.setText("");
                disp.setText("");
            }
        });
    }


}
