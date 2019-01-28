/**
 * 
 */
package com.colewe.ws1819.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;

import java.util.ArrayList;

import org.gwtbootstrap3.client.*;
import org.gwtbootstrap3.client.ui.RadioButton;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author jingwen
 *
 */
public class UIPanel extends Composite implements HasText {

	private static UIPanelUiBinder uiBinder = GWT.create(UIPanelUiBinder.class);

	interface UIPanelUiBinder extends UiBinder<Widget, UIPanel> {	
	}
	
	@UiField
	TextBox inputTextBox;
	@UiField
	RadioButton chineseOption;
	@UiField
	RadioButton pinyinOption;
	@UiField
	RadioButton englishOption;
	@UiField
	RadioButton advancedOption;
	@UiField
	CheckBox chineseCheckBox;
	@UiField
	CheckBox pinyinCheckBox;
	@UiField
	CheckBox englishCheckBox;
	@UiField
	Button searchButton;
	@UiField 
	HTML outputHTML;

	

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	  *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public UIPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		
		chineseOption.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				chineseOption.setActive(true);
				pinyinOption.setActive(false);
				englishOption.setActive(false);
				Window.alert("Chinese chosen.");				
			}
			
		});
		
		pinyinOption.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				chineseOption.setActive(false);
				pinyinOption.setActive(true);
				englishOption.setActive(false);
				Window.alert("Pinyin chosen.");
			}
			
		});
		
		englishOption.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				chineseOption.setActive(false);
				pinyinOption.setActive(false);
				englishOption.setActive(true);
				Window.alert("English chosen.");
				
			}
			
		});
		
		advancedOption.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				advancedOption.setActive(true);
				
			}
			
		});
		
		searchButton.setText("search");
		chineseCheckBox.setText("Chinese");
		chineseCheckBox.setValue(false);
		pinyinCheckBox.setText("Pinyin");
		pinyinCheckBox.setValue(false);
		englishCheckBox.setText("English");
		englishCheckBox.setValue(false);
		
		outputHTML.setHTML("<h3>Your output will go here<h3>");
		
		searchButton.addStyleName("btn btn-dark");
		inputTextBox.addStyleName("form-control");
	}



	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateResult(ArrayList<String[]> results) {
		
		outputHTML.setHTML("<h5>Hello</h5>");
	}


}
