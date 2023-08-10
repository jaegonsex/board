package jg.board.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public String login(String email, String rawPassword) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(email, rawPassword);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return jwtTokenProvider.generateToken(authentication);
    }

    public AccountDto signup(Signup.Request signupRequest) {
        checkDuplicateEmail(signupRequest);

        Account account = Account.builder()
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .nickname(signupRequest.getNickname())
                .role(Role.USER)
                .build();

        return AccountDto.from(accountRepository.save(account));

    }

    private void checkDuplicateEmail(Signup.Request signupRequest) {
        if (accountRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("duplicated email");
        }
    }


}
