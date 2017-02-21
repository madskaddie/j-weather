package pt.pminds.sandbox.jweather.ipma;

public enum WindSpeed {
	MODERATE
	, WEAK
	;

	public static WindSpeed fromStringValue(String html) {
		final WindSpeed rvalue;
		String value = html.trim();
		switch (value) {
		case "Vento moderado":
			rvalue = WindSpeed.MODERATE;
			break;
		case "Vento fraco":
			rvalue = WindSpeed.WEAK;
			break;
		default:
			throw new IllegalArgumentException("unknown value: "+html);
		}
		return rvalue;
	}
}
