package ru.cgmd.accounting_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cgmd.accounting_system.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByOrderByIdAsc();
}