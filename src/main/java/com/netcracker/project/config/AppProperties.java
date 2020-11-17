package com.netcracker.project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Properties for app
 */
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    /**
     * Beans for property
     */
    private final Auth auth = new Auth();
    private final OAuth2 oauth2 = new OAuth2();

    /**
     * Auth property
     */
    @Data
    public static class Auth {
        private String tokenSecret;
        private String tokenExpirationMsec;
        private String aliveTime;

        /**
         * Get token expiration msec
         */
        public long getTokenExpirationMsec() {
            return Long.parseLong(tokenExpirationMsec);
        }

        /**
         * Get alive time
         */
        public long getAliveTime() {
            return Long.parseLong(aliveTime);
        }
    }

    public static final class OAuth2 {
        private final List<String> authorizedRedirectUris = new ArrayList<>();

        /**
         * Get authorized redirect uris
         */
        public List<String> getAuthorizedRedirectUris() {
            return authorizedRedirectUris;
        }
    }

    /**
     * Get auth
     */
    public Auth getAuth() {
        return auth;
    }

    /**
     * get Auth2
     */
    public OAuth2 getOauth2() {
        return oauth2;
    }
}
