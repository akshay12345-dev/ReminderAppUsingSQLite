package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;
import java.lang.String;
import java.lang.StringBuffer;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Calendar;

public class SetTime extends AppCompatActivity implements View.OnClickListener {
    int h, m;
    EditText t11;
    TextView bv11;
    TimePicker tp;
    ImageButton b21;
    Button b31, b41;
    SQLiteDatabase db;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_set_time);

        t11 = (EditText) findViewById (R.id.et1);
        bv11 = (TextView) findViewById (R.id.tv1);


        b21 = (ImageButton) findViewById (R.id.i1);

        b31 = (Button) findViewById (R.id.btnLn);
        b41 = (Button) findViewById (R.id.btnLn1);
        db = openOrCreateDatabase ("StudentDB1", Context.MODE_PRIVATE, null);
        db.execSQL ("CREATE TABLE IF NOT EXISTS student1(time VARCHAR);");


        b21.setOnClickListener (this);
        b31.setOnClickListener (this);
        b41.setOnClickListener (this);
        //bv11.setOnClickListener (this);
    }

    public void onClick(View v) {
        if (v == b21) {
            final Calendar c = Calendar.getInstance ( );
            mHour = c.get (Calendar.HOUR_OF_DAY);
            mMinute = c.get (Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog (this, new TimePickerDialog.OnTimeSetListener ( ) {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    t11.setText (hourOfDay + ":" + minute);
                }
            }, mHour, mMinute, false);
            timePickerDialog.show ( );
        }
        if (v == b31) {
            if (t11.getText ( ).toString ( ).trim ( ).length ( ) == 0) {
                Toast.makeText (SetTime.this, "Error! Insert Value", Toast.LENGTH_SHORT).show ( );
            } else {
                db.execSQL ("INSERT INTO student1 VALUES('" + t11.getText ( ) + "');");
                Toast.makeText (SetTime.this, "Timing inserted successfully", Toast.LENGTH_SHORT).show ( );
            }
        }
        if (v == b41) {
      /*  Cursor c=db.rawQuery("SELECT time FROM student1", null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM student1");
               // showMessage("Success", "Record Deleted");
            }
            else
            {
                //showMessage("Error", "Invalid Rollno");
            }*/





   Cursor c=db.rawQuery("SELECT time FROM student1 WHERE time='"+t11.getText()+"'", null);
            if(c.moveToFirst())
            {
               bv11.setText(c.getString(0));

            }
            else
            {
                Toast.makeText (SetTime.this,"Error",Toast.LENGTH_SHORT).show ();

            }
            String s1=t11.getText ().toString ();
            String s2=bv11.getText ().toString ();
            if(s1.length ()==0)
         {
                Toast.makeText (SetTime.this,"Fill Time",Toast.LENGTH_SHORT).show ();
            }
            else if(s1.equals (s2))
            {
                Intent i1=new Intent (SetTime.this,main123.class);
                startActivity (i1);
            }
        }
      /*  if(v==bv11)
        {
           Cursor c=db.rawQuery("SELECT time FROM student1", null);
            if(c.getCount()==0)
            {
                Toast.makeText (SetTime.this,"No data",Toast.LENGTH_SHORT).show ();

            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append(c.getString(0)+"\n");
                showMessage("Student Details", buffer.toString());

            }
            Cursor c=db.rawQuery("SELECT time FROM student1", null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM student1");
                showMessage("Success", "Record Deleted");
            }
            else
            {
                showMessage("Error", "Invalid Rollno");
            }
        }*/



       /* public void showMessage (String title, String message)
        {
            Builder builder = new Builder (this);
            builder.setCancelable (true);
            builder.setTitle (title);
            builder.setMessage (message);
            builder.show ( );
        }*/
    }
}