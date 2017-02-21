package pt.pminds.sandbox.jweather.ipma;

import java.time.LocalTime;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

final class IpmaServicesConstants {

	final static Map<IpmaLocation, WeatherTableInfo> weatherTableInfoMap;
	public static final LocalTime T1200 = LocalTime.NOON;
	
	static {
		EnumMap<IpmaLocation, WeatherTableInfo> tmp = new EnumMap<>(IpmaLocation.class);
		tmp.put(IpmaLocation.BRAGANCA, new WeatherTableInfo(0));
		weatherTableInfoMap = Collections.unmodifiableMap(tmp);
	}


	static class WeatherTableInfo {
		final int row;
		public WeatherTableInfo(int row) {
			this.row = row;
		}
	}
	
	private IpmaServicesConstants() {}
}
