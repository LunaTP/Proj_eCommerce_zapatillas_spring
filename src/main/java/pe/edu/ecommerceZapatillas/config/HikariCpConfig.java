package pe.edu.ecommerceZapatillas.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariCpConfig {

    @Value("${DB_ZAPATILLA_URL}")
    private String dbZapatillaUrl;
    @Value("${DB_ZAPATILLA_USER}")
    private String dbZapatillaUser;
    @Value("${DB_ZAPATILLA_PASS}")
    private String dbZapatillaPass;
    @Value("${DB_ZAPATILLA_DRIVER}")
    private String dbZapatillaDriver;

    @Bean
    public HikariDataSource hikariDataSource() {

        HikariConfig config = new HikariConfig();
        /**
         * Configurar propiedad de conexion a BD
         */
        config.setJdbcUrl(dbZapatillaUrl);
        config.setUsername(dbZapatillaUser);
        config.setPassword(dbZapatillaPass);
        config.setDriverClassName(dbZapatillaDriver);

        /**
         * Configurar propiedades del pool de HikariCP
         * - MaximumPoolSize: Máximo # de conexiones del pool.
         * - MinimumIdle: Mínimo # de conexiones inactivas del pool.
         * - IdleTimeout: Tiempo máximo de espera para
         *      eliminar una conexión inactiva.
         * - ConnectionTimeout: Tiempo máximo de espera
         *      para conectarse a la BD.
         */
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.setIdleTimeout(300000);
        config.setConnectionTimeout(30000);

        System.out.println("###### HikariCP initialized ######");
        return new HikariDataSource(config);

    }

}
