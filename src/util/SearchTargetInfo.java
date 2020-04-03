/**
 * 
 */
package util;

/**
 *
 */
public enum SearchTargetInfo {
	
	
	HANKOU("hk"),
	
	WUCHANG("wc"),
	
	HANYANG("hy"),
	
	DONGHU("dhgx");
	
	private final String url;
	private Type type;
	private SearchTargetInfo(String url){
		this.url = url;
	}
	
	private SearchTargetInfo(String url,Type type){
		this.url = url;
		this.setType(type);
	}
	
	Type getType(){
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public SearchTargetInfo setType(Type type) {
		this.type = type;
		return this;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	public String getWholeURL(){
		return getType().getTypeUrl() + "/" + getUrl() + "/";
	}

	public enum Type{
		SUDDEN("tfxts"),
		PLAN("jhxts");
		
		private final String typeUrl;
		private Type(String typeUrl){
			this.typeUrl = typeUrl;
		}
		/**
		 * @return the typeUrl
		 */
		public String getTypeUrl() {
			return typeUrl;
		}
	}
}
