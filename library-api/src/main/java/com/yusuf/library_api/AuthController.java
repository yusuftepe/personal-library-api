package com.yusuf.library_api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
        private final JwtUtil jwtUtil;
            private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
                        this.userRepository = userRepository;
                                this.jwtUtil = jwtUtil;
                                    }

                                        @PostMapping("/register")
                                            public String register(@RequestBody User user) {
                                                    user.setPassword(encoder.encode(user.getPassword()));
                                                            userRepository.save(user);
                                                                    return "Kayıt başarılı";
                                                                        }

                                                                            @PostMapping("/login")
                                                                                public String login(@RequestBody User loginRequest) {
                                                                                        User user = userRepository.findByUsername(loginRequest.getUsername())
                                                                                                        .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

                                                                                                                if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
                                                                                                                            throw new RuntimeException("Şifre yanlış");
                                                                                                                                    }

                                                                                                                                            return jwtUtil.generateToken(user.getUsername());
                                                                                                                                                }
                                                                                                                                                }