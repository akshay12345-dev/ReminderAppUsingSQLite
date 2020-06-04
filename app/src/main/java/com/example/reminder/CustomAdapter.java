package com.example.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.util.ArrayList;
import java.util.Calendar;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
   // private static final Object ALARM_SERVICE = ;
    ArrayList personNames;
    int n1,n2;
   // ArrayList personImages;
     PendingIntent pendingIntent;
 AlarmManager alarmManager;
    Context context;
    public CustomAdapter(Context context, ArrayList personNames) {
        this.context = context;
        this.personNames = personNames;
      //  this.alarmManager=alarmManager;
       // this.pendingIntent=pendingIntent;

      alarmManager = (AlarmManager) context.getSystemService ( Context.ALARM_SERVICE);
        // this.personImages = personImages;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(listItem); // pass the view to View Holder
        return vh;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.name.setText(personNames.get(position).toString ());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                if (holder.name.getText ( ) == personNames.get (position).toString ( )) {

                    Toast.makeText (view.getContext ( ), personNames.get (position).toString ( ), Toast.LENGTH_SHORT).show ( );
                    Intent i7=new Intent (context,first1.class);
                    i7.putExtra ("time",holder.name.getText ());
                   context.startActivity ( i7);
                }
            }
        });

        holder.image1.setOnCheckedChangeListener (new CompoundButton.OnCheckedChangeListener ( ) {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    long time;
                    String ss5 = holder.name.getText ( ).toString ( );
                    if(ss5.length ( )==5) {
                        String ss6 = ss5.substring (0, 2);
                         n1 = Integer.parseInt (ss6);
                        String ss8 = ss5.substring (3, 5);
                         n2 = Integer.parseInt (ss8);
                    }
                    else if(ss5.length ()==3)
                    {
                        char ch=ss5.charAt (0);
                        n1=Character.getNumericValue (ch);
                        char ch1=ss5.charAt (2);
                        n2=Character.getNumericValue (ch1);
                       // String ss9=ss5.substring (2,4);

                    }
                    else if(ss5.length ()==4)
                    {
                        char ch=ss5.charAt (0);
                        n1=Character.getNumericValue (ch);
                        String ss9=ss5.substring (2,4);
                        n2=Integer.parseInt (ss9);

                    }

                    Calendar calendar = Calendar.getInstance ( );
                    calendar.set (Calendar.HOUR_OF_DAY, n1);
                    calendar.set (Calendar.MINUTE, n2);
                    Intent intent = new Intent (context, AlarmReciever.class);
                    pendingIntent = PendingIntent.getBroadcast (context, 0, intent, 0);

                    time = (calendar.getTimeInMillis ( ) - (calendar.getTimeInMillis ( ) % 60000));
                    if (System.currentTimeMillis ( ) > time) {
                        if (calendar.AM_PM == 0) time = time + (1000 * 60 * 60 * 12);
                        else time = time + (1000 * 60 * 60 * 24);
                    }
                    alarmManager.setRepeating (AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
                    Toast.makeText (context,"Alert",Toast.LENGTH_SHORT).show ();
                }
                else
                {
                    Toast.makeText (context,"off",Toast.LENGTH_SHORT).show ();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return personNames.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        ToggleButton image1;
        public RelativeLayout relativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.textView);
            image1 = (ToggleButton) itemView.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
