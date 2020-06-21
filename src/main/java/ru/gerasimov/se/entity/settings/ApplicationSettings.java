package ru.gerasimov.se.entity.settings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("APPLICATION")
public class ApplicationSettings extends Settings {

    @Column
    private String system;

    @Column
    private String memory;

    public ApplicationSettings(String system, String memory) {
        this.system = system;
        this.memory = memory;
    }
}
