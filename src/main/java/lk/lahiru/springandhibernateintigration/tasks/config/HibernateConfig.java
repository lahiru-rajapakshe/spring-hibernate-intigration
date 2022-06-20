package lk.lahiru.springandhibernateintigration.tasks.config;

import org.omg.CORBA.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-prod.properties")
@EnableTransactionManager
public class HibernateConfig {
private final Environment env;

public HibernateConfig(Environment env){
    this.env=env;
}
    @Bean
    public DataSource dataSource(){
        JndiObjectFactoryBean jndiDataSource = new JndiObjectFactoryBean();
        jndiDataSource.setJndiName("java:comp/env/jdbc/pool");
        jndiDataSource.setResourcereference(true);
        return (DataSource) jndiDataSource.getObject();
    }


    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        lsfb.setPackagesToScan("lk.ijse.dep8.tasks.entity");
        return lsfb;
    }

peivate Properties hibernateProperties(){
        Properties prop = new Properties();
        prop.put("hibernate.dialet",env.getRequiredProperty("hibernate.dialet"));
        prop.put("hibernate.allow.refresh_dialet_entity",env.getRequiredProperty("hibernate.dialet"));

    }

    public PlatformTransactionManager platformTransactionManager (Sessionfactory sf){
    retrun new HibernatetransactionManager(sf);
    }


}
