package ru.gerasimov.se.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gerasimov.se.api.repository.ISettingsRepository;
import ru.gerasimov.se.api.service.ISettingsService;
import ru.gerasimov.se.entity.settings.Settings;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SettingsService implements ISettingsService {

    @Autowired
    private ISettingsRepository iSettingsRepository;

    @Override
    public void saveMessage(EntityManager em, List<Settings> settingsList) {
        em.getTransaction().begin();
        iSettingsRepository.saveUser(em, settingsList);
        em.getTransaction().commit();
    }
}
