package com.motolola.moj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motolola.moj.model.User;
import com.motolola.moj.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void getMyTest() throws Exception
    {
        mockMvc.perform(get("/api/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is test!"));
    }
    @Test
    public void createUserTest() throws Exception
    {
        User user = new User();
        user.setFirstName("Ayodele");
        user.setSecondName("Emmanue");
        user.setAccountNumber(2236799);

        when(userService.create(user)).thenReturn(user);

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user)))
                .andExpect(status().isOk());

    }
    @Test
    public void getOneTest() throws Exception
    {
        User user = new User();
        user.setId(3);
        user.setFirstName("John");
        user.setSecondName("Shirley");
        user.setAccountNumber(776655);

        when(userService.getOne(user.getId())).thenReturn(user);

        mockMvc.perform(get("/api/user/"+user.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllTest() throws Exception
    {
        List<User> user = new ArrayList<>();

        User user1 = new User();
        user1.setId(3);
        user1.setFirstName("John");
        user1.setSecondName("Shirley");
        user1.setAccountNumber(776655);

        User user2 = new User();
        user2.setId(4);
        user2.setFirstName("Mike");
        user2.setSecondName("Thumble");
        user2.setAccountNumber(556633);

        user.add(user1);
        user.add(user2);

        when(userService.getAll()).thenReturn(user);

        mockMvc.perform(get("/api/user")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    }

    @Test
    public void updateTest() throws Exception
    {
        User user = new User();
        user.setId(6);
        user.setFirstName("John");
        user.setSecondName("Shirley");
        user.setAccountNumber(776655);

        mockMvc.perform(put("/api/user", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(user)))
                .andExpect(status().isOk());
                //.andExpect(content().string("{\"id\":6,\"firstName\":\"John\",\"secondName\":\"Shirley\",\"accountNumber\":\"776655\"}"));

    }

    @Test
    public void deleteTest() throws Exception
    {
        User user = new User();
        user.setId(3);
        user.setFirstName("John");
        user.setSecondName("Shirley");
        user.setAccountNumber(776655);

        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/user/"+user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            System.out.println(jsonContent);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}