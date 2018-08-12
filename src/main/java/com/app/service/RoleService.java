package com.app.service;

import com.app.model.Role;

public interface RoleService {
    Long insert(Role role);

    void update(Role role);

    void delete(Role role);

    Role getById(Long role_id);
}
