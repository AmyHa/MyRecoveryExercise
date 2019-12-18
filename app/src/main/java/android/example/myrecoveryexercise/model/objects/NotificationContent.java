package android.example.myrecoveryexercise.model.objects;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationContent extends BaseContent {

    private static final String TAG = "NotificationContent";

    public String getTrimmedText() {
        String key = "}}";
        int startIndex = super.getText().indexOf(key);
        return super.getText().substring(startIndex + key.length());
    }

    public String getTab() {
        String key = "tab=";
        int startIndex = super.getText().indexOf(key);
        int endIndex = super.getText().indexOf("&", startIndex);

        return super.getText().substring(startIndex + key.length(),endIndex);
    }


    public String getPushPage() {
        String key = "pushPage=";
        int startIndex = super.getText().indexOf(key);
        int endIndex = super.getText().indexOf("&", startIndex);
        return super.getText().substring(startIndex + key.length(),endIndex);
    }

    public String getMilestone() {
        String key = "milestone=";
        int startIndex = super.getText().indexOf(key);
        int endIndex = super.getText().indexOf("&", startIndex);
        return super.getText().substring(startIndex + key.length(),endIndex);
    }

    public String getLocalTime() {
        String key = "localTime=";
        int startIndex = super.getText().indexOf(key);
        int endIndex = super.getText().indexOf("}", startIndex);
        return super.getText().substring(startIndex + key.length(),endIndex);
    }

}
