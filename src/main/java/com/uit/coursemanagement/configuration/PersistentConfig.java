package com.uit.coursemanagement.configuration;

import com.uit.coursemanagement.domain.user.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class PersistentConfig {

    @Bean(name = "jpaAuditorProvider")
    public AuditorAware<User> jpaAuditorProvider() {
        return new JpaAuditorAware();
    }
}
