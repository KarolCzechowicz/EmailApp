package KarolCzechowicz.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import pl.KarolCzechowicz.converter.*;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.KarolCzechowicz")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.KarolCzechowicz.repository")
public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("rentOfferPersistenceUnit");
        return emfb;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager tm = new JpaTransactionManager(emf);
        return tm;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getManufacturerConverter());
        registry.addConverter(getCarModelConverter());
        registry.addConverter(getFuelConverter());
        registry.addConverter(getDriveTypeConverter());
        registry.addConverter(getSeatConverter());
        registry.addConverter(getTransmissionConverter());
        registry.addConverter(getOfferConverter());
        registry.addConverter(getUserConverter());
        registry.addConverter(getCarConverter());
    }

    @Bean
    public ManufacturerConverter getManufacturerConverter() {
        return new ManufacturerConverter();
    }

    @Bean
    public CarModelConverter getCarModelConverter() {
        return new CarModelConverter();
    }

    @Bean
    public FuelConverter getFuelConverter() {
        return new FuelConverter();
    }

    @Bean
    public DriveTypeConverter getDriveTypeConverter() {
        return new DriveTypeConverter();
    }

    @Bean
    public SeatConverter getSeatConverter() {
        return new SeatConverter();
    }

    @Bean
    public TransmissionConverter getTransmissionConverter() {
        return new TransmissionConverter();
    }

    @Bean
    public OfferConverter getOfferConverter() {
        return new OfferConverter();
    }

    @Bean
    public UserConverter getUserConverter() {
        return new UserConverter();
    }

    @Bean
    public CarConverter getCarConverter() {
        return new CarConverter();
    }

    @Bean(name = "localeResolver")
    public LocaleContextResolver getLocaleContextResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pl", "PL"));
        return localeResolver;
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}