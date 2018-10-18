package com.motolola.moj.repository;

import com.motolola.moj.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Override
    List<User> findAll();

    @Override
    User getOne(Integer integer);

    User save(User user);

    @Override
    void delete(User user);
}
