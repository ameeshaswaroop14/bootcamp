package com.commerceApp.commerceApp;


import com.commerceApp.commerceApp.repositories.UserRepository;
import com.commerceApp.commerceApp.util.AuditingDateTimeProvider;
//import com.commerceApp.commerceApp.util.AuditorAwareImpl;
import com.commerceApp.commerceApp.util.AuditorAwareImpl;
import com.commerceApp.commerceApp.util.CurrentTimeDateTimeService;
import com.commerceApp.commerceApp.util.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan({"com.commerceApp.commerceApp.repositories"})
@ComponentScan(basePackages={"com.commerceApp.commerceApp"})

public class PersistenceConfig {

    @Autowired
    UserRepository userRepository;

   @Bean
    AuditorAware<String> auditorProvider() {
      return new AuditorAwareImpl();
    }



    @Bean
    DateTimeProvider dateTimeProvider(DateTimeService dateTimeService) {
        return new AuditingDateTimeProvider(dateTimeService);
    }
    @Bean
    DateTimeService currentTimeDateTimeService() {
        return new CurrentTimeDateTimeService();
    }
}


