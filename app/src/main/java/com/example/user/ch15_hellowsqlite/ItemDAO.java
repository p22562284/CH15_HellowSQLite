package com.example.user.ch15_hellowsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 2016/3/12.
 */
public class ItemDAO {
    // 表格名稱
    public static final  String TABLE_NAME = "doctor";

    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";

    // 其它表格欄位名稱
    public static final String DATETIME_C = "datetime";
    public static final String NAME_C = "name";
    public static final String HOSPITAL_C = "hospital";
    public static final String SPECIALTY_C = "specialty";

    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " {" +
                    KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DATETIME_C + " INTEGER NOT NULL. " +
                    NAME_C + " TEXT NOT NULL, " +
                    HOSPITAL_C + " TEXT NOT NULL, " +
                    SPECIALTY_C + "TEXT)";
    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public ItemDAO(Context context) {
        db = MyDBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    //查詢
    public List<Item> query(){
        List<Item> result = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            result.add(getRecord(cursor));
        }
        cursor.close();
        return result;
    }

    // 把Cursor目前的資料包裝為物件
    private Item getRecord(Cursor cursor) {
        Item result = new Item();

        result.setId(cursor.getLong(0));
        result.setDatetime(cursor.getLong(1));
        result.setName(cursor.getString(2));
        result.setHospital(cursor.getString(3));
        result.setSpecialty(cursor.getString(4));

        return result;
    }

    // 新增參數指定的物件
    public Item insert(Item item){
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(DATETIME_C, item.getDatetime());
        cv.put(NAME_C, item.getName());
        cv.put(HOSPITAL_C, item.getHospital());
        cv.put(SPECIALTY_C, item.getSpecialty());

        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
        item.setId(id);
        // 回傳結果
        return item;
    }
    public int getCount(){
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME,null);

        if(cursor.moveToNext()){
            result = cursor.getInt(0);
        }
        return  result;
    }

    public void sample(){
        Item sampleItem = new Item(0, new Date().getTime(), "小明","台大","耳鼻喉科");
    }
}
