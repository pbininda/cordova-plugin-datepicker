package com.plugin.datepicker;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

public class CustomTimeIntervalDialog extends TimePickerDialog {

    private int interval = 15;
    private boolean mIgnoreEvent = false;

    public CustomTimeIntervalDialog(Context context, OnTimeSetListener callBack, 
    		int hourOfDay, int minute, boolean is24HourView, int interval) {
    	super(context, callBack, hourOfDay, minute, is24HourView);
    	this.interval = interval;
    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
        super.onTimeChanged(timePicker, hourOfDay, minute);
        if (!mIgnoreEvent){
            minute = getRoundedMinute(minute);
            mIgnoreEvent=true;
            timePicker.setCurrentMinute(minute);
            mIgnoreEvent=false;
        }
    }

    public int getRoundedMinute(int minute){
         if(minute % interval != 0){
            int minuteFloor = minute - (minute % interval);
            minute = minuteFloor + (minute == minuteFloor + 1 ? interval : 0);
            if (minute == 60)  minute=0;
         }

        return minute;
    }
}

