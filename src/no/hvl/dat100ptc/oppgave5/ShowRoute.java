package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {
	
	private static String[] output;
	private static int MARGIN = 50;
	private static int MAPXSIZE = 600;
	private static int MAPYSIZE = 600;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		output = gpscomputer.displayarray();
		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		playRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		// TODO - START
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
			ystep = MAPYSIZE / (Math.abs(maxlat-minlat));
		
		// TODO - SLUTT
		return ystep;
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		double startx = 0;
		double starty = 0;
		double ystep = ystep();
		double xstep = xstep();

		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		
		startx = MARGIN+(Math.abs(gpspoints[0].getLongitude()-minlon)*xstep);
		starty = ybase-(Math.abs(gpspoints[0].getLatitude()-minlat)*ystep);
		
		
		for(int x=1; x < gpspoints.length-1; x++) {
			
			double b1 = gpspoints[x].getLatitude();	
			double l1 = gpspoints[x].getLongitude();
			
			double endx = MARGIN+(Math.abs(l1-minlon)*xstep);
			double endy = ybase-(Math.abs(b1-minlat)*ystep);
			
			setColor(0,255,0);
			drawLine((int)startx,(int)starty,(int)endx,(int)endy);

			drawCircle((int)startx,(int)starty, 2);
			fillCircle((int)startx,(int)starty, 2);
			startx = endx;
			starty = endy;
			
	
		// TODO - SLUTT
	}
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Arial", 12);
		// TODO - START

		int y = 10;
		
		for(int x=0; x<output.length; x++) {
		
		drawString(output[x], TEXTDISTANCE, y);
		y += 10;

		}
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {
	
		// TODO - START
		double ystep = ystep();
		double xstep = xstep();
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		
		
		double startx = MARGIN+(Math.abs(gpspoints[0].getLongitude()-minlon)*xstep);
		double starty = ybase-(Math.abs(gpspoints[0].getLatitude()-minlat)*ystep);
		
		
		setColor(0,0,255);
	
		int sirkel = fillCircle((int)startx,(int)starty, 5);
		setSpeed(3);
		
		
		for(int x=0; x < gpspoints.length; x++) {
			double b1 = gpspoints[x].getLatitude();	
			double l1 = gpspoints[x].getLongitude();
			
			double endx = MARGIN+(Math.abs(l1-minlon)*xstep);
			double endy = ybase-(Math.abs(b1-minlat)*ystep);
			
			moveCircle((int)sirkel, (int)endx, (int)endy);
			
		}
		
		
		
		
		// TODO - SLUTT
	}

}
