package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    ProjectRepository projectRepository;

    @Mock
    ProjectMapper projectMapper;

    @InjectMocks
    ProjectServiceImpl projectServiceImpl;

    @Test
    void getByProjectCode_Test(){ // Mocks are the objects; stubs are the behaviour of the method.

        // GIVEN
        when(projectRepository.findByProjectCode(anyString())).thenReturn(new Project()); // Stubbing
        when(projectMapper.convertToDto(any(Project.class))).thenReturn(new ProjectDTO()); // Stubbing

        // WHEN
        // I'm calling the method that I want to test
        ProjectDTO projectDTO = projectServiceImpl.getByProjectCode(anyString());

        // THEN
        // I want to check the order of these 2 Mocks
        InOrder inOrder = inOrder(projectRepository,projectMapper);
        // I'm providing in which order these 2 Mocks
        inOrder.verify(projectRepository).findByProjectCode(anyString());
        inOrder.verify(projectMapper).convertToDto(any(Project.class));

        assertNotNull(projectDTO);

    }

    @Test
    void getByProjectCode_ExceptionTest(){

        when(projectRepository.findByProjectCode("")).thenThrow(new NoSuchElementException("Project Not Found"));

        Throwable throwable = assertThrows(NoSuchElementException.class, () -> projectServiceImpl.getByProjectCode(""));

        verify(projectRepository).findByProjectCode("");

        verify(projectMapper, never()).convertToDto(any(Project.class));

        assertEquals("Project Not Found", throwable.getMessage());

    }


}