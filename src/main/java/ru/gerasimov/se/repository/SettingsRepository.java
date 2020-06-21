package ru.gerasimov.se.repository;

import org.springframework.stereotype.Repository;
import ru.gerasimov.se.api.repository.ISettingsRepository;
import ru.gerasimov.se.entity.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SettingsRepository implements ISettingsRepository {

    @Override
    public void saveUser(EntityManager em, List<Settings> settingsList) {
        for (Settings setting : settingsList) {
            em.persist(setting);
        }
    }
}
