package eu.filip.backend.controller;

import eu.filip.backend.dto.LikeDto;
import eu.filip.backend.dto.PostDto;
import eu.filip.backend.entity.Like;
import eu.filip.backend.entity.Post;
import eu.filip.backend.entity.PostPage;
import eu.filip.backend.entity.User;
import eu.filip.backend.repository.LikeRepository;
import eu.filip.backend.repository.PostRepository;
import eu.filip.backend.repository.UserRepository;
import eu.filip.backend.service.LikeService;
import eu.filip.backend.service.PostService;
import eu.filip.backend.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class Controller {

    private final PostService postService;
    private final LikeService likeService;
    private final UserService userService;

    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> posts(PostPage postPage){
        return new ResponseEntity<>(postService.getPosts(postPage), HttpStatus.ACCEPTED);
    }

    @GetMapping("/test")
    public String test(){
        return "h1";
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> post(@PathVariable("id") Long id, Authentication authentication) throws Exception{
        if(authentication != null && authentication.isAuthenticated()){
            log.info("USER WITH DETAILS");
            User user = userService.getUserByUsername(authentication.getName());
            PostDto post = postService.getPostForAuthenticated(id, user.getId());
            return ResponseEntity.ok(post);
        }
        log.info("USER WITHOUT DETAILS");
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping("/like")
    public ResponseEntity<?> like(@RequestBody LikeDto likeDto, Authentication authentication){

        Long userId = userService.getUserByUsername(authentication.getName()).getId();
        Long postId = likeDto.getId();

        likeService.like(postId, userId);

        return ResponseEntity.accepted().build();
    }
}
