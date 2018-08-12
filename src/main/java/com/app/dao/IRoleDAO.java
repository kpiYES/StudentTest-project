package com.app.dao;

import com.app.model.Role;

public interface IRoleDAO {

    Long insert(Role role);

    void update(Role role);

    void delete(Role role);

    Role getById(Long role_id);

}
