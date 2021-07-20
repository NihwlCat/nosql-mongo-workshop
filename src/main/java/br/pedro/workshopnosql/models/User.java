package br.pedro.workshopnosql.models;

import br.pedro.workshopnosql.dto.UserDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String email;

    // Reference to posts with lazy loading
    @DBRef(lazy = true)
    private List<Post> posts = new ArrayList<>();

    public User(){ }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(UserDTO user){
        name = user.getName();
        email = user.getEmail();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
