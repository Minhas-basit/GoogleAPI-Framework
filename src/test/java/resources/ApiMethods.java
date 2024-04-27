package resources;

public enum ApiMethods {
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String EPresource;
	ApiMethods(String EPresource){
	this.EPresource=EPresource;
	}
	public String getEPresource() {
		return EPresource;
	}
}
