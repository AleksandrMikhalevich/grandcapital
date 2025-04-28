package by.mikhalevich.grandcapitaltesttask.api.controller;

import by.mikhalevich.grandcapitaltesttask.service.intrfc.EmailDataService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author Alex Mikhalevich
 * @created 2025-04-28 13:21
 */
@TestConfiguration
public class EmailDataTestConfig {

    @Bean
    public EmailDataService emailDataService() {
        return Mockito.mock(EmailDataService.class);
    }
}
