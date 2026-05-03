package ru.nn.dvm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nn.dvm.core.entity.Target;

/**
 * Доступ к {@link Target} по идентификатору пользователя Telegram.
 */
@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {

    Target findByTgUser_TelegramId(Long userId);

}
