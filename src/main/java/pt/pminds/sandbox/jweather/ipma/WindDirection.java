package pt.pminds.sandbox.jweather.ipma;

public enum WindDirection {
	N, S, E, W,
	NE, NW, SE, SW
	;

	public static WindDirection fromStringValue(String value) {
		WindDirection rvalue;
		switch (value) {
		case "N":
			rvalue = N;
			break;
		case "S":
			rvalue = S;
			break;
		case "E":
			rvalue = E;
			break;
		case "VV":
			rvalue = W;
			break;
		case "NE":
			rvalue = NE;
			break;
		case "NW":
			rvalue = NW;
			break;
		case "SE":
			rvalue = SE;
			break;
		case "SW":
			rvalue = SW;
			break;
		default:
			throw new IllegalArgumentException("unknown value: "+value);
		}
		return rvalue;
	}
}
