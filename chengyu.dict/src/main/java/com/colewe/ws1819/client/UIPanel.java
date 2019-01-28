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
	Button searchButton;
	@UiField 
	HTML outputHTML;
	
	int mode = 1;

	

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
		
		chineseOption.setActive(true);
		
		chineseOption.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				chineseOption.setActive(true);
				pinyinOption.setActive(false);
				englishOption.setActive(false);
				mode = 1;
			}
			
		});
		
		pinyinOption.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				chineseOption.setActive(false);
				pinyinOption.setActive(true);
				englishOption.setActive(false);
				mode = 2;
			}
			
		});
		
		englishOption.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				chineseOption.setActive(false);
				pinyinOption.setActive(false);
				englishOption.setActive(true);
				mode = 3;
			}
			
		});
		
		advancedOption.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				advancedOption.setActive(true);
				
			}
			
		});
		
		searchButton.setText("search");
		
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
		OutputTableBuilder otb = new OutputTableBuilder(results);
		outputHTML.setHTML(otb.toHTML());
	}
	
	private class OutputTableBuilder{
		
		StringBuilder sb;
		
		public OutputTableBuilder() {
		}
		
		public OutputTableBuilder(ArrayList<String[]> results) {
			sb = new StringBuilder("");
			this.appendTableOpen(sb, "table");
			this.appendTHeadOpen(sb, "thead-dark");
			this.appendTrOpen(sb, "even");
			this.appendTHeader(sb, "ID");
			this.appendTHeader(sb, "Abbr");
			this.appendTHeader(sb, "Chinese");
			this.appendTHeader(sb, "English Literal");
			this.appendTHeader(sb, "English Figurative");
			this.appendTHeader(sb, "Pinyin");
			this.appendTHeader(sb, "Example");
			this.appendTHeader(sb, "Example Translation");
			this.appendTHeader(sb, "Origin");
			this.appendTHeader(sb, "Origin Translation");
			this.appendTHeader(sb, "Frequency");
			this.appendTrClose(sb);
			this.appendTHeadClose(sb);
			this.appendTBodyOpen(sb);
			
			for(String[] result: results) {
				this.appendTrOpen(sb);
				this.appendTEntries(sb, result);
				this.appendTrClose(sb);
			}
			this.appendTBodyClose(sb);
			this.appendTableClose(sb);
		}
		
		private String toHTML() {
			return this.sb.toString();
		}
		
		//may be used for highlighting?
		private void appendHTMLTag(StringBuilder sb, String tag, String elem) {
			sb.append("<").append(tag).append(">"); // open tag
			sb.append(elem);
			sb.append("</").append(tag).append(">"); // closing tag
		}
		
		private void appendHTMLTag(StringBuilder sb, String tag, String elem, String cssClass) {
			sb.append("<").append(tag).append(" class=\"").append(cssClass).append("\">"); // open tag
			sb.append(elem);
			sb.append("</").append(tag).append(">"); // closing tag
		}
		
		private void appendTableOpen(StringBuilder sb) {
			sb.append("<table>");
		}
		
		private void appendTableOpen(StringBuilder sb, String cssClass) {
			sb.append("<table class=\"" + cssClass + "\">");
		}
		
		private void appendTableClose(StringBuilder sb) {
			sb.append("</table>");
		}
		
		private void appendTrOpen(StringBuilder sb) {
			sb.append("<tr>");
		}
		
		private void appendTrOpen(StringBuilder sb, String cssClass) {
			sb.append("<tr class=\"" + cssClass + "\">");
		}
		
		private void appendTrClose(StringBuilder sb) {
			sb.append("</tr>");
		}
		private void appendTHeadOpen(StringBuilder sb) {
			sb.append("<thead>");
		}
		
		private void appendTHeadOpen(StringBuilder sb, String cssClass) {
			sb.append("<thead class=\"" + cssClass + "\">");
		}
		
		private void appendTHeadClose(StringBuilder sb) {
			sb.append("</thead>");
		}
		
		private void appendTBodyOpen(StringBuilder sb) {
			sb.append("<tbody>");
		}
		
		private void appendTBodyClose(StringBuilder sb) {
			sb.append("</tbody>");
		}
		
		private void appendTdOpen(StringBuilder sb) {
			sb.append("<td>");
		}
		
		private void appendTdOpen(StringBuilder sb, String cssClass) {
			sb.append("<td class=\"" + cssClass + "\">");
		}
		
		private void appendTdClose(StringBuilder sb) {
			sb.append("</td>");
		}
		
		private void appendThOpen(StringBuilder sb) {
			sb.append("<th>");
		}
		
		private void appendThOpen(StringBuilder sb, String cssClass) {
			sb.append("<th class=\"" + cssClass + "\">");
		}
		
		private void appendThClose(StringBuilder sb) {
			sb.append("</th>");
		}
		
		private void appendTHeader(StringBuilder sb, String head) {
			this.appendHTMLTag(sb,"th",head);
		}
		
		private void appendTHeader(StringBuilder sb, String head, String cssClass) {
			this.appendHTMLTag(sb,"th",head, cssClass);
		}
		
		private void appendTEntries(StringBuilder sb, String[] result) {
			for(String data: result) {
				this.appendHTMLTag(sb, "td", data);
			}
		}
	}


}
