package ru.nn.dvm.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Target {

    @Id
    @GeneratedValue
    private Long id;

    private long target;

    private long residuum;

    private long dayleResiduum;

    @OneToOne
    private TgUser tgUser;

    public Target(long target, long residuum, TgUser tgUser, long dayleResiduum) {
        this.target = target;
        this.residuum = residuum;
        this.tgUser = tgUser;
        this.dayleResiduum = dayleResiduum;
    }

    public Target() {

    }

}
