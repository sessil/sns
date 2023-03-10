package com.fastcampus.snsproject.service;

import com.fastcampus.snsproject.exception.ErrorCode;
import com.fastcampus.snsproject.exception.SnsApplicationException;
import com.fastcampus.snsproject.model.Post;
import com.fastcampus.snsproject.model.entity.PostEntiity;
import com.fastcampus.snsproject.model.entity.UserEntity;
import com.fastcampus.snsproject.repository.PostEntityRepository;
import com.fastcampus.snsproject.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostEntityRepository postEntityRepository;
    private final UserEntityRepository userEntityRepository;


    @Transactional
    public void create(String title, String body, String userName){
        //User Find
        UserEntity userEntity =  userEntityRepository.findByUserName(userName).orElseThrow(() ->
                new SnsApplicationException(ErrorCode.USER_NOT_FOUNED, String.format("%s not founded", userName)));
        //post Save
        postEntityRepository.save(PostEntiity.of(title, body, userEntity));
    }
    @Transactional
    public Post modify(String title, String body, String userName, Integer postId){

        UserEntity userEntity =  userEntityRepository.findByUserName(userName).orElseThrow(() ->
                new SnsApplicationException(ErrorCode.USER_NOT_FOUNED, String.format("%s not founded", userName)));
        // post exists
        PostEntiity postEntiity = postEntityRepository.findById(postId).orElseThrow(() ->
            new SnsApplicationException(ErrorCode.POST_NOT_FOUND,String.format("%s not founded",postId)));

        //post permission
        if(postEntiity.getUser() != userEntity){
            throw new SnsApplicationException(ErrorCode.INVALID_PERMISSION,String.format("%s has no permission with %s",userName, postId));
        }
        //post Save
        postEntiity.setTitle(title);
        postEntiity.setBody(body);
        return Post.fromEntity(postEntityRepository.saveAndFlush(postEntiity));

    }

    @Transactional
    public void delete(String userName, Integer postId){

        UserEntity userEntity =  userEntityRepository.findByUserName(userName).orElseThrow(() ->
                new SnsApplicationException(ErrorCode.USER_NOT_FOUNED, String.format("%s not founded", userName)));
        // post exists
        PostEntiity postEntiity = postEntityRepository.findById(postId).orElseThrow(() ->
                new SnsApplicationException(ErrorCode.POST_NOT_FOUND,String.format("%s not founded",postId)));

        //post permission
        if(postEntiity.getUser() != userEntity){
            throw new SnsApplicationException(ErrorCode.INVALID_PERMISSION,String.format("%s has no permission with %s",userName, postId));
        }
        //post Delete
        postEntityRepository.delete(postEntiity);

    }

    @Transactional
    public Page<Post> list(Pageable pageable){
        return postEntityRepository.findAll(pageable).map(Post::fromEntity);
    }

    @Transactional
    public Page<Post> my(String userName, Pageable pageable){
        UserEntity userEntity =  userEntityRepository.findByUserName(userName).orElseThrow(() ->
                new SnsApplicationException(ErrorCode.USER_NOT_FOUNED, String.format("%s not founded", userName)));

        return postEntityRepository.findAllByUser(userEntity, pageable).map(Post::fromEntity);
    }
}
