package ru.cgmd.accounting_system.repos;

import ru.cgmd.accounting_system.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByOrderByIdAsc();
}