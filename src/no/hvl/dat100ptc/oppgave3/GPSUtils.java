package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import java.text.DecimalFormat;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		min = da[0];
	
		// TODO - START

		for(double d : da) {
			if (d < min) {
				min = d;
				
				
			}
		}

		// TODO - SLUT
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double[] latTab = new double[gpspoints.length];
		for(int x=0; x < latTab.length; x++) {
			latTab[x] = gpspoints[x].getLatitude();
		}
		
		return latTab;
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double[] longTab = new double[gpspoints.length];
		for(int x=0; x < longTab.length; x++) {
			longTab[x] = gpspoints[x].getLongitude();
		}
		// TODO - SLUTT
		return longTab;
	}
		

	private static int R = 6371000; // jordens radius
 
	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d; //distance
		double latitude1, longitude1, latitude2, longitude2;
		
		
		
		// TODO - START
		//Get og få i degrees
		latitude1 = gpspoint1.getLatitude(); //bruker get latitude for å hente latitude for punkter.
		latitude2 = gpspoint2.getLatitude();
		longitude1 = gpspoint1.getLongitude(); // ^ longitudes
		longitude2 = gpspoint2.getLongitude();
		
		//Grader om til Radianer.
		latitude1 = latitude1*Math.PI/180;
		latitude2 = latitude2*Math.PI/180;
		longitude1 = longitude1*Math.PI/180;
		longitude2 = longitude2*Math.PI/180;
		
		
		double deltalat = latitude2-latitude1;
		double deltalong = longitude2-longitude1;
		
		
		double p1 = Math.pow(Math.sin(deltalat/2), 2);
		double p2 = Math.sin(deltalong/2)*Math.sin(deltalong/2);
		
		double a = p1+Math.cos(latitude1)*Math.cos(latitude2)*p2;
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		
		
		d = R*c;
		//throw new Uns	upportedOperationException(TODO.method());

		// TODO - SLUTT
		return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		
		int sec1 = gpspoint1.getTime();
		int sec2 = gpspoint2.getTime();
		double d = distance(gpspoint1, gpspoint2);
		

		// TODO - START
		secs = sec2-sec1; //Delta time
		
		//Regn om secs og distance til gjennomsnitts km/t
		double ds = d/secs; //meter per sec
		double dh = ds*3600; //meter per hour
		double kmh = dh/1000; //km/h
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
		return kmh;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		//Regn om secs til timer, bruk rest til minutter, så rest til sekunder
		// TODO - START

		int hours = secs/3600;
		int minutes = (secs%3600)/60;
		int seconds = (secs%3600)%60;

		String hrs = Integer.toString(hours/10)+Integer.toString(hours%10); // hours/10 = tier posisjon. hours%10 = 1 posisjon. 
		String mins = Integer.toString(minutes/10)+Integer.toString(minutes%10);
		String sek = Integer.toString(seconds/10)+Integer.toString(seconds%10);
		
		
		timestr = ("  "+hrs+TIMESEP+mins+TIMESEP+sek);
		
		
		
		return timestr;
		
		
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
	
		// TODO - START

		DecimalFormat df = new DecimalFormat("#.##");      
		d = Double.valueOf(df.format(d));
		
		str = Double.toString(d);
		
		
		while(str.length() < TEXTWIDTH) {
			
			str = (" "+str);
			
			
		}
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		return str;
	}
}
