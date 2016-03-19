package com.example.user.ch15_hellowsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button queryBtn,updateBtn,insertBtn;
    EditText hname,dname,department;
    TextView output;
    private SQLiteDatabase db;//資料庫物件
    private MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        queryBtn = (Button)findViewById(R.id.query);
        updateBtn = (Button)findViewById(R.id.update);
        insertBtn = (Button)findViewById(R.id.insert);

        hname = (EditText)findViewById(R.id.Hname);
        dname = (EditText)findViewById(R.id.Dname);
        department = (EditText)findViewById(R.id.Department);

        output = (TextView)findViewById(R.id.output);

//        dbHelper = new MyDBHelper(this);
//        db = dbHelper.getWritableDatabase();

        //Insert
        insertBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                // 加入ContentValues物件包裝的新增資料
                // 第一個參數是欄位名稱， 第二個參數是欄位的資料
                cv.put("Hname",hname.getText().toString());
                cv.put("Dname",dname.getText().toString());
                cv.put("Department", department.getText().toString());
                long id = db.insert("test",null,cv);
                output.setText("新增成功"+id);
                //System.out.println("outprint"+hname.getText().toString());
            }
        });

        //Query
        queryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SqlQuery("SELECT * FROM " + "test");
            }
        });
    }

    public void SqlQuery(String sql){
        String[] colNames;
        String str = "";
        Cursor c = db.rawQuery(sql, null);
        colNames = c.getColumnNames();
        for(int i = 0; i<colNames.length; i++){
            str += colNames[i] +"\t\t";
        }
        str += "\n";
        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++){
            str += c.getString(0) + "\t";
            str += c.getString(1) + "\t";
            str += c.getString(2) + "\t";
            c.moveToNext();
        }
        output.setText(str.toString());
    }
    public void goBack(View v){
        finish();
    }


}
