/**
 * 
 */
package com.colewe.ws1819.client;

import com.colewe.ws1819.shared.Entry;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;

import java.util.ArrayList;

import org.gwtbootstrap3.client.*;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.PanelGroup;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.extras.select.client.ui.MultipleSelect;

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
	Button advancedOption;
	@UiField
	MultipleSelect filter;
	@UiField
	Button searchButton;
	@UiField 
	HTML outputHTML;
	@UiField
	Button tagButton;
	
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
		
		chineseOption.setActive(true); // search in Chinese is preselected as default
		
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
				// do something (activate advanced search)
			
			}
			
		});
		
		searchButton.setText("search");
		searchButton.addStyleName("btn btn-dark");
		
		tagButton.setText("tag");
		tagButton.addStyleName("btn btn-dark");
		
		filter.setVisible(true);
		
		outputHTML.setHTML("<h3>Your output will go here<h3>");
		
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
	
	public void updateResult(ArrayList<Entry> results) {
		OutputTableBuilder otb = new OutputTableBuilder(results);
		outputHTML.setHTML(otb.toHTML());
	}
	
	private class OutputTableBuilder{
		
		StringBuilder sb;
		
		public OutputTableBuilder() {
		}
		
		public OutputTableBuilder(ArrayList<Entry> results) {
			sb = new StringBuilder("");
			
			this.appendHTMLTagOpen(sb, "table");
			this.appendHTMLTagOpen(sb, "thead");
			this.appendHTMLTagOpen(sb, "tr");
			
			this.appendHTMLTag(sb, "th", "Chinese");
			this.appendHTMLTag(sb, "th", "Pinyin");
			this.appendHTMLTag(sb, "th", "English Literal");
			this.appendHTMLTag(sb, "th", "English Figurative");
			this.appendHTMLTag(sb, "th", "Example");
			this.appendHTMLTag(sb, "th", "Example Translation");
			this.appendHTMLTag(sb, "th", "Origin");
			this.appendHTMLTag(sb, "th", "Origin Translation");
			this.appendHTMLTag(sb, "th", "Frequency");
			this.appendHTMLTag(sb, "th", "Tag");
			
			this.appendHTMLTagClose(sb, "tr");
			this.appendHTMLTagClose(sb, "thead");
			this.appendHTMLTagOpen(sb, "tbody");
			
//			for(String[] result: results) {
//				this.appendHTMLTagOpen(sb, "tr");
//				this.appendTEntries(sb, result);
//				this.appendHTMLTagClose(sb, "tr");
//			}
			
			for(Entry entry: results) {
				this.appendHTMLTagOpen(sb, "tr");
				this.appendHTMLTagOpen(sb, "td");
				this.appendHTMLTag(sb, "label", entry.getChinese());
				
				if(entry.hasTags()) {
					for(String tag: entry.getTags()) {
						this.appendHTMLTag(sb, "badge", tag, "badge");
					}
				}
				
				
				
				this.appendHTMLTagClose(sb, "td");
				
				
//				this.appendHTMLTag(sb, "td", entry.getChinese());
//				this.appendHTMLTag(sb, "td", entry.getEnglishLiteral());
//				this.appendHTMLTag(sb, "td", entry.getEnglishFigurative());
//				this.appendHTMLTag(sb, "td", entry.getPinyin());
//				this.appendHTMLTag(sb, "td", entry.getExample());
//				this.appendHTMLTag(sb, "td", entry.getExampleTranslation());
//				this.appendHTMLTag(sb, "td", entry.getOrigin());
//				this.appendHTMLTag(sb, "td", entry.getOrignTranslation());
//				this.appendHTMLTag(sb, "td", entry.getFrequency());
				this.appendHTMLTag(sb, "td", entry.toString());
				
				Entry newEntry = new Entry("9833", "", "", "", "", "", "", "", "", "", "");
				
				
				this.appendHTMLTag(sb, "td", String.valueOf(results.contains(newEntry)));
				this.appendHTMLTag(sb, "td", String.valueOf(entry.equals(newEntry)));
				
//				this.appendHTMLTagOpen(sb, "td");
//				this.appendHTMLTag(sb, "badge", "negative", "badge");
//				this.appendHTMLTagClose(sb, "td");
				this.appendHTMLTagClose(sb, "tr");
			}
			this.appendHTMLTagClose(sb, "tbody");
			this.appendHTMLTagClose(sb, "table");
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
		
		private void appendHTMLTagOpen(StringBuilder sb, String tag) {
			sb.append("<").append(tag).append(">");
		}
		
		private void appendHTMLTagOpen(StringBuilder sb, String tag, String cssClass) {
			sb.append("<").append(tag).append(" class=\"").append(cssClass).append("\">");
		}
		
		private void appendHTMLTagClose(StringBuilder sb, String tag) {
			sb.append("</").append(tag).append(">");
		}
		
		private void appendTEntries(StringBuilder sb, String[] result) {
			for(String data: result) {
				
				data += "<span class=\"badge\">4</span>";
				
				this.appendHTMLTag(sb, "td", data);
			}
		}
	}


}
