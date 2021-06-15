package com.example.facebook.repository;

import com.example.facebook.entity.Post;
import com.example.facebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.Stack;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Stack<Post> findAllByUser(User user);
}
