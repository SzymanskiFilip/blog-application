package eu.filip.backend.repository;

import eu.filip.backend.entity.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {

    @Query(nativeQuery = true, value = "select * from likes where post_id = ?1 && user_id = ?2")
    Optional<Like> findByPost_idAndUser_id(Long postId, Long userId);
}
