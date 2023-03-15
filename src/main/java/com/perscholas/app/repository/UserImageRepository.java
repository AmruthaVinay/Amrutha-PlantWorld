package com.perscholas.app.repository;

import com.perscholas.app.model.UserImage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional(rollbackOn = Exception.class)
public interface UserImageRepository extends JpaRepository<UserImage, Integer> {

    Optional<UserImage> findByName(String name);
    Optional<UserImage> findByUrl(String url);


}
