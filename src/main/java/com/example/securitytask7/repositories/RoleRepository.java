package com.example.securitytask7.repositories;

import com.example.securitytask7.models.Role;

public interface RoleRepository {
    void save(Role role);
    void delete(Role role);
    Role getById(Long id);
    Role getRoleByName(String rolename);
    Role createRoleIfNotFound(String name, long id);
}
