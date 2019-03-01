/**
 * 
 */
package com.colewe.ws1819.client;

import java.util.ArrayList;

import com.colewe.ws1819.shared.Entry;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author jingwen and xuefeng
 *
 */
public interface DictionaryServiceAsync{

//	void search(AsyncCallback<ArrayList<String[]>> callback);
	
	void search(String target, int mode, AsyncCallback<ArrayList<Entry>> callback);
	
	void tagSearch(String tagID, AsyncCallback<ArrayList<Entry>> callback);

}
