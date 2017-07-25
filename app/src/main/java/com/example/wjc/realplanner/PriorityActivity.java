package com.example.wjc.realplanner;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PriorityActivity extends AppCompatActivity {

    Button button;
    Button MakePriority;
    PriorityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        MakePriority = (Button)findViewById(R.id.makepriority);
        MakePriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MakePriorityActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new PriorityAdapter();
        //데이터베이스 써야할듯.

        load_values() ;

        adapter.addItem(new PriorityItem(R.drawable.number1,"2017/10/11","열심히살자"));
        adapter.addItem(new PriorityItem(R.drawable.number2,"2017/10/12","열심히살자2"));
        adapter.addItem(new PriorityItem(R.drawable.number3,"2017/10/13","열심히살자3"));
        adapter.addItem(new PriorityItem(R.drawable.number4,"2017/10/14","열심히살자4"));
        adapter.addItem(new PriorityItem(R.drawable.number5,"2017/10/15","열심히살자5"));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PriorityItem item = (PriorityItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택됨"+item.getMemo(),Toast.LENGTH_LONG).show();
            }
        });
    }

    class PriorityAdapter extends BaseAdapter {

        ArrayList<PriorityItem> items = new ArrayList<PriorityItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(PriorityItem item)
        {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            PriorityItemView view = new PriorityItemView(getApplicationContext());
            PriorityItem item = items.get(position);
            view.setImage(item.getPrioritynum()); // 이미지 매칭 필요. -> 데이터베이스
            view.setDate(item.getDate());
            view.setMemo(item.getMemo());
            return view;
        }
    }

    private void load_values() {

        if (PlannerDB != null) {
            String sqlQueryTbl = "SELECT * FROM CONTACT_T" ;
            Cursor cursor = null ;

            // 쿼리 실행
            cursor = sqliteDB.rawQuery(sqlQueryTbl, null) ;

            if (cursor.moveToNext()) { // 레코드가 존재한다면,
                // no (INTEGER) 값 가져오기.
                int no = cursor.getInt(0) ;
                EditText editTextNo = (EditText) findViewById(R.id.editTextNo) ;
                editTextNo.setText(Integer.toString(no)) ;

                // name (TEXT) 값 가져오기
                String name = cursor.getString(1) ;
                EditText editTextName = (EditText) findViewById(R.id.editTextName) ;
                editTextName.setText(name) ;

                // phone (TEXT) 값 가져오기
                String phone = cursor.getString(2) ;
                EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone) ;
                editTextPhone.setText(phone) ;

                // over20 (INTEGER) 값 가져오기.
                int over20 = cursor.getInt(3) ;

            }
        }
    }







}


