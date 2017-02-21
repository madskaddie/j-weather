package pt.pminds.sandbox.jweather.ipma;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import pt.pminds.sandbox.jweather.ipma.IpmaLocation;
import pt.pminds.sandbox.jweather.ipma.ServicesIpma;
import pt.pminds.sandbox.jweather.ipma.WeatherInfo;

public class TestGetCity {

	private final ServicesIpma underTest;

	public TestGetCity() {
		this.underTest = new ServicesIpma(null);
	}

	@Test
	public void test01() throws Exception {
		String html = doReadPackageResourceAsString("t01.html");
		Document doc = Jsoup.parse(html);

		Optional<WeatherInfo> actual = underTest.doFindWether(doc, IpmaLocation.BRAGANCA, LocalTime.of(10, 30));

		Assert.assertNotNull(actual);
		Assert.assertTrue(actual.isPresent());
		return;
	}

	private String doReadPackageResourceAsString(String resourceName) throws IOException {
		String rvalue;
		try (InputStream is = getClass().getResourceAsStream(resourceName)){
			rvalue = doReadString(is, "UTF-8");
		}
		return rvalue;
	}

	private String doReadString(InputStream is, String encoding) throws IOException {
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int b;
		while((b=is.read()) >= 0) {
			buf.write(b);
		}
		String rvalue = new String(buf.toByteArray(), encoding);
		return rvalue;
	}
}
