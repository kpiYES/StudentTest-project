package com.app.service.impl;

import com.app.dao.QuestionDAO;
import com.app.model.Question;
import com.app.model.Subject;
import com.app.service.QuestionService;
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
public class QuestionServiceImplTest {

    @Mock
    private QuestionDAO questionDAO;

    @InjectMocks
    private QuestionService questionService = QuestionServiceImpl.getInstance();
    private Question question = new Question();
    private Subject subject = new Subject();
    private com.app.model.Test test = new com.app.model.Test();
    private Set<Question> questionSet = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        question.setId(21L);
        subject.setId(4L);
        test.setId(7L);
        questionSet.add(new Question());
        questionSet.add(new Question());
    }

    @Test
    public void shouldInsert() {

        //when
        questionService.insert(question);

        //then
        verify(questionDAO, times(1)).insert(question);
    }

    @Test
    public void shouldDelete() {

        //when
        questionService.delete(question);

        //then
        verify(questionDAO, times(1)).delete(question);
    }


    @Test
    public void shouldFindById() {
        // given
        when(questionDAO.findById(question.getId()))
                .thenReturn(question);

        // when
        Question foundQuestion = questionService.findById(question.getId());

        // then
        verify(questionDAO, times(1)).findById(question.getId());
        assertThat(question, is(foundQuestion));
    }

    @Test
    public void shouldFindAllBySubjectIdWithPagination() {
        // given
        when(questionDAO.findAllBySubjectIdWithPagination(subject.getId(), 2, 1)).thenReturn(questionSet);

        // when
        Set<Question> foundQuestionSet = questionService.findAllBySubjectIdWithPagination(subject.getId(), 2, 1);

        // then
        verify(questionDAO, times(1)).findAllBySubjectIdWithPagination(subject.getId(), 2, 1);
        assertThat(questionSet, is(foundQuestionSet));
    }

    @Test
    public void shouldFindAllBySubjectId() {
        //given
        when(questionDAO.findAllBySubjectId(subject.getId())).thenReturn(questionSet);

        // when
        Set<Question> foundQuestionSet = questionService.findAllBySubjectId(subject.getId());

        // then
        verify(questionDAO, times(1)).findAllBySubjectId(subject.getId());
        assertThat(questionSet, is(foundQuestionSet));
    }

    @Test
    public void shouldFindAllByTestId() {

        //given
        when(questionDAO.findAllByTestId(test.getId())).thenReturn(questionSet);

        //when
        Set<Question> foundQuestionSet = questionService.findAllByTestId(test.getId());

        //then
        verify(questionDAO, times(1)).findAllByTestId(test.getId());
        assertThat(questionSet, is(foundQuestionSet));
    }
}
