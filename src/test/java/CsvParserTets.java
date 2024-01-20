import org.example.models.csv.Module;
import org.example.models.csv.Task;
import org.example.models.csv.TaskScore;
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
        Assertions.assertEquals(6, modules.get(1).getMaxScoreByExercise());
        Module module3 = modules.get(2);
        Assertions.assertEquals(2, module3.getMaxScoreByExercise());
        Assertions.assertEquals(20, module3.getMaxScoreByHomework());
        Assertions.assertEquals(4, module3.getMaxScoreByControlQuestion());
        List<TaskScore> taskScore = module3.getTasks().get(0).getScores();
        Assertions.assertEquals(2, taskScore.get(0).getScore());
        Assertions.assertEquals(0, taskScore.get(taskScore.size() - 1).getScore());
        Assertions.assertEquals("Трибушко Данил", taskScore.get(13).getStudent().getFullName());
    }
}
