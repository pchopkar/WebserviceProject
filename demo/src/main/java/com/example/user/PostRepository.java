package com.example.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//51 JPA GET - 2)create new repository with extending JpaRepository<User, Integer>
@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
