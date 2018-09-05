package com.app.service.impl;

import com.app.dao.TestDAO;
import com.app.model.Question;
import com.app.model.Subject;
import com.app.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceImplTest {

    @Mock
    private TestDAO testDAO;

    @InjectMocks
    private TestService testService = TestServiceImpl.getInstance();

    private com.app.model.Test test = new com.app.model.Test();
    private Question question = new Question();
    private Subject subject = new Subject();
    private Set<com.app.model.Test> testSet = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        test.setId(2L);
        question.setId(5L);
        subject.setId(4L);
    }

    public void shouldInsert() {

        //when
        testService.insert(test);

        //then
        verify(testDAO, times(1)).insert(test);
    }

    public void shouldDelete() {

        //when
        testService.delete(test);

        //then
        verify(testDAO, times(1)).delete(test);
    }

    @Test
    public void shouldFindById() {
        // given
        when(testDAO.findById(test.getId()))
                .thenReturn(test);

        // when
        com.app.model.Test foundTest = testService.findById(test.getId());

        // then
        verify(testDAO, times(1)).findById(test.getId());
        assertThat(test, is(foundTest));
    }

    @Test
    public void shouldFindAllBySubjectId() {
        //given
        when(testDAO.findAllBySubjectId(subject.getId())).thenReturn(testSet);

        // when
        Set<com.app.model.Test> foundTestSet = testService.findAllBySubjectId(subject.getId());

        // then
        verify(testDAO, times(1)).findAllBySubjectId(subject.getId());
        assertThat(testSet, is(foundTestSet));
    }

    @Test
    public void shouldFindAllByQuestionId() {
        //given
        when(testDAO.findAllByQuestionId(question.getId())).thenReturn(testSet);

        // when
        Set<com.app.model.Test> foundTestSet = testService.findAllByQuestionId(question.getId());

        // then
        verify(testDAO, times(1)).findAllByQuestionId(question.getId());
        assertThat(testSet, is(foundTestSet));
    }
}
