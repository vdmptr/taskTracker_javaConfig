package vdm.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import vdm.entity.User;
import vdm.repository.UserRepository;
import vdm.controllers.helpsOfControllers.FormUser;
import vdm.service.UserService;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController =
                                                 new RegistrationController();
    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private Model model;

    @Mock
    private FormUser formUser;

    @Mock
    private BindingResult bindingResult;

    @Test
    public void account() throws Exception {
        String resultView = registrationController.register(model);

        assertEquals(resultView, "registration");

        verify(model).addAttribute(eq("formUser"),
                                   any(FormUser.class));
    }

    @Test
    public void email() throws Exception {

        List<User> userList = new ArrayList<>();
        userList.add(user);
        List<User> users = new ArrayList<>();

        when(userRepository.findUsersByEmail(anyString())).thenReturn(userList)
                                                          .thenReturn(userList)
                                                          .thenReturn(users);
        when(userService.addFormUser(formUser))
                                        .thenThrow(new MockitoException("ok"));
        when(bindingResult.hasErrors()).thenReturn(true,
                                                   false);
        String resultView = registrationController.email(formUser,
                                                         bindingResult,
                                                         model);
        assertEquals(resultView, "registration");

        resultView = registrationController.email(formUser,
                                                  bindingResult,
                                                  model);
        assertEquals(resultView, "login");

        try {
           registrationController.email(formUser,
                                                      bindingResult,
                                                      model);
        }catch (MockitoException e){
            assertEquals(e.getMessage(), "ok");
        }

    }

    @Test
    public void goodEmail() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(user);
        List<User> users = new ArrayList<>();
        when(userRepository.findUsersByEmail(anyString())).thenReturn(userList)
                                                          .thenReturn(users);

        String resultView = registrationController.goodEmail("testEmail");
        assertEquals(resultView, "login");

        try {
            registrationController.goodEmail("testEmail");
        }catch (UsernameNotFoundException e){
            assertEquals(e.getMessage(), "testEmail");
        }

    }

}