package eu.filip.backend.service;

import eu.filip.backend.entity.Like;
import eu.filip.backend.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public Like likePost(Long userId, Long postId){
        System.out.println(doesLikeExist(1L, 3L));
        return new Like();
    }

    private boolean doesLikeExist(Long userId, Long postId){
        return likeRepository.findByPost_idAndUser_id(postId, userId).isPresent();
    }
}
