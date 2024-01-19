package org.example.services;

import org.example.models.csv.Module;
import org.example.models.csv.Student;

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
    List<Module> getModuleFromCsv(Integer group);

    /**
     * Получение списка студентов из csv файла
     *
     * @param group номер группы
     * @return список студентов
     */
    List<Student> getStudentsFromCsv(Integer group);
}
