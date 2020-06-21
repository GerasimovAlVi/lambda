package ru.gerasimov.se.entity.settings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.gerasimov.se.entity.AbstractEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_settings")
@DiscriminatorColumn(name = "xtype", discriminatorType = DiscriminatorType.STRING)
public class Settings extends AbstractEntity {

    @Column(insertable = false, updatable = false)
    private String xtype;
}
