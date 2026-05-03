package ru.nn.dvm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nn.dvm.core.entity.TgUser;

import java.util.Optional;

/**
 * Доступ к сущностям {@link ru.nn.dvm.core.entity.TgUser} по идентификатору Telegram.
 */
@Repository
public interface UserRepository extends JpaRepository<TgUser, Long> {

    Optional<TgUser> findByTelegramId(Long telegramId);
}
