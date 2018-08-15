package com.app.dao;

import com.app.model.Role;

public interface RoleDAO {

    Long insert(Role role);

    void update(Role role);

    void delete(Role role);

    Role findById(Long role_id);
}
