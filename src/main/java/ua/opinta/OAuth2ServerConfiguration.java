//package ua.opinta;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.OrRequestMatcher;
//
///**
// * Created by Administrator on 6/28/2016.
// */
//@Configuration
//public class OAuth2ServerConfiguration {
//
//    private static final String RESOURCE_ID = "restservice";
//
//    @Configuration
//    @EnableResourceServer
//    protected static class ResourceServerConfiguration extends
//            ResourceServerConfigurerAdapter {
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            // @formatter:off
//            http
//                    .requestMatcher(new OrRequestMatcher(
//                            //new AntPathRequestMatcher("/user"),
//                            new AntPathRequestMatcher("/role")
//                    ))
//                    .authorizeRequests()
//                    .anyRequest().access("#oauth2.hasScope('read')");
//            // @formatter:on
//        }
//
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources)
//                throws Exception {
//            resources.resourceId(RESOURCE_ID);
//        }
////        @Override
////        public void configure(ResourceServerSecurityConfigurer resources) {
////            // @formatter:off
////            resources
////                    .resourceId(RESOURCE_ID);
////            // @formatter:on
////        }
////
////        @Override
////        public void configure(HttpSecurity http) throws Exception {
////            // @formatter:off
////            http
////                    .authorizeRequests()
////                    .antMatchers("/user").hasRole("ADMIN")
////                    .antMatchers("/user").authenticated();
////            // @formatter:on
////        }
//
//
//
//    }
//
//    @Configuration
//    @EnableAuthorizationServer
//    protected static class AuthorizationServerConfiguration extends
//            AuthorizationServerConfigurerAdapter {
//
//        @Autowired
//        private AuthenticationManager authenticationManager;
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
//                throws Exception {
//            endpoints.authenticationManager(authenticationManager);
//        }
//
////        @Override
////        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////            // @formatter:off
////            clients.inMemory().withClient("my-trusted-client")
////                    .authorizedGrantTypes("password", "authorization_code",
////                            "refresh_token", "implicit")
////                    .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
////                    .scopes("read", "write", "trust").resourceIds(RESOURCE_ID)
////                    .accessTokenValiditySeconds(60).and()
////                    .withClient("my-client-with-registered-redirect")
////                    .authorizedGrantTypes("authorization_code").authorities("ROLE_CLIENT")
////                    .scopes("read", "trust").resourceIds(RESOURCE_ID)
////                    .redirectUris("http://anywhere?key=value").and()
////                    .withClient("my-client-with-secret")
////                    .authorizedGrantTypes("client_credentials", "password")
////                    .authorities("ROLE_CLIENT").scopes("read").resourceIds(RESOURCE_ID)
////                    .secret("secret");
////            // @formatter:on
////        }
//
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//            // @formatter:off
//            clients.inMemory().withClient("my-trusted-client")
//                    .authorizedGrantTypes("password", "authorization_code",
//                            "refresh_token", "implicit")
//                    .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//                    .scopes("read", "write", "trust").resourceIds(RESOURCE_ID)
//                    .accessTokenValiditySeconds(60).and()
//                    .withClient("my-client-with-registered-redirect")
//                    .authorizedGrantTypes("authorization_code").authorities("ROLE_CLIENT")
//                    .scopes("read", "trust").resourceIds(RESOURCE_ID)
//                    .redirectUris("http://anywhere?key=value").and()
//                    .withClient("my-client-with-secret")
//                    .authorizedGrantTypes("client_credentials", "password")
//                    .authorities("ROLE_CLIENT").scopes("read").resourceIds(RESOURCE_ID)
//                    .secret("secret");
//            // @formatter:on
//
//
////            http.authorizeRequests().anyRequest().fullyAuthenticated().and().
////                    httpBasic().and().
////                    csrf().disable();
//}
//
////        private TokenStore tokenStore = new InMemoryTokenStore();
////
////        @Autowired
////        @Qualifier("authenticationManagerBean")
////        private AuthenticationManager authenticationManager;
////
////        @Autowired
////        private CustomUserDetailsService userDetailsService;
////
////        @Override
////        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
////                throws Exception {
////            // @formatter:off
////            endpoints
////                    .tokenStore(this.tokenStore)
////                    .authenticationManager(this.authenticationManager)
////                    .userDetailsService(userDetailsService);
////            // @formatter:on
////        }
////
////        @Override
////        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////            // @formatter:off
////            clients
////                    .inMemory()
////                    .withClient("clientapp")
////                    .authorizedGrantTypes("password", "refresh_token")
////                    .authorities("USER")
////                    .scopes("read", "write")
////                    .resourceIds(RESOURCE_ID)
////                    .secret("123456")
////                    ;
////            // @formatter:on
////        }
////
////        @Bean
////        @Primary
////        public DefaultTokenServices tokenServices() {
////            DefaultTokenServices tokenServices = new DefaultTokenServices();
////            tokenServices.setSupportRefreshToken(true);
////            tokenServices.setTokenStore(this.tokenStore);
////            return tokenServices;
////        }
//    }
//}