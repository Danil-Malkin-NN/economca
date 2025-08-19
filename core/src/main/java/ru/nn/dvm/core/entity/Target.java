package ru.nn.dvm.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Target {

    @Id
    @GeneratedValue
    private Long id;

    private long target;

    private long residuum;

    @OneToOne
    private User user;

    public Target(long target, long residuum) {
        this.target = target;
        this.residuum = residuum;
    }

    public Target() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTarget() {
        return target;
    }

    public void setTarget(long target) {
        this.target = target;
    }

    public long getResiduum() {
        return residuum;
    }

    public void setResiduum(long residuum) {
        this.residuum = residuum;
    }
}
