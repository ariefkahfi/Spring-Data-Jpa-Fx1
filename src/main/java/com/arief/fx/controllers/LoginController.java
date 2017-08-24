package com.arief.fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.arief.fx.configuration.AbstractFxController;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

@Component
public class LoginController extends AbstractFxController {

	@FXML
	private Button bSignOut;
	/*
	 *
	 *Konten Login belum diisi
	 *
	 *
	 **/
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	
	public void doSignOut(ActionEvent ev) {
		//Platform.exit();
	}

}
