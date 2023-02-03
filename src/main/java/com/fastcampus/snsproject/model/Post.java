package com.fastcampus.snsproject.model;

import com.fastcampus.snsproject.model.entity.PostEntiity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;
@Getter
@AllArgsConstructor
public class Post {
    private Integer id;

    private String title;

    private String body;

    private User user;

    private Timestamp registerAt;

    private Timestamp updatedAt;

    private Timestamp deletedAt;

    public static Post fromEntity(PostEntiity entity){
        return new Post(
                entity.getId(),
                entity.getTitle(),
                entity.getBody(),
                User.fromEntity(entity.getUser()),
                entity.getRegisterAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt()
        );
    }
}
