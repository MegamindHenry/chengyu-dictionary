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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.*;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.RadioButton;
import org.gwtbootstrap3.extras.select.client.ui.MultipleSelect;
import org.gwtbootstrap3.extras.select.client.ui.Option;

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
	@UiField
	Button downloadButton;

	
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
		
		advancedOption.setActive(false);
		advancedOption.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				// do something (activate advanced search)
			
			}
			
		});
		
		searchButton.setText("Search");
		searchButton.addStyleName("btn btn-dark");
		
		tagButton.setText("Tag");
		tagButton.addStyleName("btn btn-dark");
		
		downloadButton.setText("Download");
		
		filter.setVisible(true);
		filter.setSize("350px", "30px");
		
//		outputHTML.setHTML("<h3>Search results will go here<h3>");
		
		inputTextBox.addStyleName("form-control");
		inputTextBox.setSize("600px", "30px");
	}
	
	public ArrayList<String> getFilter() {
		ArrayList<String> tagsFilter = new ArrayList<String>();
		
		List<Option> items = this.filter.getSelectedItems();
		
		for(Option item: items) {
			tagsFilter.add(item.getValue());
		}
		
		return tagsFilter;
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
	
//	private class OutputTableBuilder{
//		
//		StringBuilder sb;
//		
//		public OutputTableBuilder() {
//		}
//		
//		public OutputTableBuilder(ArrayList<Entry> results) {
//			sb = new StringBuilder("");
//			
//			this.appendHTMLTagOpen(sb, "table");
//			this.appendHTMLTagOpen(sb, "thead");
//			this.appendHTMLTagOpen(sb, "tr");
//			
//			this.appendHTMLTag(sb, "th", "Chinese");
//			this.appendHTMLTag(sb, "th", "Pinyin");
//			this.appendHTMLTag(sb, "th", "English Literal");
//			this.appendHTMLTag(sb, "th", "English Figurative");
//			this.appendHTMLTag(sb, "th", "Example");
////			this.appendHTMLTag(sb, "th", "Example Translation");
//			this.appendHTMLTag(sb, "th", "Origin");
////			this.appendHTMLTag(sb, "th", "Origin Translation");
//			this.appendHTMLTag(sb, "th", "Frequency");
//			this.appendHTMLTagClose(sb, "tr");
//			this.appendHTMLTagClose(sb, "thead");
//			this.appendHTMLTagOpen(sb, "tbody");
//			for(Entry entry: results) {
//				this.appendHTMLTagOpen(sb, "tr");
//				this.appendHTMLTagOpen(sb, "td");
//				this.appendHTMLTag(sb, "label", entry.getChinese());
//				if(entry.hasTags()) {
//					for(String tag: entry.getTags()) {
//						this.appendHTMLTag(sb, "badge", tag, "badge");
//					}
//				}
//				this.appendHTMLTagClose(sb, "td");
//				this.appendHTMLTag(sb, "td", entry.getPinyin());
//				this.appendHTMLTag(sb, "td", entry.getEnglishLiteral());
//				this.appendHTMLTag(sb, "td", entry.getEnglishFigurative());
//				this.appendHTMLTag(sb, "td", entry.getExample());
////				this.appendHTMLTag(sb, "td", entry.getExampleTranslation());
//				this.appendHTMLTag(sb, "td", entry.getOrigin());
////				this.appendHTMLTag(sb, "td", entry.getOrignTranslation());
//				this.appendHTMLTag(sb, "td", entry.getFrequency());
//				
////				ArrayList<String> tags = new ArrayList<String>();
////				this.appendHTMLTag(sb, "td", entry.hasTags(tags));
//				
//				this.appendHTMLTagClose(sb, "tr");
//			}
//			this.appendHTMLTagClose(sb, "tbody");
//			this.appendHTMLTagClose(sb, "table");
//		}
//		
//		private String toHTML() {
//			return this.sb.toString();
//		}
//		
//		//may be used for highlighting?
//		private void appendHTMLTag(StringBuilder sb, String tag, String elem) {
//			sb.append("<").append(tag).append(">"); // open tag
//			sb.append(elem);
//			sb.append("</").append(tag).append(">"); // closing tag
//		}
//		
//		private void appendHTMLTag(StringBuilder sb, String tag, String elem, String cssClass) {
//			sb.append("<").append(tag).append(" class=\"").append(cssClass).append("\">"); // open tag
//			sb.append(elem);
//			sb.append("</").append(tag).append(">"); // closing tag
//		}
//		
//		private void appendHTMLTagOpen(StringBuilder sb, String tag) {
//			sb.append("<").append(tag).append(">");
//		}
//		
//		private void appendHTMLTagOpen(StringBuilder sb, String tag, String cssClass) {
//			sb.append("<").append(tag).append(" class=\"").append(cssClass).append("\">");
//		}
//		
//		private void appendHTMLTagClose(StringBuilder sb, String tag) {
//			sb.append("</").append(tag).append(">");
//		}
//		
//		private void appendTEntries(StringBuilder sb, String[] result) {
//			for(String data: result) {
//				
//				data += "<span class=\"badge\">4</span>";
//				
//				this.appendHTMLTag(sb, "td", data);
//			}
//		}
//	}
	
	
	private class OutputTableBuilder{
		
		StringBuilder sb;
		
		public OutputTableBuilder() {
		}
		
		public OutputTableBuilder(ArrayList<Entry> results) {
			sb = new StringBuilder("");
			
			// open tags
			this.appendHTMLTagOpen(sb, "b:PanelGroup" + "\t" + "b:id=\"accordion\"");
			
			for(Entry entry: results) {
				this.appendHTMLTagOpen(sb, "b:Panel");
				this.appendHTMLTagOpen(sb, "b:PanelHeader");
				this.appendHTMLTagOpen(sb, "Heading" + "\t" + "size:\"H4\"");
				this.appendHTMLTagClose(sb, "b:Heading");
				this.appendHTMLTagClose(sb, "b:PanelHeader");
			
				StringBuilder block = new StringBuilder("");
				
				this.appendHTMLTagOpen(block, "table");
				this.appendHTMLTagOpen(block, "thead");
				this.appendHTMLTagOpen(block, "tr\\tvalign=\\\"top\\\"");
				
				this.appendHTMLTag(block, "th", "Chinese");
				this.appendHTMLTag(block, "th", "Pinyin");
				this.appendHTMLTag(block, "th", "English Literal");
				this.appendHTMLTag(block, "th", "English Figurative");
				this.appendHTMLTag(block, "th", "Example");
//				this.appendHTMLTag(block, "th", "Example Translation");
				this.appendHTMLTag(block, "th", "Origin");
//				this.appendHTMLTag(block, "th", "Origin Translation");
				this.appendHTMLTag(block, "th", "Frequency");
				this.appendHTMLTagClose(block, "tr");
				this.appendHTMLTagClose(block, "thead");
				this.appendHTMLTagOpen(block, "tbody");
				
				
				
				this.appendHTMLTag(block, "label", entry.getChinese());
			
				if(entry.hasTags()) {
					for(String tag: entry.getTags()) {
						this.appendHTMLTag(block, "badge", tag, "badge");
					}
				}
				this.appendHTMLTagOpen(block, "tr\tvalign=\"top\"");
				this.appendHTMLTag(block, "td", entry.getChinese());
				
				
				this.appendHTMLTag(block, "td", entry.getPinyin());
				this.appendHTMLTag(block, "td", entry.getEnglishLiteral());
				this.appendHTMLTag(block, "td", entry.getEnglishFigurative());
				this.appendHTMLTag(block, "td", entry.getExample());
//				this.appendHTMLTag(block, "td", entry.getExampleTranslation());
				this.appendHTMLTag(block, "td", entry.getOrigin());
//				this.appendHTMLTag(block, "td", entry.getOrignTranslation());
				this.appendHTMLTag(block, "td", entry.getFrequency());
				
//				ArrayList<String> tags = new ArrayList<String>();
//				this.appendHTMLTag(block, "td", entry.hasTags(tags));
				
				this.appendHTMLTagClose(block, "tr");
				this.appendHTMLTagClose(block, "tbody");
				this.appendHTMLTagClose(block, "table");
				this.appendHTMLTag(sb, "b:PanelBody", block.toString());
				this.appendHTMLTagClose(sb, "b:Panel");
			}
			// close tags
			this.appendHTMLTagClose(sb, "b:PanelGroup");
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
