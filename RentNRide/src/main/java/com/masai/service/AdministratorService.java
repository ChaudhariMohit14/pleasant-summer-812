package com.masai.service;

import java.util.List;

import com.masai.dao.AdministratorDAO;
import com.masai.model.Administrator;

public class AdministratorService {

    private AdministratorDAO administratorDAO;

    public AdministratorService() {
        this.administratorDAO = new AdministratorDAO();
    }

    public void addAdministrator(Administrator administrator) {
        administratorDAO.addAdministrator(administrator);
    }

    public Administrator getAdministratorById(Long administratorId) {
        return administratorDAO.getAdministratorById(administratorId);
    }

    public List<Administrator> getAllAdministrators() {
        return administratorDAO.getAllAdministrators();
    }

    public void updateAdministrator(Administrator administrator) {
        administratorDAO.updateAdministrator(administrator);
    }

    public void deleteAdministrator(Administrator administrator) {
        administratorDAO.deleteAdministrator(administrator);
    }
}
