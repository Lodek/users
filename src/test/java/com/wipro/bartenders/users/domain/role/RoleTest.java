package com.wipro.bartenders.users.domain.role;

import com.wipro.bartenders.users.utils.PojoTestUtils;
import org.junit.Test;

public class RoleTest {
    @Test
    public void validateAccessors_shouldAccessProperField() {
        PojoTestUtils.validateAccessors(Role.class);
        PojoTestUtils.callToStringHashCodeAndEquals(new Role());
    }
}
