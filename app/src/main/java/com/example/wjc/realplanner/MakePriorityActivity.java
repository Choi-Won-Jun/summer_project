package com.example.wjc.realplanner;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import static com.example.wjc.realplanner.R.id.editTextDate;

public class MakePriorityActivity extends AppCompatActivity {

    Button BackButton;
    Button MenuButton;
    EditText EditTextPriority;
    EditText EditTextDate;
    Spinner spinner;
    SQLiteDatabase PlannerDB;
    Button ButtonSave;
    TextView SpinnerText;
    String[] spinneritems={"10","20","30","40","50","60","70","80","90","100"};
    int counter =0; // 5가지만 저장할거임

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_priority);
        EditTextPriority = (EditText) findViewById(R.id.editTextPriority);
        EditTextDate = (EditText)findViewById(editTextDate);
        spinner = (Spinner)findViewById(R.id.spinner);
        SpinnerText = (TextView)findViewById(R.id.SpinnerText);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,spinneritems
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerText.setVisibility(View.INVISIBLE);
                SpinnerText.setText(spinneritems[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                SpinnerText.setText("");
            }
        });
        PlannerDB = init_database(); // 데이터베이스 생성
        init_tables(); //테이블 생성
        ButtonSave = (Button)findViewById(R.id.buttonSave);
        ButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_values();
            }
        });
        BackButton =(Button)findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PriorityActivity.class);
                startActivity(intent);
                finish();
            }
        });
        MenuButton =(Button)findViewById(R.id.MenuButton);
        MenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private SQLiteDatabase init_database(){

        SQLiteDatabase db = null;

        File file = new File(getFilesDir(),"contact.db");
        System.out.println("PATH : " + file.toString()) ;
        try {
            db = SQLiteDatabase.openOrCreateDatabase(file, null) ;
        } catch (SQLiteException e) {
            e.printStackTrace() ;
        }

        if (db == null) {
            System.out.println("DB creation failed. " + file.getAbsolutePath()) ;
        }

        return db ;
    }

    private void init_tables(){
        if (PlannerDB != null) {
            String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS PRIORITY_T (" +
                        "CONTENT "         + "TEXT," +
                        "DATE "           + "TEXT," +
                        "PNUM "           + "INTEGER NOT NULL" + //PNUM = PRIORITY NUMBER
                        ")" ;
            System.out.println(sqlCreateTbl) ;
            PlannerDB.execSQL(sqlCreateTbl) ;
        }
    }

    private void save_values() {

        if(counter<5) {
            if (PlannerDB != null) {

                //delete부분 삭제함.

                EditText EditTextPriority = (EditText) findViewById(R.id.editTextPriority);
                String content = EditTextPriority.getText().toString();

                EditText EditTextDate = (EditText) findViewById(editTextDate);
                String date = EditTextDate.getText().toString();

                TextView SpinnerText = (TextView) findViewById(R.id.SpinnerText);
                String prioritynum = SpinnerText.getText().toString();

                int pnum = 0;
                if (prioritynum != null && !prioritynum.isEmpty()) {
                    pnum = Integer.parseInt(prioritynum);
                }

                //데이터 정보 저장 완료.

                String sqlInsert = "INSERT INTO PRIORITY_T " +
                        "(CONTENT, DATE, PNUM ) VALUES (" +
                        content + "," + "'" + date +
                        "'," + "'" + Integer.toString(pnum) + "',"
                        + ")";
                System.out.println(sqlInsert);
                PlannerDB.execSQL(sqlInsert);
                counter++;
            }
        }
        else if(counter>5){
            Toast.makeText(getApplicationContext(),"이미 우선순위가 5가지입니다!",Toast.LENGTH_LONG).show();
        }
    }

}



