package ru.gerasimov.se.api.service;

import ru.gerasimov.se.entity.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

public interface ISettingsService {

    void saveMessage(EntityManager em, List<Settings> settingsList);
}
