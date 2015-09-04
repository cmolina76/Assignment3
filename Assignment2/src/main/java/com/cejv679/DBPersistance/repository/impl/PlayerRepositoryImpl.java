package com.cejv679.DBPersistance.repository.impl;

import com.cejv679.DBPersistance.models.NewPlayerModel;
import com.cejv679.DBPersistance.repository.PlayerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Carlos Molina.
 */
@Repository
@Transactional
public class PlayerRepositoryImpl implements PlayerRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public PlayerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    public long count() {
        return findAll().size();
    }

    public NewPlayerModel save(NewPlayerModel player) {
        Serializable id = currentSession().save(player);
        return new NewPlayerModel((Long) id,
                player.getFirstname(),
                player.getLastname(),
                player.getAge(),
                player.getCurrency(),
                player.getSalary(),
                player.getCountry(),
                player.getPosition(),
                player.getGoals(),
                player.getBookings());
    }

    public NewPlayerModel findOne(long id) {
        return (NewPlayerModel) currentSession().get(NewPlayerModel.class, id);
    }

    public NewPlayerModel findByLastName(String lastname) {
        try {
            return (NewPlayerModel) currentSession()
                    .createCriteria(NewPlayerModel.class)
                    .add(Restrictions.eq("lastname", lastname))
                    .list().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public List<NewPlayerModel> findAll() {
        return (List<NewPlayerModel>) currentSession()
                .createCriteria(NewPlayerModel.class).list();
    }


}
