package pt.pminds.sandbox.jweather.ipma;

public enum IpmaLocation {
	BRAGANCA
	;

	public static IpmaLocation fromCode(Integer cityCode) {
		if (cityCode==null) throw new IllegalArgumentException();
		final IpmaLocation rvalue;
		int value = cityCode.intValue();
		switch (value) {
		case 1:
			rvalue = BRAGANCA;
			break;
		default:
			throw new IllegalArgumentException("Unknown value: "+ Integer.toString(value));
		}
		return rvalue;
	}
}