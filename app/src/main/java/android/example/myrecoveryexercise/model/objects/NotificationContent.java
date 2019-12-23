package android.example.myrecoveryexercise.model.objects;

public class NotificationContent extends BaseContent {

    private static final String TAG = "NotificationContent";

    public String getTrimmedText() {
        String key = "}}";
        int startIndex = super.getText().indexOf(key);
        return super.getText().substring(startIndex + key.length());
    }

    public String getTab() {
        String key = "tab=";
        return returnValueOfKey(key, "&");
    }


    public String getPushPage() {
        String key = "pushPage=";
        return returnValueOfKey(key, "&");
    }

    public String getMilestone() {
        String key = "milestone=";
        return returnValueOfKey(key, "&");
    }

    public String getLocalTime() {
        String key = "localTime=";
        return returnValueOfKey(key, "}");
    }

    // Returns substring between Key string and a specific character (endChar)
    String returnValueOfKey(String key, String endChar) {
        int startIndex = super.getText().indexOf(key);
        int endIndex = super.getText().indexOf(endChar, startIndex);
        return super.getText().substring(startIndex + key.length(),endIndex);
    }

}
