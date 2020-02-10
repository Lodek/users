import com.wipro.bartenders.users.domain.user.User;
import com.wipro.bartenders.users.utils.PojoTestUtils;
import org.junit.Test;

public class UserTest {
    @Test
    public void validateAccessors_shouldAccessProperField() {
        PojoTestUtils.validateAccessors(User.class);
        PojoTestUtils.callToStringHashCodeAndEquals(new User());
    }
}
