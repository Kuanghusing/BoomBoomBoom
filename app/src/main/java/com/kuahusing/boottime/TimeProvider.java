package com.kuahusing.boottime;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

enum Time {
    Day, Hour, Minutes
}

/**
 * Created by kuahusing on 16/6/22.
 */
public class TimeProvider extends AppWidgetProvider {
    private static int i = 0;
    private static List<String> stringArray = new ArrayList<>();
    Context mContext;

    private String getTime(long time, Time type) {
//        long week = time / (1000 * 60 * 60 * 24 * 7);

        switch (type) {
            case Day:
                long day = time % (1000 * 60 * 60 * 24 * 7) / (1000 * 60 * 60 * 24);
                return Long.toString(day);

            case Hour:
                long hour = time % (1000 * 60 * 60 * 24) / (1000 * 60 * 60);

                return Long.toString(hour);
            case Minutes:
                long minutes = time % (1000 * 60 * 60) / (1000 * 60);

                return Long.toString(minutes);
            default:
                return null;
        }

    }

/*    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        mContext = context;
        if (stringArray.size() == 0) {
            getString();
        }
        Toast.makeText(context, stringArray.get(i), Toast.LENGTH_SHORT).show();
        i++;
        if (i > 28) {
            i = 0;
        }

    }*/

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
//        mContextext = context;
        long time = SystemClock.elapsedRealtime();


        for (int id :
                appWidgetIds) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.excited);
            rv.setTextViewText(R.id.day, getTime(time, Time.Day));
            rv.setTextViewText(R.id.hour, getTime(time, Time.Hour));
            rv.setTextViewText(R.id.minute, getTime(time, Time.Minutes));

            /*Intent i = new Intent(context, TimeProvider.class);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, i, 0);
            rv.setOnClickPendingIntent(R.id.main, pendingIntent);*/

            rv.setTextViewText(R.id.emoji, getRandomEmoji());


            appWidgetManager.updateAppWidget(id, rv);
            Log.v(context.toString(), "onupdate");

        }
    }

    private String getRandomEmoji() {
        int emojis[] = {0x1F602, 0x1F601, 0x1F605, 0x1F60A, 0x1F618, 0x1F617, 0x1F610, 0x1F60F
                , 0x1F60C, 0x1F614, 0x1F62D, 0x1F631, 0x1F47B, 0x1F648, 0x1F438, 0x1F31A, 0x1F31D};

        //get these emoji code from http://unicode.org/emoji/charts/full-emoji-list.html

        return new String(Character.toChars(emojis[new Random().nextInt(emojis.length - 1)]));

    }


    private void getString() {
        for (int i = 0; i < 29; i++) {
            String s = "warn";
            s += i;
            int resourceId = mContext.getResources().getIdentifier(s, "string", mContext.getPackageName());
            if (resourceId > 0) {
                stringArray.add(mContext.getString(resourceId) + getRandomEmoji());
            }

        }

    }
}
