package eu.filip.backend.repository;

import eu.filip.backend.entity.Like;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {

    @Query(nativeQuery = true, value = "select * from likes where post_id = ?1 and user_id = ?2")
    Optional<Like> findByPost_idAndUser_id(Long postId, Long userId);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update likes set status = !status where post_id = ?1 and user_id = ?2")
    void toggleLike(Long postId, Long userId);
}
