package vdm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vdm.entity.User;
import vdm.helpers.Role;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByNameAndLastName(String name, String lastName);

    List<User> findUsersByRole(Role role);

    List<User> findUsersByEmail(String email);
}
