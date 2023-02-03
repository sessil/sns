package com.fastcampus.snsproject.model.entity;

import com.fastcampus.snsproject.model.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "\"post\"")
@Getter
@Setter
@SQLDelete(sql = "UPDATE \"post\" SET deleted_at = NOW() where id = ?")
@Where(clause = "deleted_at is NULL")
public class PostEntiity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "register_at")
    private Timestamp registerAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;


    @PrePersist
    void registedAt(){
        this.registerAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updateAt(){
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public static PostEntiity of(String title, String body, UserEntity userEntity){
        PostEntiity postEntiity = new PostEntiity();
        postEntiity.setTitle(title);
        postEntiity.setBody(body);
        postEntiity.setUser(userEntity);

        return postEntiity;
    }
}
