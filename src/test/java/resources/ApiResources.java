package resources;

public enum ApiResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;

	ApiResources(String resource) {
		
		this.resource=resource;
	}
	
	public String getApiResource() {
		
		return resource;
	}

}
