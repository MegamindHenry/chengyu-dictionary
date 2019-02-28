/**
 * 
 */
package com.colewe.ws1819.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author jingwen and xuefeng
 *
 */
public interface DictionaryServiceAsync{

//	void search(AsyncCallback<ArrayList<String[]>> callback);
	
	void search(String target, int mode, AsyncCallback<ArrayList<String[]>> callback);
	
	void tagSearch(String tagID, AsyncCallback<ArrayList<String[]>> callback);

}
