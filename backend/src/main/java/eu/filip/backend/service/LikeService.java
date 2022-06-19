package eu.filip.backend.service;

import eu.filip.backend.entity.Like;
import eu.filip.backend.repository.LikeRepository;
import eu.filip.backend.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public void like(Long postId, Long userId){
        if(doesLikeExist(postId, userId)){
            Like like = likeRepository.findByPost_idAndUser_id(postId, userId).get();
            if(like.isStatus()){
                postRepository.changeLikeCount(postId, -1);
            } else if(!like.isStatus()){
                postRepository.changeLikeCount(postId, 1);
            }
            toggleLike(postId, userId);
        } else {
            Like like = new Like();
            like.setPost_id(postId);
            like.setUser_id(userId);
            like.setStatus(true);
            likeRepository.save(like);
            postRepository.changeLikeCount(postId, 1);
        }
    }

    public boolean doesLikeExist(Long postId, Long userId){
        return likeRepository.findByPost_idAndUser_id(postId, userId).isPresent();
    }

    private void toggleLike(Long postId, Long userId){
        likeRepository.toggleLike(postId, userId);
    }

    public Like getLike(Long postId, Long userId){
        return likeRepository.findByPost_idAndUser_id(postId, userId).get();
    }
}
