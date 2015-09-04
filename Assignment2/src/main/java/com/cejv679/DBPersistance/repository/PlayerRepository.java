package com.cejv679.DBPersistance.repository;

import com.cejv679.DBPersistance.models.NewPlayerModel;
import java.util.List;

/**
 * Created by Carlos Molina.
 */
public interface PlayerRepository {

    long count();

    NewPlayerModel save(NewPlayerModel player);

    NewPlayerModel findOne(long id);

    NewPlayerModel findByLastName(String lastname);

    List<NewPlayerModel> findAll();
}
