package com.app.alcala.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Release;
import com.app.alcala.repositories.ReleaseRepository;

@ExtendWith(MockitoExtension.class)
public class ReleaseServiceImplTest {

    @Mock
    private ReleaseRepository releaseRepository;

    @InjectMocks
    private ReleaseServiceImpl releaseService;

    @Test
    void testFindByReleaseNotCompleted() {
        List<Release> releases = Arrays.asList(new Release(), new Release());

        when(releaseRepository.findByStatusReleaseNot("Completed")).thenReturn(releases);

        List<Release> result = releaseService.findByReleaseNotCompleted();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(releases));
    }

    @Test
    void testFindByReleasesOpen() {
        List<Release> releases = Arrays.asList(new Release(), new Release());

        when(releaseRepository.findByStatusReleaseIn(Arrays.asList("Backlog", "In progress"))).thenReturn(releases);

        List<Release> result = releaseService.findByReleasesOpen();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(releases));
    }

    @Test
    void testSave() {
        Release release = new Release();

        when(releaseRepository.save(release)).thenReturn(release);

        Release result = releaseService.save(release);

        assertEquals(release, result);
        verify(releaseRepository, times(1)).save(release);
    }

    @Test
    void testFindAll() {
        List<Release> releases = Arrays.asList(new Release(), new Release());

        when(releaseRepository.findAll()).thenReturn(releases);

        List<Release> result = releaseService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.containsAll(releases));
    }

    @Test
    void testFindByIdRelease() {
        Release release = new Release();
        release.setIdRelease(1L);

        when(releaseRepository.findByIdRelease(1L)).thenReturn(Optional.of(release));

        Release result = releaseService.findByIdRelease(1L);

        assertEquals(release, result);
    }

    @Test
    void testFindByNameRelease() {
        Release release = new Release();
        release.setNameRelease("Release 1");

        when(releaseRepository.findByNameRelease("Release 1")).thenReturn(Optional.of(release));

        Release result = releaseService.findByNameRelease("Release 1");

        assertEquals(release, result);
    }

    @Test
    void testMapNewRelease() {
        Release release = new Release();
        Employee employee = new Employee();
        employee.setUserEmployee("JohnDoe");
        Timestamp proDate = new Timestamp(System.currentTimeMillis());
        release.setProDate(proDate);

        when(releaseRepository.save(release)).thenReturn(release);

        Release result = releaseService.mapNewRelease(release, employee);

        assertEquals(employee, result.getEmployeeCreation());
        assertEquals(employee.getUserEmployee(), result.getEmployeeUserCreation());
        assertEquals("Start", result.getStatusRelease());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String formattedDate = dateFormat.format(proDate);
        assertEquals("R" + formattedDate, result.getNameRelease());
        verify(releaseRepository, times(1)).save(release);
    }

    @Test
    void testEditRelease() {
        Release release = new Release();
        release.setIdRelease(1L);
        release.setInitialDate(new Timestamp(System.currentTimeMillis()));

        Release updatedRelease = new Release();
        updatedRelease.setInitialDate(new Timestamp(0));
        updatedRelease.setRequirementsDate(new Timestamp(System.currentTimeMillis() - 1000000));
        updatedRelease.setPrjAssignmentDate(new Timestamp(System.currentTimeMillis() - 2000000));
        updatedRelease.setDevelopDate(new Timestamp(System.currentTimeMillis() - 3000000));
        updatedRelease.setTstDate(new Timestamp(System.currentTimeMillis() - 4000000));
        updatedRelease.setUatDate(new Timestamp(System.currentTimeMillis() - 5000000));
        updatedRelease.setProDate(new Timestamp(System.currentTimeMillis() - 6000000));

        when(releaseRepository.findByIdRelease(1L)).thenReturn(Optional.of(release));
        when(releaseRepository.save(release)).thenReturn(release);

        Release result = releaseService.editRelease(1L, updatedRelease);

        assertEquals(updatedRelease.getInitialDate(), result.getInitialDate());
        assertEquals(updatedRelease.getRequirementsDate(), result.getRequirementsDate());
        assertEquals(updatedRelease.getPrjAssignmentDate(), result.getPrjAssignmentDate());
        assertEquals(updatedRelease.getDevelopDate(), result.getDevelopDate());
        assertEquals(updatedRelease.getTstDate(), result.getTstDate());
        assertEquals(updatedRelease.getUatDate(), result.getUatDate());
        assertEquals(updatedRelease.getProDate(), result.getProDate());
        verify(releaseRepository, times(1)).save(release);
    }

    @Test
    void testDelete() {
        Release release = new Release();

        assertTrue(releaseService.delete(release));
        verify(releaseRepository, times(1)).delete(release);
    }
}
