package org.example.services;

import org.example.models.csv.ModuleCsv;
import org.example.models.csv.StudentCsv;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 18.01.2024
 * <p>
 * Интерфейс парсинга csv файла
 */
public interface CsvParser {
    /**
     * Получение списка модулей из csv файла
     *
     * @param group номер группы
     * @return список модулей
     */
    List<ModuleCsv> getModuleFromCsv(Integer group);

    /**
     * Получение списка студентов
     *
     * @return список студентов
     */
    List<StudentCsv> getStudents();

}
