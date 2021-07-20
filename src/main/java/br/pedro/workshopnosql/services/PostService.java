package br.pedro.workshopnosql.services;

import br.pedro.workshopnosql.dto.PostDTO;
import br.pedro.workshopnosql.models.Post;
import br.pedro.workshopnosql.repositories.PostRepository;
import br.pedro.workshopnosql.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository repository;

    @Autowired
    public void setRepository(PostRepository repository) {
        this.repository = repository;
    }

    private Post getEntity(String id){
        return repository.findById(id).orElseThrow(() -> new ServiceException("Entity not found"));
    }

    public PostDTO findById (String id){
        return new PostDTO(getEntity(id));
    }

    public List<PostDTO> findByTitle(String text){
        return repository.querySearch(text) // .findByTitleContainingIgnoreCase(text)
                .stream().map(PostDTO::new)
                .collect(Collectors.toList());
    }

    private Instant convertData(String data, Instant defaultValue){
        try {
            return Instant.parse(data);
        } catch (DateTimeException e){
            return defaultValue;
        }
    }

    public List<PostDTO> fullSearch(String text, String start, String end){
        Instant startMoment = convertData(start, Instant.ofEpochMilli(0L));
        Instant endMoment = convertData(end, Instant.now());
        return repository.fullSearch(text, startMoment, endMoment)
                .stream().map(PostDTO::new)
                .collect(Collectors.toList());
    }
}
