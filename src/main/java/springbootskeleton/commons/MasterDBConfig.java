package springbootskeleton.commons;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.ConnectionReleaseMode;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef="masterEntityManagerFactory",
		transactionManagerRef="masterTransactionManager",
		basePackages= {"springbootskeleton.datasource"})
public class MasterDBConfig {

		@Primary
		@Bean(name = "masterHikariConfig")
		@ConfigurationProperties(prefix = "master.datasource")
		public HikariConfig hikariConfig() {
			HikariConfig hikariConfig = new HikariConfig();
			return hikariConfig;
		}

		@Primary
		@Bean(name = "masterDataSource")
		public DataSource dataSource(@Qualifier("masterHikariConfig") HikariConfig hikariConfig) {
			return  new HikariDataSource(hikariConfig);
		}

		protected Map<String, Object> jpaProperties() {
			Map<String, Object> props = new HashMap<>();
			props.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
			props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
			props.put("hibernate.naming-strategy", ImprovedNamingStrategy.class.getName());
			props.put("hibernate.connection.release_mode", ConnectionReleaseMode.AFTER_TRANSACTION);
			return props;
		}

		@Primary
		@Bean(name = "masterEntityManagerFactory")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(
				EntityManagerFactoryBuilder builder,
				@Qualifier("masterDataSource") DataSource dataSource) {
			return builder.dataSource(dataSource)
					.packages("springbootskeleton.datasource.model")
					.properties(jpaProperties())
					.build();
		}

		@Bean(name = "masterTransactionManager")
		public PlatformTransactionManager transactionManager(
				@Qualifier("masterEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
			return new JpaTransactionManager(entityManagerFactory);
		}
}
