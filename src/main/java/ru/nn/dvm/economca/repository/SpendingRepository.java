package ru.nn.dvm.economca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nn.dvm.economca.entity.Spending;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Long> {
}
