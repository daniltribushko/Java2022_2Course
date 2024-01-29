package org.example.services.imp;

import org.example.models.dao.GroupFromVkDao;
import org.example.models.dao.imp.GroupFromVkDaoImp;
import org.example.models.entities.GroupFromVk;
import org.example.services.GroupFromVkDbService;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 25.01.2024
 *
 * Реализация сервиса по работе с сущностями сообществ вк в бд
 */
public class GroupFromVkDbServiceImp implements GroupFromVkDbService {
    private final GroupFromVkDao dao;

    public GroupFromVkDbServiceImp(){
        dao = new GroupFromVkDaoImp();
    }

    @Override
    public void save(Session session, GroupFromVk group) {
        GroupFromVk newGroup = findByName(session, group.getName())
                .orElse(null);
        if (newGroup != null){
            System.out.println("!!!Ошибка!!! Студент " + group.getName() + " уже сохранен");
        } else {
            dao.save(session, group);
        }
    }

    @Override
    public void update(Session session, GroupFromVk group) {
        GroupFromVk newGroup = findByName(session, group.getName())
                .orElse(null);
        if (newGroup == null){
            System.out.println(getErrorMessage(group));
        } else {
            dao.update(session, group);
        }

    }

    @Override
    public void delete(Session session, GroupFromVk group) {
        GroupFromVk newGroup = findByName(session, group.getName())
                .orElse(null);
        if (newGroup == null){
            System.out.println(getErrorMessage(group));
        } else {
            dao.delete(session, group);
        }
    }

    private String getErrorMessage(GroupFromVk group){
        return "!!!Ошибка!!! Студент " + group.getName() + " не найден";
    }
    @Override
    public Optional<GroupFromVk> findById(Session session, Integer id) {
        return dao.findById(session, id);
    }

    @Override
    public List<GroupFromVk> findAll(Session session) {
        return dao.findAll(session);
    }

    @Override
    public Optional<GroupFromVk> findByName(Session session, String name) {
        return dao.findByName(session, name);
    }
}
