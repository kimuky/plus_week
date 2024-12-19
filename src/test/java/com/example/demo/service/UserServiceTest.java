package com.example.demo.service;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void signupWithEmail() {
        // GIVEN
        UserRequestDto userRequestDto
                = new UserRequestDto("user", "test@test.com", "kim", "123");
        User user = mock(User.class);
        given(user.getPassword()).willReturn("asd123");

        // WHEN
        when(userRepository.save(any(User.class))).thenReturn(user);

        // THEN
        User result = userService.signupWithEmail(userRequestDto);
        assertEquals(result.getPassword(), user.getPassword());
    }

    @Test
    void loginUser() {
    }
}