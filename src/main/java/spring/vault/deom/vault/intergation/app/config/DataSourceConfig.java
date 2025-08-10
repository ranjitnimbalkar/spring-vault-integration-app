package spring.vault.deom.vault.intergation.app.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
public class DataSourceConfig {

    @Autowired
    VaultTemplate vaultTemplate;

    @Bean
    public DataSource dataSource() {

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mysql-docker");
        dataSource.setUsername(getSecret("username"));
        dataSource.setPassword(getSecret("password"));
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // Optional HikariCP tuning
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(2);
        dataSource.setIdleTimeout(300000);
        dataSource.setMaxLifetime(1800000);

        return dataSource;
    }

    private String getSecret(String key) {
        VaultResponse secret = vaultTemplate
                                 .opsForKeyValue("secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2)
                                 .get("db/secrets");
        return Objects.requireNonNull(secret.getData()).get(key).toString();
    }
}
