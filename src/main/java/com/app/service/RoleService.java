package com.app.service;

import com.app.model.Role;

import java.util.Set;

public interface RoleService extends Service {

    Long insert(Role role);

    void update(Role role);

    void delete(Role role);

    Role findById(Long id);

    Set<Role> findAll();

    Role findByName(String name);
}
