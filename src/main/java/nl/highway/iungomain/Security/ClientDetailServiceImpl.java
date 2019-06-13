package nl.highway.iungomain.Security;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailServiceImpl implements ClientDetailsService {

    public static final String APP_CLIENT = "app";
    public static final String CLIENT_SCOPES = "read,write";
    static final String RESOURCE_ID = "iungo";

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        if ("app".equals(clientId)) {
            return new BaseClientDetails(
                    APP_CLIENT,
                    RESOURCE_ID,
                    CLIENT_SCOPES,
                    "password",
                    "");
        }
        throw new ClientRegistrationException("Invalid client id '" + clientId + "'");
    }
}