package de.dm.infrastructure.springbootsystemdnotification;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SystemdNotificationServiceTest {

    @Mock
    private SystemdNotificationProperties systemdNotificationProperties;

    @Mock
    private ApplicationReadyEvent applicationReadyEvent;

    @Mock
    private ConfigurableApplicationContext context;

    @Mock
    private SDNotifyWrapper sdNotifyWrapper;

    private SystemdNotificationService systemdNotificationService;

    @Before
    public void setup() {
        System.clearProperty("NOTIFY_SOCKET");
        when(applicationReadyEvent.getApplicationContext()).thenReturn(context);
        systemdNotificationService = new SystemdNotificationService(systemdNotificationProperties, sdNotifyWrapper);
    }

    @Test
    public void enabledAndSocketEnvVarHasBeenSetBySystemD() {

        when(systemdNotificationProperties.isEnabled()).thenReturn(true);

        System.setProperty("NOTIFY_SOCKET", "something");

        systemdNotificationService.onApplicationEvent(applicationReadyEvent);

        verify(sdNotifyWrapper).sendNotify();
        verify(sdNotifyWrapper).sendStatus("Application Context is ready!");
    }

    @Test
    public void disabled() {

        when(systemdNotificationProperties.isEnabled()).thenReturn(false);

        systemdNotificationService.onApplicationEvent(applicationReadyEvent);

        verifyZeroInteractions(sdNotifyWrapper);
    }

    @Test
    public void notifySocketEnvVarHasNotBeenSetBySystemD() {

        when(systemdNotificationProperties.isEnabled()).thenReturn(true);

        systemdNotificationService.onApplicationEvent(applicationReadyEvent);

        verifyZeroInteractions(sdNotifyWrapper);
    }
}
