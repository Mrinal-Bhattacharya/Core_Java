package com.ds.map;

public class UserHashMap {
	private static final int BUCKET_SIZE=16;
	private int size;
	private Entry[] bucket=new Entry[BUCKET_SIZE];

	public String get(Object key){
		int hashCode=key.hashCode()%BUCKET_SIZE;
		Entry entry=bucket[hashCode];
		
		while(entry!=null)
		{
			if(entry.getKey().equals(key)){
				return entry.getValue();
			}
			entry=entry.next;
		}
		return null;
	}
	
	
	public boolean put(Object key,String value){
		int hashCode=key.hashCode()%BUCKET_SIZE;
		System.out.println(hashCode);
		Entry entry=bucket[hashCode];
		if(entry==null)
		{
			Entry newEntry=new Entry(key, value);
			bucket[hashCode]=newEntry;
			size++;
			return true;
		}
		else{
			if(entry.key.equals(key))
			{
				entry.value=value;
				return true;
			}
			while(entry.next!=null)
			{
				entry=entry.next;
			}
			Entry newEntry=new Entry(key, value);
			entry.next=newEntry;
			size++;
			return true;
		}
	}
public int size(){
	return this.size;
}

	static class Entry{
		final Object key;
		String value;
		Entry next;
		public Entry(Object key,String value) {
			this.key=key;
			this.value=value;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public Entry getNext() {
			return next;
		}
		public void setNext(Entry next) {
			this.next = next;
		}
		public Object getKey() {
			return key;
		}

	}
}
