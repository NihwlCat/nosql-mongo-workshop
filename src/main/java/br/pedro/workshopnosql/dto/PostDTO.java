package br.pedro.workshopnosql.dto;

import br.pedro.workshopnosql.models.Author;
import br.pedro.workshopnosql.models.Comment;
import br.pedro.workshopnosql.models.Post;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PostDTO {
    private String id;
    private String title;
    private String body;
    private Instant moment;

    private Author author;

    private final List<Comment> comments = new ArrayList<>();

    public PostDTO(Post post){
        id = post.getId();
        title = post.getTitle();
        body = post.getBody();
        moment = post.getMoment();
        author = post.getAuthor();
        comments.addAll(post.getComments());
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
