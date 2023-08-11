package jg.board.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/auth/signup")
    public ResponseEntity<Signup.Response> signup(@RequestBody Signup.Request request) {
        AccountDto accountDto = accountService.signup(request);

        return ResponseEntity.ok().body(Signup.Response.from(accountDto));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Login.Response> login(@RequestBody Login.Request request) {

        String token = accountService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new Login.Response(token));
    }
}
