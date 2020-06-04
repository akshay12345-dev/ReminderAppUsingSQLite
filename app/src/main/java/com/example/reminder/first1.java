package com.example.reminder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.app.AlertDialog.Builder;
import java.lang.String;
import java.lang.StringBuffer;

import java.util.Calendar;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class first1 extends AppCompatActivity implements View.OnClickListener {

  EditText eff1;
  ImageButton bff1;
  Button bff2,bff3;
    SQLiteDatabase dbb;
    private int mYear, mMonth, mDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_first1);
        eff1=(EditText)findViewById (R.id.eft1);
        bff1=(ImageButton)findViewById (R.id.if1);
        bff2=(Button)findViewById (R.id.btnLn1f);
        bff3=(Button)findViewById (R.id.btnLn2f);
        String ss5=getIntent ().getStringExtra ("time");
        dbb = openOrCreateDatabase ("StudentDB1", Context.MODE_PRIVATE, null);
        dbb.execSQL ("CREATE TABLE IF NOT EXISTS student4(time VARCHAR,date VARCHAR);");
        bff1.setOnClickListener (this);
        bff2.setOnClickListener (this);
        bff3.setOnClickListener (this);








    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View v) {
        if (v == bff1) {
            final Calendar c = Calendar.getInstance ( );
            mYear = c.get (Calendar.YEAR);
            mMonth = c.get (Calendar.MONTH);
            mDay = c.get (Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog (this, new DatePickerDialog.OnDateSetListener ( ) {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    eff1.setText (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show ( );
        }

        if (v == bff2) {
            String ss6=getIntent ().getStringExtra ("time");
            if (eff1.getText ( ).toString ( ).trim ( ).length ( ) == 0) {
                Toast.makeText (first1.this, "Error! Insert Value", Toast.LENGTH_SHORT).show ( );
            } else {
                dbb.execSQL ("INSERT INTO student4 VALUES('" + eff1.getText ( ) + "','"+ss6+"');");
                Toast.makeText (first1.this, "Date inserted successfully", Toast.LENGTH_SHORT).show ( );
            }


        }

     if (v == bff3) {
         Cursor c=dbb.rawQuery("SELECT * FROM student4", null);
         if(c.getCount ()==0)
         {
             showMessage("Error", "No records found");
             return;
         }
         StringBuffer buffer=new StringBuffer();
         while(c.moveToNext())
         {
             buffer.append("Date is: "+c.getString(0)+"\t\t");
             buffer.append("Time is: "+c.getString(1)+"\n");

         }
         showMessage("Student Details", buffer.toString());


    }

}

    public void showMessage (String title, String message)
    {
        Builder builder = new Builder(this);
        builder.setCancelable (true);
        builder.setTitle (title);
        builder.setMessage (message);
        builder.show ( );
    }


}


