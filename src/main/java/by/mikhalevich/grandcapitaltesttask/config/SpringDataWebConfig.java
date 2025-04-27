package by.mikhalevich.grandcapitaltesttask.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Конфигурация для включения поддержки DTO для страниц (Page)
 * @author Alex Mikhalevich
 * @created 2025-04-27 14:46
 */
@Configuration
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class SpringDataWebConfig {
}
