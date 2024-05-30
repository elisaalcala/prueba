package com.app.alcala.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.alcala.entities.Release;

public interface ReleaseRepository extends JpaRepository<Release, Long>{
	
	List<Release> findByStatusRelease(String statusRelease);

	List<Release> findByStatusReleaseNot(String string);

	List<Release> findByStatusReleaseIn(List<String> asList);
	
	Optional<Release> findByIdRelease(Long idRelease);

	Optional<Release> findByNameRelease(String nameRelease);

}
