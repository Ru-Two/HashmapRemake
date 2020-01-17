/*
 Ruturaj Darji
 2018 Vincent Massey Secondary
*/

import java.util.*;

public class HashTables{
	public static void main (String [] args){
		HashTable<String> names = new HashTable<String>();
		names.add("Leo");
		names.add("Gary");
		names.add("Luka");
		names.add("Alex");
		names.add("Jasnoor");
		names.add("Musbah");
		names.add("Robin");
		System.out.println(names);
	}
}

	
class HashTable<T>{
	private ArrayList<LinkedList<T>> table;
	int items=0;
	
	//Creating a null list of n values
	private void fill(int n){
		table = new ArrayList<LinkedList<T>>();
		for (int i = 0; i<n; i++){
			table.add(null);
		}
	}
	
	//default to size 10 on construction
	public HashTable(){
		fill(10);
	}
	
	//add value to map
	public void add(T val){
		int hash = Math.abs(val.hashCode());
		int spot = hash%table.size();
		LinkedList<T> list = table.get(spot);
		if(list==null){
			list = new LinkedList<T>();
			table.set(spot,list);
		}
		if (!list.contains(val)){
			list.add(val);
			items++;
			if (items*100/table.size()>70){
				resize();
			}
		}
		
	}
	
	//resize map by multiples of 10
	public void resize(){
		ArrayList<LinkedList<T>> tmp = table;
		items = 0;
		fill(table.size()*10);
		for (LinkedList<T> lst : tmp){
			if (lst!=null){
				for (T val : lst){
					add(val);
				}
			}
		}
	}
	
	//simple toString
	public String toString(){
		String ans = "# ";		
		for (LinkedList<T> lst : table){
			if (lst!=null){
				for(T val : lst){
					ans+=val+", ";
				}
			}
		}
		if (ans!="# "){
			ans = ans.substring(0,ans.length()-2);
		}
		return ans+" #";
	}
}