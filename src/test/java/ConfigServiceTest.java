import org.example.services.ConfigService;
import org.example.services.imp.ConfigServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConfigServiceTest {
    private static final String PROPERTY_FILE = "src/test/resources/test.properties";

    @Test
    void getValueTest(){
        ConfigService configService = new ConfigServiceImp(PROPERTY_FILE);
        Assertions.assertEquals("testValue123", configService.getProperty("test1"));
    }
}
