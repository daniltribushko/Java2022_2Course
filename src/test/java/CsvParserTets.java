import org.example.models.csv.Module;
import org.example.services.CsvParser;
import org.example.services.imp.CsvParserImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CsvParserTest {
    @Test
    void getModulesTest(){
        CsvParser csvParser = new CsvParserImp();
        List<Module> modules = csvParser.getModuleFromCsv(1);
        Assertions.assertEquals(8, modules.get(0).getMaxScoreByControlQuestion());
        Assertions.assertEquals(2, modules.get(1).getMaxScoreByExercise());
        Module module3 = modules.get(2);
        Assertions.assertEquals(2, module3.getMaxScoreByExercise());
        Assertions.assertEquals(20, module3.getMaxScoreByHomework());
        Assertions.assertEquals(4, module3.getMaxScoreByControlQuestion());
    }
}
