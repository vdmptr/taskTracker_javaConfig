package vdm.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import vdm.entity.User;

import vdm.helpers.Role;
import vdm.repository.UserRepository;
import vdm.controllers.helpsOfControllers.FormUser;
import vdm.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService = new UserService();

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private FormUser formUser;

    @Before
    public void setUp() throws Exception {

        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findUsersByRole(Role.ROLE_DEVELOPER))
                                                    .thenReturn(userList);
        when(user.getUserId()).thenReturn(1);
        when(user.getRole()).thenReturn(Role.ROLE_DEVELOPER);
        when(user.getName()).thenReturn("testName");
        when(user.getLastName()).thenReturn("testLastName");
    }

    @Test
    public void getAllDeveloper() throws Exception {

        List<UserDTO> userDTOList = userService.getAllDeveloper();

        assertEquals(userDTOList.get(0).getUserId(), 1);
    }

    @Test
    public void addUser() throws Exception {
        when(userRepository.saveAndFlush(user)).thenReturn(user);

        UserDTO userDTO = userService.addUser(user);

        assertEquals(userDTO.getUserId(), 1);
    }

    @Test
    public void addFormUser() throws Exception {
        when(formUser.getName()).thenReturn("testName");
        when(formUser.getLastName()).thenReturn("testLastName");
        when(formUser.getEmail()).thenReturn("testEmail");
        when(formUser.getPassword()).thenReturn("testPassword");
        when(formUser.getRole()).thenReturn(Role.ROLE_DEVELOPER);
        when(formUser.isEnabled()).thenReturn(true);

        when(userRepository.saveAndFlush(any(User.class)) )
                .thenAnswer((Answer<User>) invocation -> {
                                 Object[] args = invocation.getArguments();
                                 return (User) args[0];
                                 });

        User persistenceUser = userService.addFormUser(formUser);

        assertEquals(persistenceUser.getEmail(),"testEmail");
    }

}