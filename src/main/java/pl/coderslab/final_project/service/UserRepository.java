package pl.coderslab.final_project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.final_project.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);
}
