package eu.filip.backend.repository;

import eu.filip.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Optional<Post> findPostById(Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update posts set likes = likes + ?2 where id = ?1")
    void changeLikeCount(Long postId, int amount);
}
