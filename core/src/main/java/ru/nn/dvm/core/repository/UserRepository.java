package ru.nn.dvm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nn.dvm.core.entity.TgUser;

@Repository
public interface UserRepository extends JpaRepository<TgUser, Long> {
}
