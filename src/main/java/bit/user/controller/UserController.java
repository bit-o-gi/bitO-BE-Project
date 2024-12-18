package bit.user.controller;

import bit.user.dto.UserCreateRequest;
import bit.user.dto.UserResponse;
import bit.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController implements UserControllerDoc{

    private final UserService userService;

    @GetMapping("/email/{email}")
    public UserResponse getUserByEmail(@PathVariable String email) {
        return UserResponse.from(userService.getByEmail(email));
    }
}
