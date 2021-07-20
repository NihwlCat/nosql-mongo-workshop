package br.pedro.workshopnosql.utils;

import br.pedro.workshopnosql.models.Author;
import br.pedro.workshopnosql.models.Comment;
import br.pedro.workshopnosql.models.Post;
import br.pedro.workshopnosql.models.User;
import br.pedro.workshopnosql.repositories.PostRepository;
import br.pedro.workshopnosql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@Profile("test")
public class TestConfig {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public void set(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostConstruct
    public void init(){
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null,"Vou viajar para São Paulo. Abraços!", "Partiu viagem", Instant.parse("2021-02-13T11:15:01Z"), new Author(maria));
        Post post2 = new Post(null,"Acordei feliz hoje!", "Bom dia", Instant.parse("2021-02-14T10:05:49Z"), new Author(maria));

        Comment c1 = new Comment(Instant.parse("2021-02-13T14:30:01Z"),"Boa viagem mano!", new Author(alex));
        Comment c2 = new Comment(Instant.parse("2021-02-13T15:38:05Z"),"Aproveite", new Author(bob));
        Comment c3 = new Comment(Instant.parse("2021-02-14T12:34:26Z"),"Tenha um ótimo dia!", new Author(alex));

        post1.getComments().addAll(Arrays.asList(c1, c2));
        post2.getComments().addAll(Collections.singletonList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
