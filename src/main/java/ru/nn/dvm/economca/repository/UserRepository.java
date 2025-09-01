package ru.nn.dvm.economca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nn.dvm.economca.entity.TgUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<TgUser, Long> {

    Optional<TgUser> findByTelegramId(Long telegramId);
}
