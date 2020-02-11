package com.wipro.bartenders.users.domain.user;

import com.wipro.bartenders.users.utils.PojoTestUtils;
import org.junit.Test;

public class UserTest {
    @Test
    public void validateAccessors_shouldAccessProperField() {
        PojoTestUtils.validateAccessors(User.class);
        PojoTestUtils.callToStringHashCodeAndEquals(new User());
    }
}
