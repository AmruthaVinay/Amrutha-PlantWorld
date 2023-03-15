package com.perscholas.app.repository;

import com.perscholas.app.model.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthGroupRepoI extends JpaRepository<AuthGroup,Integer> {

    List<AuthGroup> findByEmail(String email);

}
