package com.arief.fx.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.arief.fx.domain.Mahasiswa;


@Repository
public interface MahasiswaRepo extends CrudRepository<Mahasiswa, String> {
	
}
