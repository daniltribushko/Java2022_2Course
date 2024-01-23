import org.example.models.csv.ModuleCsv;
import org.example.models.csv.TaskScoreCsv;
import org.example.services.CsvParser;
import org.example.services.imp.CsvParserImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CsvParserTest {
    @Test
    void getModulesTest(){
        CsvParser csvParser = new CsvParserImp();
        List<ModuleCsv> moduleCsvs = csvParser.getModuleFromCsv(1);
        Assertions.assertEquals(8, moduleCsvs.get(0).getMaxScoreByControlQuestion());
        Assertions.assertEquals(6, moduleCsvs.get(1).getMaxScoreByExercise());
        ModuleCsv moduleCsv3 = moduleCsvs.get(2);
        Assertions.assertEquals(2, moduleCsv3.getMaxScoreByExercise());
        Assertions.assertEquals(20, moduleCsv3.getMaxScoreByHomework());
        Assertions.assertEquals(4, moduleCsv3.getMaxScoreByControlQuestion());
        List<TaskScoreCsv> taskScore = moduleCsv3.getTasks().get(0).getScores();
        Assertions.assertEquals(2, taskScore.get(0).getScore());
        Assertions.assertEquals(0, taskScore.get(taskScore.size() - 1).getScore());
        Assertions.assertEquals("Трибушко Данил", taskScore.get(13).getStudent().getFullName());
    }
}
