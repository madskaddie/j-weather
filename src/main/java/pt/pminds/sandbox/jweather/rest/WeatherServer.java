package pt.pminds.sandbox.jweather.rest;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import pt.pminds.sandbox.jweather.ipma.IpmaLocation;
import pt.pminds.sandbox.jweather.ipma.ServicesIpma;
import pt.pminds.sandbox.jweather.ipma.WeatherInfo;
import pt.pminds.sandbox.jweather.rest.ResourceIndex.Place;
import pt.pminds.sandbox.jweather.rest.ResourceIndex.PlaceWeather;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WeatherServer {

	public static void main(String[] args) {
		new WeatherServer().run();
	}

	private int port = 8080;
	private final HandlebarsTemplateEngine engine;
	private final ServicesIpma services;
	public WeatherServer() {
		this.engine = new HandlebarsTemplateEngine();
		this.services = new ServicesIpma(URI.create("http://www.ipma.pt"));
		
	}
	private void run() {

		Spark.port(port);
		Spark.staticFiles.location("/public");
		Spark.get("/", (req, res) -> {
			PlaceWeather selectedPlace;
			String strPlaceRef = req.queryParams("placeRef");
			if(strPlaceRef == null ) {
				selectedPlace = null;
			}
			else {
				Optional<WeatherInfo> optWeather = services.getPlaceWeather(Integer.valueOf(strPlaceRef));
				if (optWeather.isPresent()) {
					selectedPlace = new PlaceWeather("some description", optWeather.get());
				}
				else {
					Spark.halt(404, "place not found");
					throw new RuntimeException();
				}
			}
			List<Place> places = Arrays.asList(new Place(1, IpmaLocation.BRAGANCA.toString()));
			ResourceIndex model = new ResourceIndex(places, selectedPlace);

			if (req.contentType() == "application/json") {
				return ResourceIndex.toJson(model);
			}
			else {
				return engine.render(new ModelAndView(model,"index.hbs"));
			}
			
		});
		
		Spark.awaitInitialization();
	}

}
