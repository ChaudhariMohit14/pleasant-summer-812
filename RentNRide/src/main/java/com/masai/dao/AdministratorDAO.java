package com.masai.dao;

import java.util.List;

import com.masai.model.Administrator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class AdministratorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void addAdministrator(Administrator administrator) {
        entityManager.persist(administrator);
    }

    public Administrator getAdministratorById(Long administratorId) {
        return entityManager.find(Administrator.class, administratorId);
    }

    public List<Administrator> getAllAdministrators() {
        return entityManager.createQuery("SELECT a FROM Administrator a", Administrator.class).getResultList();
    }

    public void updateAdministrator(Administrator administrator) {
        entityManager.merge(administrator);
    }

    public void deleteAdministrator(Administrator administrator) {
        entityManager.remove(administrator);
    }
}
