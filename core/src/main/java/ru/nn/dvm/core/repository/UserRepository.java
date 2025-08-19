package ru.nn.dvm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nn.dvm.core.entity.Spending;
import ru.nn.dvm.core.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
