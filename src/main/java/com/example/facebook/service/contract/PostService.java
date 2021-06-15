package com.example.facebook.service.contract;

import com.example.facebook.entity.Image;
import com.example.facebook.entity.Post;
import com.example.facebook.entity.User;
import com.example.facebook.exception.EmptyPostException;

import java.util.Set;
import java.util.Stack;

public interface PostService {

    public Post addNewPost(User userDTO, Post postDTO, Image imageDTO) throws EmptyPostException;

    public Stack<Post> getUserPost(User user);
}
