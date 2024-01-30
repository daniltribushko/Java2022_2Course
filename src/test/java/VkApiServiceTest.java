import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.example.models.vk.VkGroup;
import org.example.models.vk.VkStudent;
import org.example.services.VKApiGroupService;
import org.example.services.VkApiService;
import org.example.services.VkApiStudentService;
import org.example.services.imp.VkApiGroupServiceImp;
import org.example.services.imp.VkApiServiceImp;
import org.example.services.imp.VkApiStudentServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VkApiServiceTest {
    private VkApiClient apiClient;
    private UserActor actor;

    @BeforeEach
    void getActorAndApiClient() {
        VkApiService apiService = new VkApiServiceImp();
        apiClient = apiService.getApiClient();
        actor = apiService.getActor();
    }

    @Test
    void getApiTest() throws ClientException, InterruptedException, ApiException {
        VkApiStudentService studentService = new VkApiStudentServiceImp();
        VKApiGroupService groupService = new VkApiGroupServiceImp();
        VkGroup vkGroup = groupService.getGroup(
                apiClient,
                actor,
                "Проектный практикум").orElse(null
        );
        VkStudent student = studentService.getStudent(
                apiClient,
                actor,
                "Трибушко Данил",
                vkGroup.getId()
        );
        Assertions.assertNull(studentService.getStudent(
                apiClient,
                actor,
                "F F",
                1L)
        );
        Assertions.assertEquals("Трибушко Данил", student.getName());
        Assertions.assertEquals("Проектный практикум", vkGroup.getName());
        Assertions.assertTrue(groupService.isMember(
                apiClient,
                actor,
                String.valueOf(vkGroup.getId()),
                student.getVkId())
        );
    }
}
