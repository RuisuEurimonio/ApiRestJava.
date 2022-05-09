package com.ruisu.webpage.dao;

import com.ruisu.webpage.models.UsersModel;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UsersModel> getUsers(){
        String query = "FROM UsersModel";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUser(Long id){
        UsersModel usersModel = entityManager.find(UsersModel.class, id);
        entityManager.remove(usersModel);
    }

    @Override
    public void createUser(UsersModel usersModel){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String passHash = argon2.hash(1, 1024, 1, usersModel.getPassword());
        usersModel.setPassword(passHash);

        entityManager.merge(usersModel);
    }

    @Override
    public UsersModel loginVerify(UsersModel usersModel){
        String query = "FROM UsersModel WHERE email = :email";
        List<UsersModel> lista = entityManager.createQuery(query)
                .setParameter("email", usersModel.getEmail())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, usersModel.getPassword())) {
            return lista.get(0);
        }
        return null;
    }

}
