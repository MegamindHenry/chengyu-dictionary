package com.colewe.ws1819.client;

import java.util.ArrayList;

import com.colewe.ws1819.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.colewe.ws1819.client.DictionaryService;
import com.colewe.ws1819.client.DictionaryServiceAsync;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class semesterproject implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";
  
  private final DictionaryServiceAsync dictionarySvc = GWT.create(DictionaryService.class);


  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  UIPanel uiPanel = new UIPanel();
	  RootPanel.get().add(uiPanel);
	  
	  ClickHandler searchCH = new ClickHandler() {
			public void onClick(ClickEvent e) {
				
				String target = uiPanel.inputTextBox.getText();
				int mode = uiPanel.mode;
				
				AsyncCallback<ArrayList<String[]>> callback = new AsyncCallback<ArrayList<String[]>>() {

					@Override
					public void onFailure(
							Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Failure");
						
					}

					@Override
					public void onSuccess(
							ArrayList<String[]> results) {
						// TODO Auto-generated method stub
//						uiPanel.outputHTML.updateOutput(result, target, highlight, reverse);
						uiPanel.updateResult(results);
					}
				    
				  };
				
				
				  dictionarySvc.search(target, mode, callback);
				
			}
		};
	  
	  
	  uiPanel.searchButton.addClickHandler(searchCH);
  }
}
