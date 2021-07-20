package br.pedro.workshopnosql.controllers;

import br.pedro.workshopnosql.dto.PostDTO;
import br.pedro.workshopnosql.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    private PostService service;

    @Autowired
    public void setService(PostService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable String id){
        PostDTO post = service.findById(id);

        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/title-search")
    public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        List<PostDTO> posts = service.findByTitle(text);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text,
                                                     @RequestParam(value = "start", defaultValue = "") String start,
                                                     @RequestParam(value = "end",defaultValue = "") String end){

        List<PostDTO> posts = service.fullSearch(text,start,end);
        return ResponseEntity.ok().body(posts);
    }
}
