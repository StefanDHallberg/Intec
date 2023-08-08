package intec.inteceksame.framework_drivers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseSource {

    @Bean(name = "dkDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dk")
    public DataSource dkDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "usDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.us")
    public DataSource usDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "usJdbcTemplate")
    public JdbcTemplate usJdbcTemplate(DataSource usDataSource) {
        return new JdbcTemplate(usDataSource);
    }

    @Bean(name = "dkJdbcTemplate")
    public JdbcTemplate dkJdbcTemplate(DataSource dkDataSource) {
        return new JdbcTemplate(dkDataSource);
    }


}