package guru.sfg.brewery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by jt on 6/13/20.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // osa sellest sisust on pärit super klassist
                http
                .authorizeRequests(authorize -> { // index leht ja erinevad fronti .js jmt koodi kaustad, et saaks kuvada lehte
                    // vaatas brauseris Network alt, et millistele staatilistele ressurssidele ei p22se ligi
                    authorize.antMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll();
                } )
                .authorizeRequests()
                .anyRequest().authenticated() // kõik erandid peavad olema toodud yleval pool seda rida
                .and()
                .formLogin().and()
                .httpBasic();
    }
}
