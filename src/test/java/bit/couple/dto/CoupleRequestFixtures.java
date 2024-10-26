package bit.couple.dto;

import bit.user.entity.UserEntity;
import java.lang.reflect.Field;
import java.util.List;

public class CoupleRequestFixtures {
    public static CoupleRequest of(List<UserEntity> users) throws Exception {
        CoupleRequest request = new CoupleRequest();
        Class<?> clazz = CoupleRequest.class;
        Field usersField = clazz.getDeclaredField("users");

        usersField.setAccessible(true);
        usersField.set(request, users);
        return request;
    }
}
