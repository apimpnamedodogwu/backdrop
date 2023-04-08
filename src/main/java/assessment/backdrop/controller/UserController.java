package assessment.backdrop.controller;

import assessment.backdrop.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
//    @PutMapping
//    public ResponseEntity<?> verifyUser(@RequestParam String accountNumber, @RequestParam String accountName, @RequestParam String bankCode) {
//
//    }
}
