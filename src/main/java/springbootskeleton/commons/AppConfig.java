package springbootskeleton.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springbootskeleton.service.UpdateService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by yogeshverma on 10/1/18.
 */

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.freedom")
public class AppConfig extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateService.class);

    @Autowired
    @Qualifier("masterEntityManagerFactory")
    private LocalContainerEntityManagerFactoryBean switchEntityManagerFactory;

    @PreDestroy
    public void destroyAll() {
        LOGGER.info("********************Shutting Down Application********************");
    }

    @PostConstruct
    public void initialize() {
        LOGGER.info("********************Starting Application********************");
    }
}