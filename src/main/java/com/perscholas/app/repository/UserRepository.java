package com.perscholas.app.repository;
import com.perscholas.app.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserRegistration,String> {

    Optional<UserRegistration> findByEmailAllIgnoreCase(String email);

    Optional<UserRegistration> findByEmail(String email);
}
