package com.example.securitytask7.repositories.impl;

import com.example.securitytask7.models.Role;
import com.example.securitytask7.repositories.RoleRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
@Component
public class RoleRepoImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public RoleRepoImpl() {
    }

    @Transactional
    @Override
    public void save(Role role) {
        Role managed = entityManager.merge(role);
        entityManager.persist(managed);
    }

    @Transactional
    @Override
    public void delete(Role role) {
        Role managed = entityManager.merge(role);
        entityManager.remove(managed);
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id );
    }

    @Override
    public Role getRoleByName(String rolename) {
        try{
            Role role = entityManager.createQuery("SELECT r FROM Role r where r.name = :name", Role.class)
                    .setParameter("name", rolename)
                    .getSingleResult();
            return role;
        } catch (NoResultException ex){
            return null;
        }
    }

    @Transactional
    public Role createRoleIfNotFound(String name, long id) {
        Role role = getRoleByName(name);
        if (role == null) {
            role = new Role(id, name);
            save(role);
        }
        return role;
    }
}
