package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {
		
		// TODO - START

		//create an array with the length antall
		gpspoints = new GPSPoint[antall]; 
		
		// TODO - SLUTT
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
		
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		// TODO - START
		//Put GPS points into previously created array, return true if inserted.
		if(antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			antall++;
			inserted=true;
		}
		

		// TODO - SLUTT
		
		return inserted;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		GPSPoint gpspoint;

		// TODO - START
		
		//Convert string using converter, then add as gpspoint. 
		gpspoint = GPSDataConverter.convert(time,latitude,longitude,elevation);
		boolean inserted = insertGPS(gpspoint);
		

		// TODO - SLUTT

		
		return inserted; 
		
	}

	public void print() {

		System.out.println("====== Konvertert GPS Data - START ======");

		// TODO - START
		
		//Print array.
		
		for(int x=0; x<gpspoints.length; x++) {

			System.out.print(gpspoints[x].toString());
		}
		

		// TODO - SLUTT
		
		System.out.println("====== Konvertert GPS Data - SLUTT ======");

	}
}
