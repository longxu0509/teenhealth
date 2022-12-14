package springboot.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import springboot.service.Impl.MyUserDetailsServiceImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService myUserDetailsService(){
        return new MyUserDetailsServiceImpl();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SuccessHandler successHandler;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    //?????????????????????
    @Bean
    public AuthenticationManager authenticationManagerBean()throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers("/userlogin").permitAll()
                .antMatchers("/tph/**").permitAll()
//                .antMatchers("/teacher/**").hasRole("USER")
                .antMatchers("/teacher/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.OPTIONS)//??????????????????????????????options??????
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/userlogin")
//                .defaultSuccessUrl("/loginSuccess")
                .successHandler(successHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .sessionManagement()
                .maximumSessions(1)     //??????????????????????????????????????????
                .expiredSessionStrategy(sessionInformationExpiredStrategy)  //????????????????????????????????????????????????(??????????????????)
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        //?????????????????????????????????
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/userlogin"); //???????????????????????????
        //????????????????????????,????????????60s
        http.rememberMe().tokenRepository(persistentTokenRepository())  //???????????????token
                .rememberMeParameter("rememberMe").tokenValiditySeconds(60*60);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //????????????????????????????????????
        web.ignoring().antMatchers("/css/**","/font/**","/img/**","/js/**","/assets/**");
//        /**/*.js","/**/*.css","/**/*.jpg","/**/*.png","/**/*.gif","/**/*.ttf","/**/*.eot","/**/*.woff
        //  "/static/css/**","/font/**","/img/**","/js/**","/assets/**"

    }
    //????????????????????? ????????????
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource); // ???????????????
//        tokenRepository.setCreateTableOnStartup(true); // ??????????????????????????????????????????
        return tokenRepository;
    }

}
