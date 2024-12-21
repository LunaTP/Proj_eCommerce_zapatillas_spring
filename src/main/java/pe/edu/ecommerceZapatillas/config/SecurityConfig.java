package pe.edu.ecommerceZapatillas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pe.edu.ecommerceZapatillas.dto.UsuariosDto;
import pe.edu.ecommerceZapatillas.service.MaintenanceService;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private MaintenanceService maintenanceService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Habilitar CORS globalmente
                .cors().and()
                // Deshabilitar CSRF para APIs (solo si es necesario, por ejemplo para SPA)
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        // Permitir acceso sin autenticación a la API
                        .requestMatchers("/api/**").permitAll()
                        // Permitir acceso sin autenticación a la página de login
                        .requestMatchers("/web/login").permitAll()
                        // Restringir acceso a la ruta de usuarios solo a usuarios con rol Admin
                        .requestMatchers("/web/usuarios").hasRole("Admin")
                        // Cualquier otra solicitud debe estar autenticada
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.sendRedirect("/web/restricted");
                        })
                )
                .formLogin(form -> form
                        .loginPage("/web/login")
                        .defaultSuccessUrl("/web/usuarios", false)
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            response.sendRedirect("/web/usuarios");
                        })
                )
                .logout(logout -> logout
                        .logoutUrl("/web/logout")
                        .logoutSuccessUrl("/web/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    // Configuración de CORS para permitir solicitudes desde Angular (localhost:4200)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Configuración CORS para las rutas que empiecen con /api
                .allowedOrigins("http://localhost:4200")  // Permite solicitudes solo desde Angular (localhost:4200)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permite todas las cabeceras
                .allowCredentials(true);  // Permite enviar credenciales como cookies
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UsuariosDto usuario = maintenanceService.getUsuarioByEmail(username);

            if (usuario == null) {
                throw new UsernameNotFoundException("Usuario no encontrado: " + username);
            }

            // Mapea los roles del usuario
            String role = usuario.rolId() == 1 ? "Admin" : "Usuario";

            return User.builder()
                    .username(usuario.email())
                    .password(passwordEncoder().encode(usuario.contrasenia()))
                    .roles(role)
                    .build();
        };
    }
}
