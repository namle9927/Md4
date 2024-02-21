package ra.md4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers(HttpMethod.GET, "/css/**", "/static/js/**","/fonts/**","/favicon.ico", "/about","/assets/logo/**").permitAll()
//                        // Ko cần check authencation
//                        .requestMatchers("/save**", "/home","/").permitAll()
//                        .requestMatchers("/admin**").hasRole("ADMIN")
//                        // tất cả các request còn lại đều phải xác thực
//                        .anyRequest().authenticated()
                                .requestMatchers("/**").permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/", true)
                        .successHandler((request, response, authentication) -> {
                            for(GrantedAuthority authority : authentication.getAuthorities()){
                                if(authority.getAuthority().equals("ADMIN") || authority.getAuthority().equals("MODERATOR")){
                                    response.sendRedirect("/admin/user");
                                }else if (authority.getAuthority().equals("USER")){
                                    response.sendRedirect("/home");
                                }
                            }
                        })
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                )

                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            // Xử lý khi truy cập bị từ chối
                            response.sendRedirect("/notfound");
                        })
                        .authenticationEntryPoint((request, response, authException) -> {
                            // Xử lý khi xác thực không thành công (chưa đăng nhập)
                            response.sendRedirect("/login");
                        })
                );
        ;
        return http.build();
    }
}
