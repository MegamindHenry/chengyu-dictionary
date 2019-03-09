/**
 * 
 */
package com.colewe.ws1819.client;

import java.util.ArrayList;

import com.colewe.ws1819.shared.Entry;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author jingwen and xuefeng
 *
 */
@RemoteServiceRelativePath("dictionary")
public interface DictionaryService extends RemoteService{
	
//	ArrayList<String[]> search();
	
	ArrayList<Entry> search(String target, int mode);
	ArrayList<Entry> tagSearch(String target, int mode, ArrayList<String> tags);


}
