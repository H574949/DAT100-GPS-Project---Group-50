package no.hvl.dat100ptc.oppgave4;

import java.text.DecimalFormat;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);  //Henter log fil.
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		for (int x = 0; x < gpspoints.length - 1; x++) { //GÂr igjennom gpspunkter og henter distance.
														//Kunne ogsÂ vÊrt gjort ved gpspoints[0], gpspoints[gpspoints.length-1]

			distance = distance + GPSUtils.distance(gpspoints[x], gpspoints[x + 1]);

		}
		// TODO - SLUTT

		return distance;

	}

	// beregn totale h√∏ydemeter (i meter)
	public double totalElevation() {

		double elevation = gpspoints[0].getElevation();

		// TODO - START

		// elevation = height/d
		for (int x = 0; x < gpspoints.length - 1; x++) {	//GÂr igjennom gpspunkter og henter distance.
															//Kunne ogsÂ vÊrt gjort ved gpspoints[0], gpspoints[gpspoints.length-1]

			elevation += gpspoints[x + 1].getElevation() - gpspoints[x].getElevation();
		}

		// TODO - SLUTT
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		//Gjort alternativt mÂte.

		int time = gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
		
		return time;

	}

	public double[] speeds() {
		
		// beregn gjennomsnitshastighets mellom hver av gps punktene, sÂ legger dem inn i tabell.

		// TODO - START // OPPGAVE - START
		double[] speedkh = new double[gpspoints.length - 1];

		for (int x = 0; x < gpspoints.length - 1; x++) {
			speedkh[x] = GPSUtils.speed(gpspoints[x], gpspoints[x + 1]);

		}
		

		// TODO - SLUTT
		return speedkh;

	}

	public double maxSpeed() {

		double maxspeed = 0;

		// TODO - START 
		
		//lag tabell
		double[] speedkh = speeds();		//Hastighet 
		
		//bruk find max til for Â returne maxspeed av tabellen
		maxspeed = GPSUtils.findMax(speedkh);
		

		// TODO - SLUTT
		return maxspeed;
	}

	public double averageSpeed() {

		double average = 0;

		// TODO - START
		
		double distance = totalDistance(); 
		double time = totalTime();
		
		average = distance/time; //meter per sec
		average = average*3600; //meter per hour
		average = average/1000; //km/h
		
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
		return average;

	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling, general
	 * 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0 bicycling,
	 * 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9 mph, racing or
	 * leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph, racing/not drafting
	 * or >19 mph drafting, very fast, racing general 12.0 bicycling, >20 mph,
	 * racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kj√∏res med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {
		double kcal;
		//Energy Expended (kcal) = MET x Body Weight (kg) x Time (h)
		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;
		int sec = secs;
		double speedmph = speed * MS;
		double hours = secs/3600.0;
		// TODO - START
		
		if(speedmph	< 10) { met = 4; }
		else if(speedmph >= 10 && speedmph < 12) { met = 6.0; }
		else if(speedmph >= 12 && speedmph < 14) { met = 8.0; }
		else if(speedmph >= 14 && speedmph < 16) { met = 10.0; }
		else if(speedmph >= 16 && speedmph <= 20) { met = 12.0; }
		else{ met = 16.0; }
		
		kcal = met*weight*hours;

	
		// TODO - SLUTT
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		double secs;
		double speed;
		
		// TODO - START
	
			secs = this.totalTime();
			
			speed = this.averageSpeed(); //km/h
			speed = speed*1000/3600; //m/s
			
			totalkcal = kcal(weight, (int)secs, speed);
			
		
		
		// TODO - SLUTT
			
		return totalkcal;
	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START
		
		DecimalFormat df2 = new DecimalFormat("#.##");
		
		int time = totalTime();
		String t = GPSUtils.formatTime(time);
		double dis = totalDistance()/1000;
		double ele = totalElevation();
		double ms = maxSpeed();
		double as = averageSpeed();
		double kc = totalKcal(WEIGHT);
		 
		
		
		System.out.println("Total Time     :    "+t);
		System.out.println("Total distance :      "+df2.format(dis)+" km");
		System.out.println("Total elevation:      "+df2.format(ele)+" m");
		System.out.println("Max speed      :      "+df2.format(ms)+" km/t");
		System.out.println("Average speed  :      "+df2.format(as)+" km/t");
		System.out.println("Energy         :      " + df2.format(kc)+" kcal");
		System.out.println("==============================================");

		// TODO - SLUTT

	}
	public String[] displayarray() {
		DecimalFormat df2 = new DecimalFormat("#.##");

		
		int time = totalTime();
		String t = GPSUtils.formatTime(time);
		double dis = totalDistance()/1000;
		double ele = totalElevation();
		double ms = maxSpeed();
		double as = averageSpeed();
		double kc = totalKcal(WEIGHT);
		
		String[] DisplayArray = new String[6];  
		DisplayArray[0] = ("Total Time         :  "+t);
		DisplayArray[1] = ("Total distance   :    "+df2.format(dis)+" km");
		DisplayArray[2] = ("Total elevation   :    "+df2.format(ele)+" m");
		DisplayArray[3] = ("Max speed         :    "+df2.format(ms)+" km/t");
		DisplayArray[4] = ("Average speed  :    "+df2.format(as)+" km/t");
		DisplayArray[5] = ("Energy               :    " + df2.format(kc)+" kcal");
		
		return DisplayArray;
	
	}
	

}
