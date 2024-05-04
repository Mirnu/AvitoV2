package ru.mirn.avitov2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirn.avitov2.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
