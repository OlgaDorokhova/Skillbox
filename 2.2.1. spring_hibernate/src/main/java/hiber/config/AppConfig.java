package hiber.config;

import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(value = "hiber")
public class AppConfig {

   private final Environment env;

   @Autowired
   public AppConfig(Environment env) {
      this.env = env;
   }

   @Bean
   public DataSource getDataSource() {
      DriverManagerDataSource dataSource = null;
      try {
         dataSource = new DriverManagerDataSource();
         dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("db.driver")));
         dataSource.setUrl(env.getProperty("db.url"));
         dataSource.setUsername(env.getProperty("db.username"));
         dataSource.setPassword(env.getProperty("db.password"));
         dataSource.setSchema(env.getProperty("db.schema"));
         System.out.println("Data source right..................................");
         return dataSource;
      } catch (Exception e) {
         System.out.println("Data source error!!!!!!!!!!!!!!!!!!==================");
      }
      return dataSource;
   }

   @Bean
   public LocalSessionFactoryBean getSessionFactory() {
      LocalSessionFactoryBean factoryBean = null;

      try {
         factoryBean = new LocalSessionFactoryBean();
         factoryBean.setDataSource(getDataSource());
         Properties props = new Properties();
         props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
         props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
         props.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
         props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

         factoryBean.setHibernateProperties(props);
         factoryBean.setAnnotatedClasses(User.class);

         System.out.println("Get factory successful......................................");
         return factoryBean;
      } catch (Exception e) {
         System.out.println("Get factory error!!!!!!!!!!!!!!!!-------------------------------");
      }
      return factoryBean;
   }

   @Bean
   public HibernateTransactionManager getTransactionManager() {
      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
      transactionManager.setSessionFactory(getSessionFactory().getObject());
      return transactionManager;
   }
}
