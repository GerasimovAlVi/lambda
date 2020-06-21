package ru.gerasimov.se.api.repository;

import ru.gerasimov.se.entity.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

public interface ISettingsRepository {

    void saveUser(EntityManager em, List<Settings> settingsList);
}
