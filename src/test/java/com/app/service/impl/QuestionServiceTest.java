package com.app.service.impl;

import com.app.dao.QuestionDAO;
import com.app.model.Question;
import com.app.service.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

    @Mock
    private QuestionDAO questionDAO;

    @InjectMocks
    private QuestionService questionService = QuestionServiceImpl.getInstance();

    private Question question = new Question();

    @Before
    public void setUp() throws Exception {
        question.setId(21L);
    }

    @Test
    public void shouldFindEntityById() {
        // given
        when(questionDAO.findById(question.getId()))
                .thenReturn(question);

        // when
        Question questionById = questionService.findById(question.getId());

        // then
        verify(questionDAO, times(1)).findById(question.getId());
        assertThat(question, is(questionById));
    }
}