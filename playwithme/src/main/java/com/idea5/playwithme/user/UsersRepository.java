package com.idea5.playwithme.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsersRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(Users user) {
        em.persist(user);
        return user.getId();
    }

    public Users findOne(Long id) {
        return em.find(Users.class, id);
    }

    public List<Users> findAll() {
        return em.createQuery("select u from Users u", Users.class)
                .getResultList();
    }

    public List<Users> findByName(String name) {
        return em.createQuery("select u from Users u where u.name = :name", Users.class)
                .setParameter("name", name)
                .getResultList();
    }

    public void deleteAll() {
        em.clear();
    }
}
