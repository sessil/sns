package com.fastcampus.snsproject.fixture;

import com.fastcampus.snsproject.model.entity.PostEntiity;
import com.fastcampus.snsproject.model.entity.UserEntity;

public class PostEntityFixture {

    public static PostEntiity get(String usename, Integer postId, Integer userId){
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setUserName(usename);

        PostEntiity result = new PostEntiity();
        result.setUser(user);
        result.setId(postId);

        return result;
    }
}
