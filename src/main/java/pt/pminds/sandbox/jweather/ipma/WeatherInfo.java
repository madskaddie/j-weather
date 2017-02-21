package pt.pminds.sandbox.jweather.ipma;

public class WeatherInfo {

	private final int tempMax;
	private final int tempMin;
	private final String skyState;
	private final WindSpeed windSpeed;
	private final WindDirection windDirection;
	public WeatherInfo(int tempMax, int tempMin, String skyState, WindSpeed windSpeed, WindDirection windDirection) {
		super();
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.skyState = skyState;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
	}
	public int getTempMax() {
		return tempMax;
	}
	public int getTempMin() {
		return tempMin;
	}
	public String getSkyState() {
		return skyState;
	}
	public WindDirection getWindDirection() {
		return windDirection;
	}
	public WindSpeed getWindSpeed() {
		return windSpeed;
	}
}
