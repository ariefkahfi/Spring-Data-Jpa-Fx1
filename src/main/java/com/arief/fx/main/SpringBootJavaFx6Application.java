package com.arief.fx.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.arief.fx.configuration.AbstractFxController;
import com.arief.fx.controllers.HomeController;
import com.arief.fx.domain.Mahasiswa;
import com.arief.fx.repositories.MahasiswaRepo;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
@EnableJpaRepositories("com.arief.fx.repositories")
@EntityScan("com.arief.fx.domain")
@ComponentScan("com.arief.fx")
public class SpringBootJavaFx6Application extends Application{

	
	private ApplicationContext context;
	
	private static String a[];
	
	public static void main(String[] args) {
		a = args;
		launch(args);
	}

	
	
	@Override
	public void init() throws Exception {
		super.init();
		context  = SpringApplication.run(SpringBootJavaFx6Application.class, a);
	}



	@Override
	public void stop() throws Exception {
		super.stop();
	}



	@Override
	public void start(Stage primaryStage) throws Exception {
		AbstractFxController fx = context.getBean(HomeController.class);	
		
		Parent p = (Parent)fx.initNodeForView("/scene/home.fxml");

		
		
		primaryStage.setScene(new Scene(p));
		primaryStage.setTitle("Spring Data Jpa Fx");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
