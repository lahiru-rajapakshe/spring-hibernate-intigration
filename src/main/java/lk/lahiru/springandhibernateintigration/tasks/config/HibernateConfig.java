package lk.lahiru.springandhibernateintigration.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-prod.properties")

public class HibernateConfig {

    @Bean
    public DataSource dataSource(){
        JndiObjectFactoryBean jndiDataSource = new JndiObjectFactoryBean();
        jndiDataSource.setJndiName("java:comp/env/jdbc/pool");
        return (DataSource) jndiDataSource.getObject();
    }

    public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
        LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
        lsfb.setDataSource(dataSource);
        lsfb.setPackagesToScan("lk.ijse.dep8.tasks.entity");
        return lsfb;
    }



}
