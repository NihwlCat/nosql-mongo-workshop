package br.pedro.workshopnosql.controllers;

import br.pedro.workshopnosql.dto.PostDTO;
import br.pedro.workshopnosql.dto.UserDTO;
import br.pedro.workshopnosql.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> users = service.findAll();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<PostDTO>> findPosts(@PathVariable String id){
        return ResponseEntity.ok().body(service.findPosts(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        UserDTO user = service.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO user){
        UserDTO u = service.insert(user);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri()).body(u);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO user){
        UserDTO u = service.update(id,user);
        return ResponseEntity.ok().body(u);
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
