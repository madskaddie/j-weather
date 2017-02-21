package pt.pminds.sandbox.jweather.ipma;

import java.io.IOException;
import java.net.URI;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ServicesIpma {

	private final String endpoint;
	

	public ServicesIpma(URI endpointUri) {
		this.endpoint = (endpointUri==null) ? null : endpointUri.toASCIIString();
	}

	public Optional<WeatherInfo> getPlaceWeather(Integer cityCode) throws IOException {
		String url = endpoint+"/pt/html.prev.jsp";
		Document doc = Jsoup.connect(url).get();
		IpmaLocation local = IpmaLocation.fromCode(cityCode);
		return doFindWether(doc, local, LocalTime.now());
	}

	Optional<WeatherInfo> doFindWether(Document doc, IpmaLocation local, LocalTime at) {
		Element dataTable = doc.select(".tablelist").first();
		Iterator<Element> rowsIterator = dataTable.select("tr").iterator();

		// headers
		rowsIterator.next();
		rowsIterator.next();

		WeatherInfo value = null; int n = IpmaServicesConstants.weatherTableInfoMap.get(local).row;
		for(int i=0; rowsIterator.hasNext(); i+=2) {
			Element el = rowsIterator.next();
			if (i == n) {
				String skyState;
				WindSpeed windState;
				WindDirection windDir;
				Elements children = el.children();
				int tMin = Integer.parseInt(children.get(3).html().trim());
				int tMax = Integer.parseInt(children.get(4).html().trim());
					
				if (at.isAfter(IpmaServicesConstants.T1200)) {
					el = rowsIterator.next(); children = el.children();
					skyState = children.get(1).html();
					windState = WindSpeed.fromStringValue(children.get(2).html().trim());
					windDir = WindDirection.fromStringValue(children.get(3).html().trim());
				}
				else {
					skyState = children.get(3).html();
					windState = WindSpeed.fromStringValue(children.get(5).html().trim());
					windDir = WindDirection.fromStringValue(children.get(6).html().trim());
				}
				
				value = new WeatherInfo(tMax, tMin, skyState, windState, windDir);
				break;
			}
			rowsIterator.next();
		}
		return Optional.ofNullable(value);
	}
}
