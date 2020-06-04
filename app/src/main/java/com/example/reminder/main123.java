package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.lang.StringBuffer;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ArrayAdapter;
public class main123 extends AppCompatActivity {
   // Button b41;
    SQLiteDatabase db4;
 //PendingIntent pendingIntent;
 // AlarmManager alarmManager;
    // ListView ll2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main123);
      //larmManager = (AlarmManager) getSystemService (ALARM_SERVICE);
        // ll2=(ListView)findViewById (R.id.lv11) ;
        ArrayList <String> theList = new ArrayList <> ( );
       // RecyclerView.Adapter adapter;
        RecyclerView recyclerView = (RecyclerView) findViewById (R.id.recyclerView);
      MyListData[] myListData=new MyListData[20];
        int i=0;
 // ArrayList<MyListData> myListdata3;
        //StringBuffer s5;
        // b41=(Button)findViewById (R.id.button4);
        db4 = openOrCreateDatabase ("StudentDB1", Context.MODE_PRIVATE, null);
        //b41.setOnClickListener (this);
        Cursor c = db4.rawQuery ("SELECT time FROM student1", null);
        if (c.getCount ( ) == 0) {
            Toast.makeText (main123.this, "No contents", Toast.LENGTH_SHORT).show ( );

        } else {
            while (c.moveToNext ( )) {
             // myListdata3.add (new MyListData (c.getString (0)));
               theList.add (c.getString (0));
               CustomAdapter customAdapter = new CustomAdapter (main123.this, theList);
                // MyListAdapter adapter = new MyListAdapter(myListData);
               recyclerView.setHasFixedSize (true);
                recyclerView.setLayoutManager (new LinearLayoutManager (this));
              recyclerView.setAdapter (customAdapter);

                // ListAdapter listAdapter=new ArrayAdapter<> (this,android.R.layout.simple_list_item_1,thelist);
                // ll2.setAdapter (listAdapter);
               //  myListData = new MyListData[] {new MyListData (c.getString (0)),
           };}

    }
}
   /*String[] s5=new String[200];
     int i=0;


        while(c.moveToNext())
        {
            buffer.append(c.getString(0)+"\n");
           s5[i]=new StringBuffer (c.getString (0));
            i++;
            showMessage("Student Details", buffer.toString());

        }
        final ArrayAdapter<StringBuffer> aa=new ArrayAdapter<StringBuffer> (main123.this,android.R.layout.simple_list_item_1,s5);
        ll2.setAdapter (aa);
        ll2.setOnItemClickListener (new AdapterView.OnItemClickListener ( ) {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {

                if(position==0)
                {
                    Intent i2=new Intent (main123.this,first1.class);
                    //StringBuffer st=aa.getItem (position);
                    String ss9=thelist.get (position);
                   // String ss1=st.toString ();
                    i2.putExtra ("first",ss9);

                    startActivity (i2);

                    //Toast.makeText (main123.this,"Item clicked"+position+" "+st,Toast.LENGTH_SHORT).show ();
                }
                if(position==1)
                {
                    Intent i3=new Intent (main123.this,second2.class);
                    String ss56=thelist.get (position);
                   StringBuffer st=aa.getItem (position);
                    String s2=st.toString ();
                    i3.putExtra ("second",ss56);
                    startActivity (i3);

                    Toast.makeText (main123.this,"Item clicked"+position+" "+st,Toast.LENGTH_SHORT).show ();
                }
               if(position==2)
                {
                    Intent i3=new Intent (main123.this,second2.class);
                    startActivity (i3);
                    StringBuffer st=aa.getItem (position);
                    String s2=st.toString ();
                    i3.putExtra ("second",s2);
                    Toast.makeText (main123.this,"Item clicked"+position+" "+st,Toast.LENGTH_SHORT).show ();
                }


            }
        });
    }
   /* public void onClick(View v)
    {
        Cursor c=db4.rawQuery("SELECT time FROM student1", null);
        if(c.getCount()==0)
        {
            Toast.makeText (main123.this,"No data",Toast.LENGTH_SHORT).show ();

        }
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {
            buffer.append(c.getString(0)+"\n");
            showMessage("Student Details", buffer.toString());

        }
    }*/
   /* public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder (this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }*/




