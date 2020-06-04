package com.example.reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class second2 extends AppCompatActivity {
    EditText eee1;
    ToggleButton ttt2;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    //PendingIntent pendingIntent;
    //AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);
        eee1=(EditText)findViewById (R.id.etttt1);
        ttt2=(ToggleButton)findViewById (R.id.btt);
        //alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        String ss7=getIntent ().getStringExtra ("second");
        eee1.setText (ss7);
        /*ttt2.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener ( ) {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {       long time;
                    String ss5=eee1.getText ().toString ();
                    String ss6=ss5.substring (0,2);
                    int n1=Integer.parseInt (ss6);
                    String ss7=ss5.substring (3,5);
                    int n2=Integer.parseInt (ss7);

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY,n1);
                    calendar.set(Calendar.MINUTE, n2);
                    Intent intent = new Intent(second2.this, AlarmReciever.class);
                    pendingIntent = PendingIntent.getBroadcast(second2.this, 0, intent, 0);

                    time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                    if(System.currentTimeMillis()>time)
                    {
                        if (calendar.AM_PM == 0)
                            time = time + (1000*60*60*12);
                        else
                            time = time + (1000*60*60*24);
                    }
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
                }
                else
                {
                    alarmManager.cancel(pendingIntent);
                    Toast.makeText(second2.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
   public void OnToggleClicked(View view)
    {
        long time;
        if (ttt2.isChecked())
        {

            String ss5=eee1.getText ().toString ();
            String ss6=ss5.substring (0,2);
            int n1=Integer.parseInt (ss6);
            String ss7=ss5.substring (3,5);
            int n2=Integer.parseInt (ss7);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,n1);
            calendar.set(Calendar.MINUTE, n2);
            Intent intent = new Intent(this, AlarmReciever.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
            if(System.currentTimeMillis()>time)
            {
                if (calendar.AM_PM == 0)
                    time = time + (1000*60*60*12);
                else
                    time = time + (1000*60*60*24);
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
        }
        else
        {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(second2.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
    }
}
