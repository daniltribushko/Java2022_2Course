package org.example.services.imp;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.GroupFull;
import org.example.models.vk.VkGroup;
import org.example.services.VKApiGroupService;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 24.01.2024
 * <p>
 * Реализация сервиса по работе с сообществами вк
 */
public class VkApiGroupServiceImp implements VKApiGroupService {
    @Override
    public Optional<VkGroup> getGroup(VkApiClient apiClient, UserActor actor, String name) throws ClientException, ApiException {
        GroupFull groupFull = apiClient.groups()
                .search(actor)
                .q(name)
                .execute()
                .getItems()
                .stream()
                .filter(g -> g.getName().equals(name))
                .findFirst()
                .orElse(null);
        VkGroup vkGroup = null;
        if (groupFull != null) {
            vkGroup = new VkGroup(groupFull.getId(), groupFull.getName());
        }
        return Optional.ofNullable(vkGroup);
    }

    @Override
    public boolean isMember(VkApiClient apiClient, UserActor actor, String groupName, Long userId) throws ClientException, ApiException {
        String isMember = apiClient.groups()
                .isMember(actor)
                .groupId(groupName)
                .userId(userId)
                .execute()
                .getValue();
        return isMember.equals("1");
    }
}
