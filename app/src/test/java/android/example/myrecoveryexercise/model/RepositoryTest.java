package android.example.myrecoveryexercise.model;

import android.example.myrecoveryexercise.Contract;
import android.example.myrecoveryexercise.model.objects.NotificationContent;
import android.example.myrecoveryexercise.model.objects.ToastContent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

/*
 * This test class assumes that Retrofit works; we will not be hitting the API
 * We are only checking that the repository can pass fetched data to its listeners
 */

public class RepositoryTest {

    @Mock
    private ToastContent mockToastContent;
    @Mock
    private NotificationContent mockNotContent;


    @Mock
    private Contract.Model.OnToastLoadFinishedListener mockOnToastLoadFinishedListener;

    @Mock Contract.Model.OnNotificationFinishedListener mockOnNotLoadFinishedListener;


    @Mock
    private List<ToastContent> mockToastResponse;

    @Mock
    private List<NotificationContent> mockNotResponse;


    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }


    // For the next two tests, assume that a response body has been received successfully

    @Test
    public void setToastDataOnSuccessfulResponse() {

        mockToastContent = mockToastResponse.get(0);
        mockOnToastLoadFinishedListener.onToastSuccess(mockToastContent);

    }

    @Test
    public void setNotDataOnSuccessfulResponse() {

        mockNotContent = mockNotResponse.get(0);
        mockOnNotLoadFinishedListener.onNotificationSuccess(mockNotContent);

    }

    // For the next two tests, assume that the repository failed to receive a response body

    @Test
    public void setToastThrowableOnFailedResponse() {

        Throwable t = new Exception();
        mockOnToastLoadFinishedListener.onToastFailed(t);

    }

    @Test
    public void setNotificationThrowableOnFailedResponse() {

        Throwable t = new Exception();
        mockOnNotLoadFinishedListener.onNotificationFailed(t);

    }


}