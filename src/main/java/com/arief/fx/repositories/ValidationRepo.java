package com.arief.fx.repositories;

import java.util.List;

import com.arief.fx.domain.Mahasiswa;
import com.arief.fx.domain.User;

public interface ValidationRepo {
	List nimIsExists(String nim);
	public boolean cariUsername(String username);
	public User validasiUser(String username ,String pass);
}
