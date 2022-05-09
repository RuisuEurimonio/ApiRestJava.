package com.ruisu.webpage.controllers;

import com.ruisu.webpage.dao.UserDao;
import com.ruisu.webpage.models.UsersModel;
import com.ruisu.webpage.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtils jwtUtils;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String loginVerify(@RequestBody UsersModel usersModel){
        UsersModel usuarioLogueado = userDao.loginVerify(usersModel);
        if (usuarioLogueado != null) {
            String tokenJwt = jwtUtils.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getEmail());
            return tokenJwt;
        }
        return "FAIL";

    }

}
