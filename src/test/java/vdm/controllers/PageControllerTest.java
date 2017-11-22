package vdm.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import vdm.entity.User;
import vdm.service.UserService;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PageControllerTest {

    @InjectMocks
    private PageController pageController = new PageController();

    @Mock
    private HttpSession session;

    @Test
    public void getCommentOfTask() throws Exception {
        int taskId = 1;

        String resultView = pageController.getCommentOfTask(taskId, session);

        assertEquals(resultView, "comment");

        verify(session).setAttribute("taskId", 1);
    }

}