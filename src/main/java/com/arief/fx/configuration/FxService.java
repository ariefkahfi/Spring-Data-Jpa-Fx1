package com.arief.fx.configuration;

import org.springframework.context.ApplicationContextAware;

import javafx.fxml.Initializable;
import javafx.scene.Node;

public interface FxService extends Initializable, ApplicationContextAware {
	public Node initNodeForView(String fxml);
}
