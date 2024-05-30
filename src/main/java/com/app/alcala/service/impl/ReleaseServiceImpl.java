package com.app.alcala.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.Release;
import com.app.alcala.repositories.ReleaseRepository;
import com.app.alcala.service.ReleaseService;

@Service
public class ReleaseServiceImpl implements ReleaseService{
	@Autowired
	ReleaseRepository releaseRepository;
	
	@Override
	public List<Release> findByReleaseNotCompleted() {
		return releaseRepository.findByStatusReleaseNot("Completed");
	}
	@Override
	public List<Release> findByReleasesOpen() {
		return releaseRepository.findByStatusReleaseIn(Arrays.asList("Backlog", "In progress"));
	}

	public Release save(Release savedRelease) {
		return releaseRepository.save(savedRelease);
	}
	
	@Override
	public List<Release> findAll() {
		return releaseRepository.findAll();
	}
	
	@Override
	public Release findByIdRelease(long id) {
		return releaseRepository.findByIdRelease(id).orElseThrow();
	}

	@Override
	public Release findByNameRelease(String nameRelease) {
		return releaseRepository.findByNameRelease(nameRelease).orElseThrow();
	}
	
	@Override
	public Release mapNewRelease(Release savedRelease, Employee employee) {
		savedRelease.setEmployeeCreation(employee);
		savedRelease.setEmployeeUserCreation(employee.getUserEmployee());
		savedRelease.setStatusRelease("Start");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String formattedDate = dateFormat.format(savedRelease.getProDate());
		savedRelease.setNameRelease("R" + formattedDate);
		return save(savedRelease);
	}
	
	@Override
	public Release editRelease(long id, Release updatedRelease) {
		Release release = findByIdRelease(id);
		if (!ObjectUtils.isEmpty(updatedRelease.getInitialDate())) {
			release.setInitialDate(updatedRelease.getInitialDate());
		}
		if (!ObjectUtils.isEmpty(updatedRelease.getRequirementsDate())) {
			release.setRequirementsDate(updatedRelease.getRequirementsDate());
		}
		if (!ObjectUtils.isEmpty(updatedRelease.getPrjAssignmentDate())) {
			release.setPrjAssignmentDate(updatedRelease.getPrjAssignmentDate());
		}
		if (!ObjectUtils.isEmpty(updatedRelease.getDevelopDate())) {
			release.setDevelopDate(updatedRelease.getDevelopDate());
		}
		if (!ObjectUtils.isEmpty(updatedRelease.getTstDate())) {
			release.setTstDate(updatedRelease.getTstDate());
		}
		if (!ObjectUtils.isEmpty(updatedRelease.getUatDate())) {
			release.setUatDate(updatedRelease.getUatDate());
		}
		if (!ObjectUtils.isEmpty(updatedRelease.getProDate())) {
			release.setProDate(updatedRelease.getProDate());
		}
		return releaseRepository.save(release);
	}
	@Override
	public Boolean delete(Release release) {
		try {
			releaseRepository.delete(release);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
