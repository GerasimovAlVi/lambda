package ru.gerasimov.se.entity.settings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("DEVELOPER")
public class DeveloperSettings extends Settings {

    @Column
    private String fio;

    @Column
    private String email;

    public DeveloperSettings(String fio, String email) {
        this.fio = fio;
        this.email = email;
    }
}
