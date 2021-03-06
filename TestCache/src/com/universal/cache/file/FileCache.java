package com.universal.cache.file;


public abstract class FileCache implements IFileCache {
	
	protected FileCache delegate = null;
	
	public FileCache() {}
	
	public FileCache(FileCache delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public final boolean isExist(String key) {
		if(isSelfExist(key)){
			return true;
		} else if(delegate != null && delegate.isExist(key)) {
			put(key, delegate.get(key));
			return true;
		}
		return false;
	}
	
	public final void put(String key, byte[] value){
		selfPut(key, value);
		if(delegate != null)
			delegate.put(key, value);
	}
	
	protected abstract void selfPut(String key, byte[] value);
	protected abstract boolean isSelfExist(String key);

}
