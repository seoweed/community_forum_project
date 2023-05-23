package com.mysite.sbb.user;

import com.mysite.sbb.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String password, String email) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setEmail(email);
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 비밀번호는 암호화 해서 보관해야 한다.
        // 하지만 이렇게 객체를 생성하는 것보다는 유지보수를 위해 빈을 만드는 것이 더 유용하다.
        // SecurityConfig 에 빈을 생성하고 코드를 변경한다.
        siteUser.setPassword(passwordEncoder.encode(password));
        SiteUser user = userRepository.save(siteUser);
        return user;
    }
}
