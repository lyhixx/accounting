package com.axdoc.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LatLon {

	public static final double R = 6371;// 地球半径

	private double lat;

	private double lon;

	public LatLon(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	/**
	 * 给定一个初始方位角和距离（千米），计算另一个点的坐标
	 * 
	 * @param brng
	 * @param d
	 * @return
	 */
	public LatLon destPoint(double brng, double d) {
		double lat1 = toRad(this.lat), lon1 = toRad(this.lon);
		brng = toRad(brng);

		double lat2 = Math.asin(Math.sin(lat1) * Math.cos(d / R) + Math.cos(lat1) * Math.sin(d / R) * Math.cos(brng));
		double lon2 = lon1 + Math.atan2(Math.sin(brng) * Math.sin(d / R) * Math.cos(lat1),
				Math.cos(d / R) - Math.sin(lat1) * Math.sin(lat2));
		lon2 = (lon2 + Math.PI) % (2 * Math.PI) - Math.PI; // normalise to
															// -180...+180

		// if (isNaN(lat2) || isNaN(lon2)) return null;
		return new LatLon(toDeg(lat2), toDeg(lon2));
	}

	/**
	 * 给定一个初始方位角和距离计算另一个点的最终方位角
	 * 
	 * @param brng
	 * @param d
	 * @return
	 */
	public double finalBrng(double brng, double d) {
		LatLon p1 = this;
		LatLon p2 = p1.destPoint(brng, d);
		// get reverse bearing point 2 to point 1
		double rev = LatLon.bearing(p2.lat, p2.lon, p1.lat, p1.lon);
		// & reverse it by adding 180
		return (rev + 180) % 360;
	}

	public LatLon destPointRhumb(double brng, double dist) {
		double d = dist / R; // d = angular distance covered on earth's
								// surface
		double lat1 = toRad(this.lat), lon1 = toRad(this.lon);
		brng = toRad(brng);

		double lat2 = lat1 + d * Math.cos(brng);
		double dLat = lat2 - lat1;
		double dPhi = Math.log(Math.tan(lat2 / 2 + Math.PI / 4) / Math.tan(lat1 / 2 + Math.PI / 4));
		double q = (Math.abs(dLat) > 1e-10) ? dLat / dPhi : Math.cos(lat1);
		double dLon = d * Math.sin(brng) / q;
		// check for some daft bugger going past the pole
		if (Math.abs(lat2) > Math.PI / 2)
			lat2 = lat2 > 0 ? Math.PI - lat2 : -(Math.PI - lat2);
		double lon2 = (lon1 + dLon + Math.PI) % (2 * Math.PI) - Math.PI;

		// if (isNaN(lat2) || isNaN(lon2)) return null;
		return new LatLon(toDeg(lat2), toDeg(lon2));

	}

	public static double toPrecision(double d, int fig) {
		if (d == 0)
			return 0;// trailing zeros in place of exponential notation
		double scale = Math.ceil(Math.log(d) * Math.log10(Math.E));
		double mult = Math.pow(10, fig - scale);
		return Math.round(d * mult) / mult;
	}

	public static double toDeg(double d) {
		return d * 180 / Math.PI;
	}

	public static double toBrng(double d) {
		return (toDeg(d) + 360) % 360;
	}

	public static double toRad(double d) {
		return d * Math.PI / 180;
	}

	public static double parseDeg(String s) {
		double deg;
		String degLL = s.replaceAll("^-", "").replace("[NSEW]", "");
		String[] dms = degLL.split("[^0-9.,]+"); // split out separate d/m/s
		// remove empty elements (see note below)
		// for (var i in dms) if (dms[i]=='') dms.splice(i,1);
		switch (dms.length) { // convert to decimal degrees...
		case 3: // interpret 3-part result as d/m/s
			deg = Double.parseDouble(dms[0]) / 1 + Double.parseDouble(dms[1]) / 60 + Double.parseDouble(dms[2]) / 3600;
			break;
		case 2: // interpret 2-part result as d/m
			deg = Double.parseDouble(dms[0]) / 1 + Double.parseDouble(dms[1]) / 60;
			break;
		case 1: // decimal or non-separated dddmmss
			Pattern p = Pattern.compile("[NS]");
			Matcher m = p.matcher(s);
			if (m.find())
				degLL = '0' + degLL; // - normalise N/S to 3-digit degrees
			deg = Double.parseDouble(dms[0].substring(0, 3)) / 1 + Double.parseDouble(dms[0].substring(3, 5)) / 60
					+ Double.parseDouble(dms[0].substring(5)) / 3600;
			break;
		default:
			return 0;
		}
		Pattern p = Pattern.compile("^-");
		Matcher m = p.matcher(s);
		Pattern p2 = Pattern.compile("[WS]");
		Matcher m2 = p2.matcher(s);
		if (m.find() || m2.find())
			deg = -deg; // take '-', west and south as -ve
		return deg;
	}

	public static String toDMS(double v) {
		double d = Math.abs(v); // (unsigned result ready for appending compass
								// dir'n)
		d += 1 / 7200; // add second for rounding
		double deg = Math.floor(d);
		double min = Math.floor((d - deg) * 60);
		double sec = Math.floor((d - deg - min / 60) * 3600);
		String sdeg = "" + (int) deg;
		String smin = "" + (int) min;
		String ssec = "" + (int) sec;
		// add leading zeros if required
		if (deg < 100)
			sdeg = "0" + sdeg;
		if (deg < 10)
			sdeg = "0" + sdeg;
		if (min < 10)
			smin = "0" + smin;
		if (sec < 10)
			ssec = "0" + ssec;
		return sdeg + "°" + smin + "′" + ssec + "″";
	}

	public static String toLat(double d) {
		return String.valueOf(toDMS(d)).substring(1) + (d < 0 ? 'S' : 'N');
	}

	public static String toLon(double d) {
		return toDMS(d) + (d > 0 ? 'E' : 'W');
	}

	/**
	 * 计算方位线上两点的距离
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double distRhumb(double lat1, double lon1, double lat2, double lon2) {
		double dLat = toRad(lat2 - lat1), dLon = toRad(Math.abs(lon2 - lon1));
		double dPhi = Math.log(Math.tan(toRad(lat2) / 2 + Math.PI / 4) / Math.tan(toRad(lat1) / 2 + Math.PI / 4));
		double q = (Math.abs(dLat) > 1e-10) ? dLat / dPhi : Math.cos(toRad(lat1));
		// if dLon over 180 take shorter rhumb across 180 meridian:
		if (dLon > Math.PI)
			dLon = 2 * Math.PI - dLon;
		double d = Math.sqrt(dLat * dLat + q * q * dLon * dLon);
		return d * R;
	}

	public static double brngRhumb(double lat1, double lon1, double lat2, double lon2) {
		double dLon = toRad(lon2 - lon1);
		double dPhi = Math.log(Math.tan(toRad(lat2) / 2 + Math.PI / 4) / Math.tan(toRad(lat1) / 2 + Math.PI / 4));
		if (Math.abs(dLon) > Math.PI)
			dLon = dLon > 0 ? -(2 * Math.PI - dLon) : (2 * Math.PI + dLon);
		return toBrng(Math.atan2(dLon, dPhi));
	}

	/**
	 * 使用 Haversine 公式 来计算地球上两点的距离 (千米)
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double distHaversine(double lat1, double lon1, double lat2, double lon2) {
		double dLat = toRad(lat2 - lat1), dLon = toRad(lon2 - lon1);
		lat1 = toRad(lat1);
		lat2 = toRad(lat2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		return d;

	}

	/**
	 * 使用余弦定律来计算地球上两点的距离（千米）
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double distCosineLaw(double lat1, double lon1, double lat2, double lon2) {
		double d = Math.acos(Math.sin(toRad(lat1)) * Math.sin(toRad(lat2))
				+ Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.cos(toRad(lon2 - lon1))) * R;
		return d;

	}

	/**
	 * 计算两点之间的初始方位角
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double bearing(double lat1, double lon1, double lat2, double lon2) {
		lat1 = toRad(lat1);
		lat2 = toRad(lat2);
		double dLon = toRad(lon2 - lon1);

		double y = Math.sin(dLon) * Math.cos(lat2);
		double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(dLon);
		return toBrng(Math.atan2(y, x));
	}

	/**
	 * 计算两点之间的中间点坐标
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static LatLon midPoint(double lat1, double lon1, double lat2, double lon2) {
		lat1 = toRad(lat1);
		lat2 = toRad(lat2);
		double dLon = toRad(lon2 - lon1);

		double Bx = Math.cos(lat2) * Math.cos(dLon);
		double By = Math.cos(lat2) * Math.sin(dLon);

		double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2),
				Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
		double lon3 = toRad(lon1) + Math.atan2(By, Math.cos(lat1) + Bx);

		// if (isNaN(lat3) || isNaN(lon3)) return null;
		return new LatLon(toDeg(lat3), toDeg(lon3));
	}

	public String toString() {
		return toLat(this.lat) + ", " + toLon(this.lon);
	}

	public static void main(String[] args) {
		double lat = parseDeg("51 07 32N"), lon = parseDeg("001 20 17E");
		LatLon latlon = new LatLon(lat, lon);
		LatLon dest = latlon.destPointRhumb(parseDeg("0°0′0″"), 1);
		LatLon dest2 = latlon.destPointRhumb(parseDeg("90°0′0″"), 1);
		LatLon dest3 = latlon.destPointRhumb(parseDeg("180°0′0″"), 1);
		LatLon dest4 = latlon.destPointRhumb(parseDeg("270°0′0″"), 1);
		System.out.println(distCosineLaw(116.433676, 39.909685, 121.447926, 37.463819));
	}
}