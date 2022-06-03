package eu.filip.backend.service;

import eu.filip.backend.entity.Post;
import eu.filip.backend.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findAllPosts(){
        //Post post = new Post(1L, 1L, "hi", "body", UUID.randomUUID(), 12);
        //List<Post> list = new ArrayList<>();
        //list.add(post);
        //return list;

        return postRepository.findAll();
    }
}
