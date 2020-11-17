package com.netcracker.project.utilsTests;

import com.netcracker.project.entity.Cart;
import com.netcracker.project.entity.Role;
import com.netcracker.project.entity.Training;
import com.netcracker.project.entity.User;
import com.netcracker.project.entity.enums.RoleName;
import com.netcracker.project.util.AggregatorUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class AggregatorUtilsTest {

    private Cart cart = new Cart();
    private User user = new User(null, null, "pswrd",
                              List.of(new Role(RoleName.ROLE_USER), new Role(RoleName.ROLE_MENTOR)), null, cart);
    private  Training training = new Training("Some training",
            "Some awesome description", 1000, user);

    @Before
    public void init() {
        this.user.getTrainings().add(training);
        cart.setUser(user);
    }

    @Test
    public void getClassFieldsPropertyValueTest() {
        assertEquals(cart.toString(),
                AggregatorUtils.getPropertyValue("cart", user)
                        .get(0).toString());
    }

    @Test
    public void getMultiplePropertyValuesTest() {
        assertEquals("pswrd",
                AggregatorUtils.getPropertyValue("trainings.*.user.password", user)
                        .get(0).toString());
    }
}
