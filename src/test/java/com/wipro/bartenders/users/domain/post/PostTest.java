package com.wipro.bartenders.users.domain.post;

import com.wipro.bartenders.users.utils.PojoTestUtils;
import org.junit.Test;

public class PostTest {
    @Test
    public void validateAccessors_shouldAccessProperField() {
        PojoTestUtils.validateAccessors(Post.class);
        PojoTestUtils.callToStringHashCodeAndEquals(new Post());
    }
}
