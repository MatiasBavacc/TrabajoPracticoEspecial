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
            .securityMatcher("/**" )
            .authorizeHttpRequests( authz -> authz
                    // Swagger y OpenAPI libres
                    .requestMatchers(
                            "/swagger-ui.html",
                            "/swagger-ui/**",
                            "/v3/api-docs/**"
                    ).permitAll()
                    // FALTA IMPLEMENTAR QUIEN PUEDE ACCEDER A QUE
                    .requestMatchers(HttpMethod.POST, "/token").permitAll()
                    .requestMatchers(HttpMethod.POST, "/registrar").permitAll()
                    // MS-PARADAS
                    .requestMatchers(HttpMethod.GET, "/parada/**").hasAnyAuthority( AuthotityConstant._USUARIO, AuthotityConstant._ADMIN )
                    .requestMatchers("/parada/**").hasAuthority( AuthotityConstant._ADMIN )
                    // MS-USUARIO Y CUENTA
                    .requestMatchers("/usuario/").hasAuthority( AuthotityConstant._ADMIN)
                    .requestMatchers(HttpMethod.PUT, "/usuario/modificar").hasAuthority( AuthotityConstant._ADMIN)
                    .requestMatchers(HttpMethod.DELETE, "/usuario/agregar").hasAnyAuthority( AuthotityConstant._ADMIN)
                    .requestMatchers("/cuenta/**").hasAuthority( AuthotityConstant._ADMIN)
                    // MOOK-MERCADO PAGO
                    .requestMatchers("/mercadopago", "/mercadopago/").permitAll()
                    .requestMatchers("/mercadopago/**").permitAll() // si querés que subrutas también sean públicas
                    // MS-MONOPATINES
                    .requestMatchers("/monopatin/").hasAuthority( AuthotityConstant._ADMIN)
                    .requestMatchers(HttpMethod.GET, "/monopatin/").hasAnyAuthority( AuthotityConstant._USUARIO, AuthotityConstant._ADMIN)
                    .requestMatchers("/monopatin/**").hasAuthority( AuthotityConstant._ADMIN )
                    // MS-FACTURAS Y TARIFA SOLO ADMINS
                    .requestMatchers("/facturas/**").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/tarifa/**").hasAuthority( AuthotityConstant._ADMIN )
                    // MS-VIAJES
                    .requestMatchers("/viaje/reportes").hasAuthority( AuthotityConstant._ADMIN )
                    .requestMatchers("/viaje/**").hasAnyAuthority( AuthotityConstant._USUARIO, AuthotityConstant._ADMIN )
                    .anyRequest().authenticated()
            )
            .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

}
