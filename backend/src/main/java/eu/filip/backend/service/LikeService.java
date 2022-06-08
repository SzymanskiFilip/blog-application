package eu.filip.backend.service;

import eu.filip.backend.entity.Like;
import eu.filip.backend.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;

    public void like(Long postId, Long userId){
        if(doesLikeExist(postId, userId)){
            toggleLike(postId, userId);
        } else {
            Like like = new Like();
            like.setPost_id(postId);
            like.setUser_id(userId);
            like.setStatus(true);
            likeRepository.save(like);
        }
    }

    private boolean doesLikeExist(Long postId, Long userId){
        return likeRepository.findByPost_idAndUser_id(postId, userId).isPresent();
    }

    private void toggleLike(Long postId, Long userId){
        likeRepository.toggleLike(postId, userId);
    }

    public Like getLike(Long postId, Long userId){
        return likeRepository.findByPost_idAndUser_id(postId, userId).get();
    }
}
