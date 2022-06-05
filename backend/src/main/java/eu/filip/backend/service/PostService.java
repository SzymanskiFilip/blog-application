package eu.filip.backend.service;

import eu.filip.backend.entity.Post;
import eu.filip.backend.entity.PostPage;
import eu.filip.backend.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    public Page<Post> getPosts(PostPage postPage){
        Pageable pageable = PageRequest.of(postPage.getPageNumber(), postPage.getPageSize());
        return postRepository.findAll(pageable);
    }
}
