package com.motolola.moj.service;

import com.motolola.moj.model.User;
import com.motolola.moj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repo;

    public List<User> getAll()
    {
        return repo.findAll();
    }

    public User getOne(int id)
    {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User create(User user)
    {
        return repo.save(user);
    }

    public void deleteUser(int id)
    {
        repo.deleteById(id);
    }
    public User update(User user)
    {

        return repo.findById(user.getId())
                .map(myUser -> {
                    myUser.setFirstName(user.getFirstName());
                    myUser.setSecondName(user.getSecondName());
                    return repo.save(myUser);
                })
                .orElseGet(() -> {
                    user.setId(user.getId());
                    return repo.save(user);
                });
    }
}
