package eu.filip.backend.service;

import eu.filip.backend.dto.PostCreationDto;
import eu.filip.backend.dto.PostDto;
import eu.filip.backend.entity.Like;
import eu.filip.backend.entity.Post;
import eu.filip.backend.entity.PostPage;
import eu.filip.backend.entity.User;
import eu.filip.backend.repository.PostRepository;
import eu.filip.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final LikeService likeService;
    private final UserRepository userRepository;

    public Page<Post> getPosts(PostPage postPage){
        Pageable pageable = PageRequest.of(postPage.getPageNumber(), postPage.getPageSize());
        return postRepository.findAll(pageable);
    }

    public PostDto getPostForAuthenticated(Long postId, Long userId){
        Post post = postRepository.findPostById(postId).get();
        PostDto postDto = new PostDto(
                post.getId(),
                post.getCreator_id(),
                post.getTitle(),
                post.getBody(),
                post.getImage_name(),
                post.getLikes(),
                likeService.getLike(postId, userId).isStatus()
        );
        return postDto;
    }

    public Post getPost(Long postId){
        return postRepository.findPostById(postId).get();
    }

    public void updatePostLikes(Long postId, int amount){
        postRepository.changeLikeCount(postId, amount);
    }

    public Post createPost(PostCreationDto postCreationDto, Authentication authentication){

        User user = userRepository.findByUsername(authentication.getName());

        Post post = new Post();
        post.setTitle(postCreationDto.getTitle());
        post.setBody(postCreationDto.getBody());
        post.setLikes(0);
        post.setCreator_id(user.getId());
        return postRepository.save(post);
    }

    public void setPostFileName(Long postId, String fileName){
        postRepository.updateImageName(postId, fileName);
    }

}
