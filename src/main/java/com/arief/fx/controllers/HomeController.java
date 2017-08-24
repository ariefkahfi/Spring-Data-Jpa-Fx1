package com.arief.fx.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@Component
public class HomeController extends AbstractFxController {

	@FXML
	private Button bLogin;
	@FXML
	private Hyperlink linkRegister;
	@FXML
	private TextField fieldUsername;
	@FXML
	private PasswordField fieldPassword;
	
	
	@Autowired
	private MahasiswaRepo repo;
	@Autowired
	private UserRepo loginRepo;
	@Autowired
	private ValidationRepo validRepo;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		User terdaftar = validRepo.validasiUser("arief_kahfi", "123");
		if(terdaftar!=null) {
			System.out.println(terdaftar.toString());
			System.out.println(terdaftar.getUsername());
			System.out.println(terdaftar.getPassword());
		}
	}
	
	
	/*private List testAda(String nim) {
		return valid.nimIsExists(nim);
	}*/
	
	public void sceneReload(Stage st,Class<? extends AbstractFxController> c , String fxml) {
		Parent p = (Parent)((AbstractFxController)context.getBean(c)).initNodeForView(fxml);
		
		st.setScene(new Scene(p));
		st.show();
	}
	
	public void doLogin(ActionEvent ev) {
		try {
				//User terdaftar = loginRepo.(fieldNama.getText().trim());
				
			   User terdaftar = validRepo.validasiUser(fieldUsername.getText().trim(), fieldPassword.getText().trim());
				
			   if(terdaftar!=null) {
					String user = terdaftar.getUsername();
					String pass = terdaftar.getPassword();
					
					if(fieldUsername.getText().trim().equals(user) && fieldPassword.getText().trim().equals(pass)) {
						loginAlert();
						clearFields();
						Stage st = (Stage)((Node)ev.getSource()).getScene().getWindow();
						st.centerOnScreen();
						sceneReload(st, LoginController.class, "/scene/login.fxml");
					}else {
						buatAlert("invalid username or password", AlertType.ERROR);
					}
			   }
			
		}catch(EmptyResultDataAccessException dao){
			buatAlert("Akun belum ada silahkan register terlebih dahulu", AlertType.ERROR);
		}catch (Exception e) {
			buatAlert("Error occurs \n: " + e.getMessage(), AlertType.ERROR);
			
		}
	}
	
	private void clearFields() {
		fieldUsername.setText("");
		fieldPassword.setText("");
	}
	
	private void loginAlert() {
		ButtonType ok = new ButtonType("Next", ButtonData.APPLY)
	    ;Alert alert = new Alert(AlertType.CONFIRMATION,"Login Success",ok);
		
	    alert.show();
		clearFields();
	}
	
	private void buatAlert(String contentText,AlertType a) {
		Alert alert = new Alert(a);
		alert.setTitle("Java Fx Dialog");
		
		
		alert.setContentText(contentText);
		
		alert.show();
		
	}
	
	public void doRegister(ActionEvent ev) {
		Stage st = (Stage)((Node)ev.getSource()).getScene().getWindow();
		sceneReload(st, RegisterController.class, "/scene/register.fxml");
	}
	
}
