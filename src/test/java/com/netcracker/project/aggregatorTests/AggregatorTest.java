package com.netcracker.project.aggregatorTests;

import com.netcracker.project.aggregator.Aggregator;
import com.netcracker.project.entity.Filter;
import com.netcracker.project.entity.Role;
import com.netcracker.project.entity.User;
import com.netcracker.project.entity.enums.FilterOperator;
import com.netcracker.project.entity.enums.RoleName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class AggregatorTest {

    private Aggregator<User> aggregator = new Aggregator<>();
    private List<RoleName> rolesToFind = new ArrayList<>();
    private List<Role> roles1 = new ArrayList<>();
    private List<Role> roles2 = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Filter[] filters1;
    private Filter[] filters2;

    @Before
    public void init() {
        rolesToFind.add(RoleName.ROLE_MENTOR);

        roles1.add(new Role(RoleName.ROLE_MENTOR));
        roles2.add(new Role(RoleName.ROLE_ADMIN));
        roles1.add(new Role(RoleName.ROLE_USER));
        roles2.add(new Role(RoleName.ROLE_USER));

        this.filters1 = new Filter[] {
                new Filter("john", "username", FilterOperator.EQUALS)
        };
        this.filters2 = new Filter[] {
                new Filter("john", "username", FilterOperator.EQUALS),
                new Filter(rolesToFind, "roles.*.name", FilterOperator.IN)
        };

        users.add(new User(null, "john", null, roles1, null, null));
        users.add(new User(null, "alex", null, roles2, null, null));
        users.add(new User(null, "Slon", null, roles1, null, null));
        users.add(new User(null, "john", null, roles2, null, null));
        users.add(new User(null, "Swan", null, roles1, null, null));
    }

    @Test
    public void filterUsersByNameTest() {
        assertEquals(2, aggregator.filter(users, filters1).size());
    }

    @Test
    public void filteUsersByRoleNameTest() {
        assertEquals(1, aggregator.filter(users, filters2).size());
    }
}
