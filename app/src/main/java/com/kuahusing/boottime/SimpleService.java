package com.kuahusing.boottime;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kuahusing on 16/6/22.
 */
public class SimpleService extends IntentService {
    private static int i = 0;
    private static List<String> stringArray = new ArrayList<>();

    public SimpleService(String name) {
        super(name);
    }

    public SimpleService() {
        super("SimpleService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (stringArray.size() == 0) {
            getString();
        }
        Toast.makeText(SimpleService.this, stringArray.get(i), Toast.LENGTH_SHORT).show();
        i++;
        if (i > 28) {
            i = 0;
        }
        Log.v(this.toString(), "onCreate");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
    }

    private void getString() {
        for (int i = 0; i < 29; i++) {
            String s = "warn";
            s += i;
            int resourceId = getResources().getIdentifier(s, "string", this.getPackageName());
            if (resourceId > 0) {
                stringArray.add(getString(resourceId) + getRandomEmoji());
            }

        }

    }

    private String getRandomEmoji() {
        int emojis[] = {0x1F602, 0x1F601, 0x1F605, 0x1F60A, 0x1F618, 0x1F617, 0x1F610, 0x1F60F
                , 0x1F60C, 0x1F614, 0x1F62D, 0x1F631, 0x1F47B, 0x1F648, 0x1F438, 0x1F31A, 0x1F31D};

        //get these emoji code from http://unicode.org/emoji/charts/full-emoji-list.html

        return new String(Character.toChars(emojis[new Random().nextInt(emojis.length - 1)]));

    }
}
