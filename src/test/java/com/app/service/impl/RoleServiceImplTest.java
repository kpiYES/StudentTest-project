package com.app.service.impl;


import com.app.dao.RoleDAO;
import com.app.model.Role;
import com.app.service.RoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

    @Mock
    private RoleDAO roleDAO;

    @InjectMocks
    private RoleService roleService = RoleServiceImpl.getInstance();

    private Set<Role> roleSet = new HashSet<>();
    private Role role = new Role();

    @Before
    public void setUp() throws Exception {
        roleSet.add(new Role());
        roleSet.add(new Role());
        role.setName("student");
    }

    @Test
    public void findAll() {
        //given
        when(roleDAO.findAll()).thenReturn(roleSet);

        // when
        Set<Role> foundQuestionSet = roleService.findAll();

        // then
        verify(roleDAO, times(1)).findAll();
        assertThat(roleSet, is(foundQuestionSet));
    }

    @Test
    public void findByName() {
        //given
        when(roleDAO.findByName(role.getName())).thenReturn(role);

        // when
        Role foundRole = roleService.findByName(role.getName());

        // then
        verify(roleDAO, times(1)).findByName(role.getName());
        assertThat(role, is(foundRole));
    }
}
