package com.ruisu.webpage.controllers;

import com.ruisu.webpage.dao.UserDao;
import com.ruisu.webpage.models.UsersModel;
import com.ruisu.webpage.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtils jwtUtils;

    private Boolean autentification(String token){
        String user = jwtUtils.getKey(token);
        return user != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<UsersModel> getUsers(@RequestHeader(value = "Autentification") String token){
        if(!autentification(token)){return null;};
        return userDao.getUsers();
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id,@RequestHeader(value = "Autentification") String token){
        if(!autentification(token)){return;};
        userDao.deleteUser(id);
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void createUser(@RequestBody UsersModel userModel){
        userDao.createUser(userModel);
    }
}
