package com.ch.article;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sql.CommonDataSource;

public class MainActivity extends AppCompatActivity {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase db;
    private ListView listView;
    private String a ,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String packageName=getPackageName();
        String db_path="/data/data/"+packageName+"/databases/";
        String db_path1=db_path+"article.db";
        File file=new File(db_path);
        listView= (ListView) findViewById(R.id.listview);
        final  List list,list1;
        list= new ArrayList();
        list1=new ArrayList();
        sqLiteOpenHelper=new openhelper(this);
        if (!file.exists()){
             file.mkdir();
        }

        File file1=new File(db_path1);
        if (!file1.exists()) {
            try {
                InputStream inputStream = getAssets().open("article.db");
                OutputStream out = new FileOutputStream(db_path1);
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf)) > 0) {

                    out.write(buf, 0, len);

                }

                inputStream.close();


                out.close();
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(), "文件不存在！", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "读写错误！", Toast.LENGTH_LONG).show();
            }

        }
           db=sqLiteOpenHelper.getReadableDatabase();


           if (db!=null){

             Cursor cursor= db.query("article",null,null,null,null,null,null);
             while (cursor.moveToNext()){

               a=cursor.getString(cursor.getColumnIndex("name"));

               b=cursor.getString(cursor.getColumnIndex("content"));
               list.add(a);
               list1.add(b);
             }




               listView.setAdapter(new Myadpater(list,this));

               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                       Intent intent=new Intent();
                       intent.setClass(MainActivity.this,contentActivity.class);

                       intent.putExtra("content",list1.get(position).toString());
                       startActivity(intent);

                   }
               });
        }

        else {
               Toast.makeText(getApplicationContext(),"数据库为空！",Toast.LENGTH_LONG).show();
           }
        }


}
