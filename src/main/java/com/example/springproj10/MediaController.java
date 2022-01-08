package com.example.springproj10;

import com.example.springproj10.model.Post;
import com.example.springproj10.model.PostWithReferenceId;
import com.example.springproj10.model.PostWithReferenceLink;
import com.example.springproj10.model.Reference;
import com.example.springproj10.repository.PostRepository;
import com.example.springproj10.repository.PostWithReferenceIdRepository;
import com.example.springproj10.repository.PostWithReferenceLinkRepository;
import com.example.springproj10.repository.ReferenceRepository;
import net.andreinc.mockneat.MockNeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Ref;
import java.util.List;

@RestController
public class MediaController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostWithReferenceIdRepository postWithReferenceIdRepository;

    @Autowired
    ReferenceRepository referenceRepository;

    @Autowired
    PostWithReferenceLinkRepository postWithReferenceLinkRepository;

    @PostMapping("/postwithref")
    public ResponseEntity<PostWithReferenceLink> addPostWithReference(@RequestParam String title, @RequestParam String content, @RequestParam String referenceLinkURL) {
        try {
            PostWithReferenceLink newPost = new PostWithReferenceLink(title, content, referenceLinkURL);
            PostWithReferenceLink _post = postWithReferenceLinkRepository.save(newPost);
            System.out.println(_post);
            return ResponseEntity.status(HttpStatus.CREATED).body(_post);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/postandref")
    public ResponseEntity<PostWithReferenceId> addPostAndReference(@RequestParam String title, @RequestParam String content, @RequestParam String referenceLinkURL) {
        try {
            Reference newReference = new Reference(referenceLinkURL);
            Reference _reference = referenceRepository.save(newReference);
            PostWithReferenceId newPost = new PostWithReferenceId(title, content, _reference);
            PostWithReferenceId _post = postWithReferenceIdRepository.save(newPost);
            System.out.println(_post);
            return ResponseEntity.status(HttpStatus.CREATED).body(_post);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<Post> addPost(@RequestParam String title, @RequestParam String content) {
        try {
            Post newPost = new Post(title, content);
            Post _post = postRepository.save(newPost);
            System.out.println(_post);
            return ResponseEntity.status(HttpStatus.CREATED).body(_post);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/mock")
    public String mockData(@RequestParam Integer size, @RequestParam String type) throws IOException {
        MockNeat m = MockNeat.threadLocal();

        List<String> strings = null;
        switch (type) {
            case "post":
                strings = m.fmt("#{title},#{content}")
                        .param("title", m.strings())
                        .param("content", m.celebrities())
                        .list(size)
                        .val();
                break;
            case "postwithref":
                strings = m.fmt("#{title},#{content},#{referenceLinkURL}")
                        .param("title", m.strings())
                        .param("content", m.celebrities())
                        .param("referenceLinkURL", m.urls())
                        .list(size)
                        .val();
                break;
            default:
                System.out.println("Unexpected value: " + type);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("mock_data_%s.csv", type)));
        switch (type) {
            case "post":
                writer.write("title,content");
                writer.write("\n");
                break;
            case "postwithref":
                writer.write("title,content,referenceLinkURL");
                writer.write("\n");
                break;
            default:
                System.out.println("Unexpected value: " + type);
        }
        assert strings != null;
        for (String line : strings) {
            writer.write(line);
            writer.write("\n");
        }
        writer.close();

        return String.format("Created mock_data_%s.csv", type);
    }
}