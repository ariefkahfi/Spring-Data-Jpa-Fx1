package com.arief.fx.controllers;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.arief.fx.configuration.AbstractFxController;
import com.arief.fx.domain.Mahasiswa;
import com.arief.fx.repositories.MahasiswaRepo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class DialogCekController extends AbstractFxController {

	@FXML
	private Button submitCek;
	@FXML
	private TextField fieldNim;
	@FXML
	private Label labelNama;
	
	

	@Autowired
	private MahasiswaRepo repo;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		refreshFields();
	}

	
	
	public void submitCek(ActionEvent ev) {
		try {
			if(!fieldNim.getText().trim().equals("")) {
				String hasilNim = fieldNim.getText().trim();
				
				boolean ada = repo.exists(hasilNim);
				if(ada) {
					Mahasiswa m = repo.findOne(hasilNim);
					fieldNim.setText(m.getNim());
					labelNama.setText(m.getNama());
					alertSubmitCek("Data Mahasiswa sudah terverifikasi \n anda bisa mendaftar untuk akun ini", AlertType.CONFIRMATION);
				}else {
					refreshFields();
					alertSubmitCek("Data Mahasiswa tidak ada di database kami", AlertType.WARNING);
				}
				
			}
		}catch (DuplicateKeyException ex) {
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void refreshFields() {
		fieldNim.setText("");
		labelNama.setText("");
	}
	
	private void alertSubmitCek(String s , AlertType a) {
		Alert alert = new Alert(a);
		alert.setTitle("Java Fx Dialog");
		alert.setContentText(s);
		
		alert.show();
	
	}
	
}
