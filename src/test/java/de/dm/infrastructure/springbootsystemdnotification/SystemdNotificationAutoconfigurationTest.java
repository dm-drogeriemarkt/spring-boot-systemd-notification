package de.dm.infrastructure.springbootsystemdnotification;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemdNotificationAutoconfigurationTest {

    private ApplicationContextRunner contextRunner;

    @Before
    public void setup() {
        contextRunner = new ApplicationContextRunner()
                .withConfiguration(AutoConfigurations.of(SystemdNotificationAutoconfiguration.class));
    }

    @Test
    public void systemdNotificationServicePresent() {
        this.contextRunner.withPropertyValues("systemd.notification.enabled=true").run((context) -> {
            assertThat(context).hasSingleBean(SystemdNotificationService.class);
        });
    }

    @Test
    public void systemdNotificationServiceAbsent() {
        this.contextRunner.withPropertyValues("systemd.notification.enabled=false").run((context) -> {
            assertThat(context).doesNotHaveBean(SystemdNotificationService.class);
        });
    }
}
