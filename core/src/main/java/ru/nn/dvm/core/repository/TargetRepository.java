package ru.nn.dvm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nn.dvm.core.entity.Target;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {

    Target findByTgUser_TelegramId(Long userId);

}
