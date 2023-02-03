package com.fastcampus.snsproject.repository;

import com.fastcampus.snsproject.model.entity.PostEntiity;
import com.fastcampus.snsproject.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostEntityRepository extends JpaRepository<PostEntiity, Integer> {
    Page<PostEntiity> findAllByUser(UserEntity entity, Pageable pageable);
}
