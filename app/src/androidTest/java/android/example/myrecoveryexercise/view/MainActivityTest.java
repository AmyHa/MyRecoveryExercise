package android.example.myrecoveryexercise.view;



import android.example.myrecoveryexercise.R;
import android.os.SystemClock;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import androidx.annotation.UiThread;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private MainActivity mMainActivity;

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() {
        mMainActivity = activityRule.getActivity();
        assertThat(mMainActivity, notNullValue());
    }

    @Test
    public void appLaunchesSuccessfully() {
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void tabLayoutVisibility() {
        onView(withId(R.id.tab_layout)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void swipePage() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()));
        onView(withId(R.id.view_pager)).perform(swipeLeft());
    }

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
    }


}