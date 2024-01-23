package org.example.services.imp;

import org.example.models.dao.ModuleDao;
import org.example.models.dao.imp.ModuleDaoImp;
import org.example.models.entities.Module;
import org.example.services.ModuleDbService;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 22.01.2024
 *
 * Реализация сервиса по работе с сущностями модулеей
 */
public class ModuleDbServiceImp implements ModuleDbService {
    private final ModuleDao dao;

    public ModuleDbServiceImp(){
        dao = new ModuleDaoImp();
    }
    @Override
    public void save(Session session, Module module) {
        Module isModuleSave = dao.findByName(session, module.getName()).orElse(null);
        if (isModuleSave != null){
            System.out.println("!!!Ошибка!!! Модуль " + module.getName() + " уже сохранен");
        } else {
            dao.save(session, module);
        }
    }

    @Override
    public void update(Session session, Module module) {
        Module isModuleSave = findById(session, module.getId()).orElse(null);
        if (isModuleSave != null){
            dao.update(session, module);
        } else {
            System.out.println("!!!Ошибка!!! Модуль " + module.getName() + " не найден");
        }
    }

    @Override
    public void delete(Session session, Module module) {
        Module isModuleSave = findById(session, module.getId()).orElse(null);
        if (isModuleSave != null){
            dao.delete(session, module);
        } else {
            System.out.println("!!!Ошибка!!! Модуль " + module.getName() + " не найден");
        }
    }

    @Override
    public Optional<Module> findById(Session session, Integer id) {
        return dao.findById(session, id);
    }

    @Override
    public List<Module> findAll(Session session) {
        return dao.findAll(session);
    }

    @Override
    public Optional<Module> findByName(Session session, String name) {
        return dao.findByName(session, name);
    }
}
