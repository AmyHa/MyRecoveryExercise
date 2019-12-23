package android.example.myrecoveryexercise.view;

import android.example.myrecoveryexercise.R;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/*
 * Note: our progress bar is replaced with a indeterminate drawable in setUp() as Espresso doesn't
 * work well with animations. We are only interested in checking that it is displayed / not displayed
 * under certain conditions
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MainActivity mMainActivity;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        mMainActivity = activityRule.getActivity();
        assertThat(mMainActivity, notNullValue());

        Drawable notAnimatedDrawable = ContextCompat.getDrawable(activityRule.getActivity(),
                R.mipmap.ic_launcher);
        ((ProgressBar) activityRule.getActivity().findViewById(R.id.progress_bar_toast))
                .setIndeterminateDrawable(notAnimatedDrawable);
        ((ProgressBar) activityRule.getActivity().findViewById(R.id.progress_bar_notification))
                .setIndeterminateDrawable(notAnimatedDrawable);
    }

    @Test
    public void toastProgressBarVisibleOnLoad() {
        onView(withId(R.id.progress_bar_toast)).check(matches(withEffectiveVisibility
                (ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void notificationProgressBarVisibleOnLoad() {
        onView(withId(R.id.progress_bar_notification)).check(matches(withEffectiveVisibility
                (ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void appLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity.class);
    }


    @Test
    public void tabLayoutVisibility() {
        onView(withId(R.id.tab_layout)).check(matches(isDisplayed()));
    }

    // Check that view pager is displayed; user can swipe left
    @Test
    public void swipePage() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()));
        onView(withId(R.id.view_pager)).perform(swipeLeft());
    }

    // Check Toast fragment text views display correct text
    @Test
    public void toastTextViews() {
        SystemClock.sleep(1500);
        onView(allOf(withId(R.id.toast_slug_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("inv-welco-tos")));
        onView(allOf(withId(R.id.toast_type_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("info")));
        onView(allOf(withId(R.id.toast_title_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("Hello {{user.firstName}}")));
        onView(allOf(withId(R.id.toast_rateable_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("true")));
        onView(allOf(withId(R.id.toast_text_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("Once known, set your procedure date in the **Profile** section of the **More** tab, to activate more content.")));
    }

    // Check Notification fragment text views display correct text
    @Test
    public void notificationTextViews() {
        SystemClock.sleep(1500);
        onView(allOf(withId(R.id.not_slug_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("2w-post-inv-not")));
        onView(allOf(withId(R.id.not_type_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("info")));
        onView(allOf(withId(R.id.not_title_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("Hello {{user.firstName}}")));
        onView(allOf(withId(R.id.not_rateable_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("true")));
        onView(allOf(withId(R.id.not_text_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("If you know the date of your procedure, set it in the app now to start tracking your progress")));
        onView(allOf(withId(R.id.not_tab_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("more")));
        onView(allOf(withId(R.id.not_pushPage_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("moreProfile")));
        onView(allOf(withId(R.id.not_milestone_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("operation")));
        onView(allOf(withId(R.id.not_localtime_text_view), isDescendantOfA(withId(R.id.view_pager))))
                .check(matches(withText("15:00")));
    }


    @Test
    public void progressBarsInvisibleAfterSetViews() {
        SystemClock.sleep(1500);
        onView(withId(R.id.progress_bar_notification)).check(matches(not(isDisplayed())));
        onView(withId(R.id.progress_bar_toast)).check(matches(not(isDisplayed())));
    }

}