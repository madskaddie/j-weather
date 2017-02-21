package pt.pminds.sandbox.jweather.rest;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;

import pt.pminds.sandbox.jweather.ipma.WeatherInfo;

public class ResourceIndex {

	private final List<Place> places;
	private final PlaceWeather selectedPlace;

	public ResourceIndex(List<Place> places, PlaceWeather selectedPlace) {
		this.places = places;
		this.selectedPlace = selectedPlace;
	}
	public List<Place> getPlaces() {
		return places;
	}
	public PlaceWeather getSelectedPlace() {
		return selectedPlace;
	}
	public static class PlaceWeather {
		private final String description;
		private final WeatherInfo weather;
		public PlaceWeather(String description, WeatherInfo info) {
			super();
			this.description = description;
			this.weather = info;
		}
		public String getDescription() {
			return description;
		}
		public WeatherInfo getWeather() {
			return weather;
		}
	}
	public static class Place {
		private final int code;
		private final String description;
		public Place(int code, String description) {
			super();
			this.code = code;
			this.description = description;
		}
		public int getCode() {
			return code;
		}
		public String getDescription() {
			return description;
		}

		static JsonObject toJson(Place p) {
			return Json.createObjectBuilder()
					.add("code", p.code)
					.add("description", p.description)
					.build();
		}
	}
	public static JsonObject toJson(ResourceIndex model) {
		return Json.createObjectBuilder()
			.add("places", array(model.places, Place::toJson))
			.add("selectedPlace", toJson(model.selectedPlace))
			.build();
	}
	static JsonValue toJson(PlaceWeather p) {
		return Json.createObjectBuilder()
				.add("description", p.description)
				.add("weather", toJson(p.getWeather()))
				.build();
	}
	static JsonValue toJson(WeatherInfo weather) {
		return Json.createObjectBuilder()
				.add("tempMin", weather.getTempMin())
				.build();
	}
	static <F> JsonArray array(Collection<F> collection, Function<F,JsonObject> mapper) {
		return collection.stream().map(mapper).<JsonArrayBuilder>reduce(Json.createArrayBuilder()
				, (acc, el)-> { acc.add(el); return acc; }
				, (x, y) -> { return x.add(y); }
		).build();
	}
}
