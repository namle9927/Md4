package ra.md4.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ra.md4.ulti.CurrencyFormatter;

import java.math.BigDecimal;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldType(BigDecimal.class, new CurrencyFormatter());
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
