package org.example.services.imp;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.example.services.ConfigService;
import org.example.services.VkApiService;

public class VkApiServiceImp implements VkApiService {
    private final VkApiClient vk;
    private final UserActor actor;

    public VkApiServiceImp(){
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        ConfigService configService = new ConfigServiceImp("src/main/resources/config.properties");
        actor = new UserActor(Long.parseLong(configService.getProperty("vk.api.id")),
                configService.getProperty("vk.api.token"));
    }

    @Override
    public VkApiClient getApiClient() {
        return vk;
    }

    @Override
    public UserActor getActor() {
        return actor;
    }
}
