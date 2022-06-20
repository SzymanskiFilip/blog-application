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

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update posts set image_name = ?2 where id = ?1")
    void updateImageName(Long postId, String fileName);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update posts set title = ?1, body = ?2 where id = ?3")
    void updatePostWithoutImage(String title, String body, Long id);

    @Query(nativeQuery = true, value = "select * from posts where creator_id = ?1 and id = ?2")
    Optional<Post> findPostByCreator_idAndPostId(Long creator_id, Long id);

    @Query(nativeQuery = true, value="select * from posts where creator_id = ?1")
    Optional<List<Post>> findPostsByCreator_id(Long id);
}
