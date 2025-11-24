package grupo30.gateway.config;

import grupo30.gateway.security.AuthotityConstant;
import grupo30.gateway.security.jwt.JwtFilter;
import grupo30.gateway.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain( final HttpSecurity http ) throws Exception {
        http
            .csrf( AbstractHttpConfigurer::disable );
        http
            .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
            .securityMatcher("/**" )// FALTA IMPLEMENTAR QUIEN PUEDE ACCEDER A QUE
            .authorizeHttpRequests( authz -> authz
                    .requestMatchers(HttpMethod.POST, "/token").permitAll()
                    .requestMatchers(HttpMethod.POST, "/registrar").permitAll()
                    // MS-PARADAS
                    .requestMatchers(HttpMethod.GET, "/paradas/**").hasAnyAuthority( AuthotityConstant._USUARIO, AuthotityConstant._ADMIN )
                    .requestMatchers("/paradas/**").hasAuthority( AuthotityConstant._ADMIN )
                    // MS-USUARIO Y CUENTA
                    .requestMatchers("/usuario").hasAuthority( AuthotityConstant._ADMIN)
                    .requestMatchers(HttpMethod.PUT, "/usuario/modificar").hasAuthority( AuthotityConstant._ADMIN)
                    .requestMatchers(HttpMethod.DELETE, "/usuario/agregar").hasAnyAuthority( AuthotityConstant._ADMIN)
                    .requestMatchers("/cuenta/**").hasAuthority( AuthotityConstant._ADMIN)
                    // MS-MONOPATINES
                    .requestMatchers("/monopatines/reportes-mantenimiento/{kmMaximo}").hasAuthority( AuthotityConstant._ADMIN)
                    .requestMatchers(HttpMethod.GET, "/api/monopatines/**").hasAnyAuthority( AuthotityConstant._USUARIO, AuthotityConstant._ADMIN)
                    .requestMatchers("/monopatines/**").hasAuthority( AuthotityConstant._ADMIN )
                    // MS-FACTURAS Y TARIFA SOLO ADMINS
                    .requestMatchers("/facturas/**").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/tarifas/**").hasAuthority( AuthotityConstant._ADMIN )
                    // MS-VIAJES
                    .requestMatchers("/viajes/reportes").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/viajes/**").hasAnyAuthority( AuthotityConstant._USUARIO, AuthotityConstant._ADMIN )
                    .anyRequest().authenticated()
            )
            .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

}
