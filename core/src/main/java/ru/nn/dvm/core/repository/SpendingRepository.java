package ru.nn.dvm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nn.dvm.core.entity.Spending;

/**
 * Персистенция записей о тратах {@link Spending}.
 */
@Repository
public interface SpendingRepository extends JpaRepository<Spending, Long> {
}
