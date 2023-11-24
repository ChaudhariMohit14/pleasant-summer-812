package com.masai.controller;

import java.util.List;

import com.masai.model.Administrator;
import com.masai.service.AdministratorService;

public class AdministratorController {

    private AdministratorService administratorService;

    public AdministratorController() {
        this.administratorService = new AdministratorService();
    }

    public void addAdministrator(Administrator administrator) {
        administratorService.addAdministrator(administrator);
    }

    public Administrator getAdministratorById(Long administratorId) {
        return administratorService.getAdministratorById(administratorId);
    }

    public List<Administrator> getAllAdministrators() {
        return administratorService.getAllAdministrators();
    }

    public void updateAdministrator(Administrator administrator) {
        administratorService.updateAdministrator(administrator);
    }

    public void deleteAdministrator(Administrator administrator) {
        administratorService.deleteAdministrator(administrator);
    }
}
