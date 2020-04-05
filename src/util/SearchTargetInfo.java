/**
 * 
 */
package util;

/**
 *
 */
public enum SearchTargetInfo {
	
	
	HANKOU("hk","汉口"),
	
	WUCHANG("wc","武昌"),
	
	HANYANG("hy","汉阳"),
	
	DONGHU("dhgx","东湖高新");
	
	private final String url;
	private final String areaName;
	private Type type;
	private SearchTargetInfo(String url,String name){
		this.url = url;
		this.areaName = name;
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

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	public enum Type{
		SUDDEN("tfxts","突发性停水"),
		PLAN("jhxts","计划性停水");
		
		private final String typeUrl;
		private final String typeName;
		private Type(String typeUrl,String typeName){
			this.typeUrl = typeUrl;
			this.typeName = typeName;
		}
		/**
		 * @return the typeUrl
		 */
		public String getTypeUrl() {
			return typeUrl;
		}
		/**
		 * @return the typeName
		 */
		public String getTypeName() {
			return typeName;
		}
	}
}
