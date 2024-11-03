package bit.couple.testFixtures.dto;

import bit.couple.dto.CoupleRequest;
import bit.user.entity.UserEntity;
import java.lang.reflect.Field;
import java.util.List;

public class CoupleRequestFixtures {

  public static void of(List<UserEntity> users) throws Exception {
//        CoupleRequest
//        CoupleRequest request = new CoupleRequest();
    Class<?> clazz = CoupleRequest.class;
    Field usersField = clazz.getDeclaredField("users");

    usersField.setAccessible(true);
//        usersField.set(request, users);
//        return request;
  }
}
