package br.pedro.workshopnosql.services;

import br.pedro.workshopnosql.dto.PostDTO;
import br.pedro.workshopnosql.dto.UserDTO;
import br.pedro.workshopnosql.models.User;
import br.pedro.workshopnosql.repositories.UserRepository;
import br.pedro.workshopnosql.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService{

    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository){
        this.repository = repository;
    }

    public List<UserDTO> findAll(){
        return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO findById (String id){
        return new UserDTO(getEntityById(id));
    }

    public UserDTO insert(UserDTO user){
        User u = repository.insert(new User(user));
        return new UserDTO(u);
    }

    private User getEntityById(String id){
        return repository.findById(id).orElseThrow(() -> new ServiceException("User not found"));
    }

    public UserDTO update(String id, UserDTO user){
        User u = getEntityById(id);
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        repository.save(u);
        return new UserDTO(u);
    }

    public void delete(String id){
        getEntityById(id);
        repository.deleteById(id);
    }

    public List<PostDTO> findPosts(String id){
        return getEntityById(id).getPosts().stream().map(PostDTO::new).collect(Collectors.toList());
    }
}
