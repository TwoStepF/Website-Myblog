package com.thisWebSite.myWebsite.repository;

import com.thisWebSite.myWebsite.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post ORDER BY id DESC", nativeQuery = true)
    Optional<List<Post>> findAllPost();

    @Query(value = "SELECT * FROM post p " +
            "WHERE p.title LIKE %:key% " +
            "ORDER BY p.id DESC", nativeQuery = true)
    Optional<List<Post>> searchPost(String key);

    @Query(value = "SELECT TIMESTAMPDIFF(hour ,post.created_on, :now) from post where post.id = :id", nativeQuery = true)
    int TimeNow(Long id, Instant now);

    @Query(value = "SELECT * FROM post ORDER BY point DESC LIMIT 10", nativeQuery = true)
    Optional<List<Post>> findAllTopPost();
}
