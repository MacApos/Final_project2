package pl.coderslab.final_project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.final_project.domain.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByEmail(String email);

//    @Query(" \"select count(*) from LoginClass login where login.emailid=:email and login.password=:password\";")
//    long count();
}
