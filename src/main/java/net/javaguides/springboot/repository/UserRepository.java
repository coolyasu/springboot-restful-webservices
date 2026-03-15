package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository //dont have to use

//@Transactional//bydefault to all methods

/*

provides declarative transaction management, ensuring that a series of database
operations are executed as a single, atomic unit of work. This means all
operations within the annotated method either succeed (commit) or
fail completely (rollback), maintaining data consistency and integrity.

 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
