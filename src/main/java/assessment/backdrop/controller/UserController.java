package assessment.backdrop.controller;

import assessment.backdrop.service.user.GraphQLService;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final GraphQLService graphQLService;

    @GetMapping("")
    public ResponseEntity<ExecutionResult> verifyUser(@RequestBody String query) {
        try {
            ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
            return new ResponseEntity<>(executionResult, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("An error occurred in method verifyUser : {}", e.getMessage());
            throw new RuntimeException(new Error(e.getMessage()));
        }

    }
}
