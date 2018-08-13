package de.dm.infrastructure.springbootsystemdnotification;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SystemdNotificationProperties.class)
public class SystemdNotificationAutoconfiguration {

    @ConditionalOnProperty(name = "systemd.notification.enabled")
    @ConditionalOnMissingBean
    @Bean
    public SystemdNotificationService systemdNotificationService(SystemdNotificationProperties systemdNotificationProperties) {
        return new SystemdNotificationService(systemdNotificationProperties);
    }
}
