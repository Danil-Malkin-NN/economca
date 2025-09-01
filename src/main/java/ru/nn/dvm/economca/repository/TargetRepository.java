package ru.nn.dvm.economca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nn.dvm.economca.entity.Target;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {

    Target findByTgUserId(Long userId);

}
