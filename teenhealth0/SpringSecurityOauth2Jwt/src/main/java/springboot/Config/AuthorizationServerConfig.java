package springboot.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerSecurityConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    // 该对象用来支持 password 模式
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    // 该对象用来将令牌信息存储到内存中
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //1.配置客户端详细信息服务
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory().withClient("c1")     //client_id
                .secret(new BCryptPasswordEncoder().encode("secret"))   //客户端秘钥
                .authorizedGrantTypes("authorization_code","password",
                        "client_credentials","implicit","refresh_token")    //  该client允许的5种授权类型
                .accessTokenValiditySeconds(1800)   // 配置access_token的过期时间
                .resourceIds("res1")    //客户端可访问的资源列表
                .scopes("all")          //允许的授权范围 其中all为标识
                .autoApprove(true)      //如果是授权码模式，false将跳转到授权码页面,true直接发送令牌
                .redirectUris("http://localhost:8080/teacher/homepage");  //回调机制
    }

    //2.令牌管理服务
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services=new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService); //客户端信息服务
        services.setSupportRefreshToken(true);                  //是否产生刷新令牌
        services.setTokenStore(tokenStore);                     //令牌的存储策略
        //JWT 设置令牌的增强
        TokenEnhancerChain tokenEnhancerChain=new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);

        services.setAccessTokenValiditySeconds(7200);           //令牌默认有效期2小时
        services.setRefreshTokenValiditySeconds(259200);        //刷新令牌默认有效期3天
        return services;
    }

    //2.令牌访问端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer){
        endpointsConfigurer
                .authenticationManager(authenticationManager)           //认证管理器 密码模式需要
                .authorizationCodeServices(authorizationCodeServices)   //授权码服务 授权码模式需要
                .tokenServices(tokenServices())                         //令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);   //允许post提交
    }

    //3.令牌访问端点安全策略配置
    @Override
    public void configure(AuthorizationServerSecurityConfigurer serverSecurityConfigurer) throws Exception{
        serverSecurityConfigurer.tokenKeyAccess("permitAll()")  //oauth/token_key是公开
                .checkTokenAccess("permitAll()")                //oauth/check_token 资源服务远程校验令牌合法性
                .allowFormAuthenticationForClients()            //表单认证，申请令牌
                ;
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }

}
