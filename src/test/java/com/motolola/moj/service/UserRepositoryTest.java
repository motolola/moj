package com.motolola.moj.service;

import com.motolola.moj.controller.UserController;
import com.motolola.moj.model.User;
import com.motolola.moj.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    // write test cases here
    @Before
    public void setUp() throws Exception {
        User alex = new User("Alex", "Mings", 556774);
        User michael = new User("Michael", "Spector", 9908);
        User john = new User("John", "Matt", 44567);
        userRepository.save(alex);
        userRepository.save(michael);
        userRepository.save(john);
    }

    @Test
    public void getAll() {

        List<User> users = userRepository.findAll();
        assertTrue(users.size() == 3);
    }

    @Test
    public void getOne() {
        User alex = new User("Alex", "Mings", 556774);

        entityManager.persist(alex);
        entityManager.flush();
        // when
        User found = userRepository.getOne(alex.getId());
        assertTrue(alex.getFirstName().equals(found.getFirstName()));
    }

    @Test
    public void create() {
        User alex = new User("Alex", "Mings", 556774);
        User created = userRepository.save(alex);
        User found = userRepository.findById(created.getId()).get();

        assertTrue(created.getFirstName().equals(found.getFirstName()));
    }

    @Test
    public void deleteUser() {

        User alex = new User("Alex", "Mings", 556774);

        entityManager.persist(alex);
        entityManager.flush();
        int initialSize = userRepository.findAll().size();

        userRepository.deleteById(alex.getId());
        int finalSize = userRepository.findAll().size();

        assertTrue((initialSize - finalSize) >= 1);
    }

   /* @Test
    public void update() {
        //create user
        User alex = new User("Alex", "Mings", 556774);

        entityManager.persist(alex);
        entityManager.flush();
        System.out.println("+++++++++++++++++");
        System.out.println("+++++++++++++++++");
        System.out.println("+++++++++++++++++");
        System.out.println("+++++++++++++++++");
        System.out.println(alex.getId());
        System.out.println(alex.getFirstName());

        alex.setFirstName("Alexander");
        userRepository.save(alex);
        //assertTrue();

    }*/
}