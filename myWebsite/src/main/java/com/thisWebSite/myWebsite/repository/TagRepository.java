package com.thisWebSite.myWebsite.repository;


import com.thisWebSite.myWebsite.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "select * from Tag where tag_name = :tagname", nativeQuery = true)
    Optional<Tag> findTagByTagName(String tagname);

    @Query(value = "select * from Tag order by tag_name ASC", nativeQuery = true)
    List<Tag> findAll();
}
