package com.example.facebook.service.impl;

import com.example.facebook.entity.Image;
import com.example.facebook.entity.Post;
import com.example.facebook.entity.User;
import com.example.facebook.exception.EmptyPostException;
import com.example.facebook.repository.PostRepository;
import com.example.facebook.service.contract.ImageService;
import com.example.facebook.service.contract.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Stack;

@Service
public class PostServiceImpl implements PostService {

    private final ImageService imageService;
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(ImageService imageService, PostRepository postRepository) {
        this.imageService = imageService;
        this.postRepository = postRepository;
    }

    @Override
    public Post addNewPost(User userDTO, Post postDTO, Image imageDTO) throws EmptyPostException {
        if(postDTO.getPostText().isEmpty() && imageDTO.getUrl().isEmpty()) {
            throw new EmptyPostException("The post must have at least text or a photo!");
        }

        Post newPost = new Post();

        newPost.setUser(userDTO);
        newPost.setPostText(postDTO.getPostText());
        newPost.setImage(imageService.getImage(imageDTO));

        LocalDate date = LocalDate.now();
        newPost.setPostDate(date);
        newPost.setParentPost(newPost);

        postRepository.save(newPost);

        return newPost;
    }

    @Override
    public Stack<Post> getUserPost(User user) {

        return postRepository.findAllByUser(user);
    }



}
