package com.app.service.impl;

import com.app.dao.PassedTestDAO;
import com.app.dao.connection.TransactionManager;
import com.app.model.PassedQuestion;
import com.app.model.PassedTest;
import com.app.service.PassedQuestionService;
import com.app.service.PassedTestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TransactionManager.class)
public class PassedTestServiceTest {

    @Mock
    private PassedTestDAO passedTestDAO;

    @Mock
    private PassedQuestionService passedQuestionService;

    @InjectMocks
    private PassedTestService passedTestService = PassedTestServiceImpl.getInstance();

    private PassedTest passedTest;
    private PassedQuestion passedQuestion1;
    private PassedQuestion passedQuestion2;

    @Before
    public void setUp() throws Exception {
        passedTest = new PassedTest();
        passedTest.setId(42L);
        passedQuestion1 = new PassedQuestion();
        passedQuestion1.setId(33L);
        passedQuestion2 = new PassedQuestion();
        passedQuestion2.setId(25L);
        passedTest.setPassedQuestionSet(new HashSet<>(asList(passedQuestion1, passedQuestion2)));
    }

    @Test
    public void shouldInsertWithPassedQuestions() {
        // given
        PowerMockito.mockStatic(TransactionManager.class);

        when(passedTestDAO.insert(passedTest))
                .thenReturn(passedTest.getId());
        when(passedQuestionService.insertAll(passedTest.getPassedQuestionSet()))
                .thenReturn(asList(passedQuestion1.getId(), passedQuestion2.getId()));

        // when
        passedTestService.insertWithPassedQuestions(passedTest);

        // then
        verify(passedTestDAO, times(1)).insert(passedTest);
        verify(passedQuestionService, times(1)).insertAll(passedTest.getPassedQuestionSet());
        assertThat(passedQuestion1.getPassedTest(), is(passedTest));
        assertThat(passedQuestion2.getPassedTest(), is(passedTest));
    }
}