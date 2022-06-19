package eu.filip.backend.controller;

import eu.filip.backend.dto.*;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            User user = userService.getUserByUsername(authentication.getName());
            PostDto post = postService.getPostForAuthenticated(id, user.getId());
            return ResponseEntity.ok(post);
        } else {
            log.info("USER WITHOUT DETAILS");
            return ResponseEntity.ok(postService.getPost(id));
        }
    }

    @PatchMapping("/post")
    public ResponseEntity<?> patchPost(@RequestBody UpdatePostDto dto, Authentication authentication){
        boolean status;
        if(authentication != null && authentication.isAuthenticated()){
            status = postService.updatePostDto(dto, authentication);
        } else {
            status = false;
        }
        return ResponseEntity.ok().body(status);
    }

    @PatchMapping("/post-img")
    public ResponseEntity<?> patchPostImg(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id,
                                       @RequestParam("title") String title, @RequestParam("body") String body, Authentication authentication){
        boolean status = false;
        if(authentication != null && authentication.isAuthenticated()){

            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String name = uuid += fileName;
            System.out.println("FILE NAME: " + name);

            try{
                file.transferTo(new File("/home/filip/Software Development/blog-application/blog-frontend/public/images/" + name));
                postService.setPostFileName(id, name);

                UpdatePostDto dto = new UpdatePostDto();
                dto.setId(id);
                dto.setTitle(title);
                dto.setBody(body);
                status = postService.updatePostDto(dto, authentication);
                return ResponseEntity.ok().body(status);
            } catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.ok().body(status);
    }

    @PostMapping("/like")
    public ResponseEntity<?> like(@RequestBody LikeDto likeDto, Authentication authentication){
        User user = userService.getUserByUsername(authentication.getName());
        Long postId = likeDto.getId();

        log.info("LIKING");
        likeService.like(postId, user.getId());

        return ResponseEntity.ok("liked");
    }

    @PostMapping("/check-availability")
    ResponseEntity<?> checkAvalibility(@RequestBody UsernameDto usernameDto){
        boolean doesUserExist = userService.doesUserExist(usernameDto.getUsername());
        return ResponseEntity.ok(doesUserExist);
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody RegisterDataDto registerDataDto){
        System.out.println("USER WANTS TO REGISTER - " + registerDataDto.toString());
        try{
            Thread.sleep(2000);
        } catch (Exception e){}
        if(userService.doesUserExist(registerDataDto.getUsername())){
            System.out.println("username taken");
            return ResponseEntity.badRequest().body("Username Taken");
        }
        return userService.registerUser(registerDataDto);
    }

    @PostMapping("/create-post-image")
    ResponseEntity<?> createPost(@RequestParam("file") MultipartFile file, @RequestParam("id") Long postId){
        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String name = uuid += fileName;
        System.out.println("FILE NAME: " + name);

        try{
            file.transferTo(new File("/home/filip/Software Development/blog-application/blog-frontend/public/images/" + name));
            postService.setPostFileName(postId, name);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-post-data")
    ResponseEntity<?> createPostData(@RequestBody PostCreationDto postCreationDto, Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()){
            return ResponseEntity.ok(postService.createPost(postCreationDto, authentication));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


}
