package android.example.myrecoveryexercise.model.objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseContent {

    @SerializedName("slug")
    private String slug;

    @SerializedName("type")
    private String type;

    @SerializedName("title")
    private String title;

    @SerializedName("rateable")
    private boolean rateable;

    @SerializedName("text")
    private String text;


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRateable() {
        return rateable;
    }

    public void setRateable(boolean rateable) {
        this.rateable = rateable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
