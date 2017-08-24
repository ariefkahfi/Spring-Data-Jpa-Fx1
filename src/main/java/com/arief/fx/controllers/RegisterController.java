package com.arief.fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.arief.fx.configuration.AbstractFxController;
import com.arief.fx.domain.Mahasiswa;
import com.arief.fx.domain.User;
import com.arief.fx.repositories.MahasiswaRepo;
import com.arief.fx.repositories.UserRepo;
import com.arief.fx.repositories.ValidationRepo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


@Component
public class RegisterController extends AbstractFxController {

	@FXML
	private TextField registerUsername,fieldNim;
	@FXML
	private PasswordField registerPassword,confirmPassword;
	@FXML
	private Button submitRegister,cancelRegister,checkNim;
	
	
	
	@Autowired
	private HomeController home;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private MahasiswaRepo mhsRepo;
	@Autowired
	private ValidationRepo validRepo;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

		
	public void register(ActionEvent ev) {
		try {
			if(registerUsername.getText().trim().equals("") 
					|| fieldNim.getText().trim().equals("")
					|| registerPassword.getText().trim().equals("") 
					|| registerUsername.getText().trim().equals("")) {
				buatDialog("Java Fx Dialog", null,"masih ada field yang kosong");
			}else if(!confirmPassword.getText().trim().equals(registerPassword.getText().trim())) {
				buatDialog("Java Fx Dialog", null,"field password harus sama");	
			}else{
				//boolean sudahAda = userRepo.exists(registerUsername.getText().trim());
				boolean validationUserName = validRepo.cariUsername(registerUsername.getText().trim());
				
				if(!validationUserName) {
					
						boolean sudahVerifikasi = mhsRepo.exists(fieldNim.getText().trim());
						int index = validRepo.nimIsExists(fieldNim.getText().trim()).size();
						
						if(sudahVerifikasi && index == 0) {
							Mahasiswa findMhs = mhsRepo.findOne(fieldNim.getText().trim());
							User newUser = new User(registerUsername.getText().trim(), 
									registerPassword.getText().trim()
									, findMhs);
								
							userRepo.save(newUser);
							buatDialog("Java Fx Dialog", null, "Register berhasil");
							clearFields();
						}else{
							if(index>=1) {
								buatDialog("Java Fx Dialog",null,"Nim ini sudah dipakai");
							}else if(!sudahVerifikasi) {
								buatDialog("Java Fx Dialog", null, "Nim ini belum diverifikasi");
							}
							clearFields();
						}
				}else{
					buatDialog("Java Fx Dialog", null, "User dengan username tersebut sudah ada\n"
							+ "Silahkan gunakan username lain");
					clearFields();
				}
			}
		}catch (DuplicateKeyException e) {
			buatDialog("Java Fx Dialog", null, "Akun ini sudah terdaftar silahkan login \n " +e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void clearFields() {
		registerUsername.setText("");
		fieldNim.setText("");
		registerPassword.setText("");
		confirmPassword.setText("");
	}
	
	public void doCheck(ActionEvent ev) {
		buatDialog("Check Dialog",context.getBean(DialogCekController.class).initNodeForView("/scene/info_mahasiswa.fxml"),null);
	}

	public void mainMenu(ActionEvent ev) {
		Stage st = (Stage)((Node)ev.getSource()).getScene().getWindow();	
		home.sceneReload(st, HomeController.class, "/scene/home.fxml");
	}
	

	private void buatDialog(String headerText,Node n,String s) {
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle(headerText);

		if(n!=null && s==null) {
			dialog.getDialogPane().setContent(n);
		}
		
		if(n==null && s!=null) {
			dialog.setContentText(s);
		}
		
		
		ButtonType ok = new ButtonType("Apply", ButtonData.APPLY);
		
		dialog.getDialogPane().getButtonTypes().setAll(ok);
		dialog.showAndWait();
	}
	
}
