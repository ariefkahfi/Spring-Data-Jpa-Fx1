package com.arief.fx.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name="t_mhs")
public class Mahasiswa {

	
	@Id
	@Column(name="nim")
	private String nim;
	
	@Column(name="nama")
	private String nama;
	

	public Mahasiswa(String nim, String nama) {
		this.nim = nim;
		this.nama = nama;
	}

	

	public Mahasiswa() {
	}

	public String getNim() {
		return nim;
	}

	public void setNim(String nim) {
		this.nim = nim;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}



	@Override
	public String toString() {
		return "Mahasiswa [nim=" + nim + ", nama=" + nama + "]";
	}

	
	
	
}
