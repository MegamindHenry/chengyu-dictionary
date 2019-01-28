/**
 * 
 */
package com.colewe.ws1819.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
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
import com.google.gwt.user.client.ui.RadioButton;
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


}
