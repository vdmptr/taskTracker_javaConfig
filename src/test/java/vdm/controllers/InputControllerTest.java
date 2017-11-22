package vdm.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import vdm.entity.User;
import vdm.helpers.Role;
import vdm.repository.UserRepository;
import vdm.security.SecurityUser;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InputControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityUser securityUser;

    @InjectMocks
    private InputController inputController= new InputController(userRepository);

    @Before
    public void setUp() throws Exception {
        int userId = 1;
        when(securityUser.getUserId()).thenReturn(userId);
        when(user.getRole()).thenReturn(Role.ROLE_ADMIN,
                                        Role.ROLE_MANAGER,
                                        Role.ROLE_DEVELOPER);
        when(userRepository.findOne(userId))
                                        .thenReturn(user);
        when(authentication.getPrincipal()).thenReturn(securityUser);
    }


    @Test
    public void getPage() throws Exception {

       String resultPage = inputController.getPage(authentication);

       assertEquals(resultPage, "admin");

       resultPage = inputController.getPage(authentication);

       assertEquals(resultPage, "manager");

       resultPage = inputController.getPage(authentication);

       assertEquals(resultPage, "developer");
    }

}