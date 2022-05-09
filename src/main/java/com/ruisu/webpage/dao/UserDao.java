package com.ruisu.webpage.dao;

import com.ruisu.webpage.models.UsersModel;
import java.util.List;

public interface UserDao {

    public List<UsersModel> getUsers();

    void deleteUser(Long id);

    void createUser(UsersModel usersModel);

    UsersModel loginVerify(UsersModel usersModel);

}
