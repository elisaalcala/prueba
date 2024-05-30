package com.app.alcala.service;

import java.util.List;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Release;

public interface ReleaseService {

	List<Release> findByReleaseNotCompleted();

	List<Release> findByReleasesOpen();

	Release mapNewRelease(Release savedRelease, Employee employee);

	List<Release> findAll();

	Release findByIdRelease(long id);

	Release editRelease(long id, Release updatedRelease);

	Release save(Release release);

	Release findByNameRelease(String nameRelease);

	Boolean delete(Release release);

}
