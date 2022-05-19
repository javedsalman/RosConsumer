package eu.arrowhead.application.skeleton.provider.connectzwaveadapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;

@Component
public class PlugEnergyReader {
	public String readEnergyKWH() {
		try {

			String root = System.getProperty("user.dir");
			String filepath = "\\target\\classes\\PlugEnergy.py";
			String abspath = root + filepath;
			System.out.println("the path is : " + abspath);
			String deviceId2 = "2";
			String deviceId3 = "3";

			ProcessBuilder pb2 = new ProcessBuilder("py ", abspath, "" + deviceId2);
			ProcessBuilder pb3 = new ProcessBuilder("py ", abspath, "" + deviceId3);
			Process p2 = pb2.start();

			BufferedReader in2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
			String plugEnergy2 = in2.readLine();

			Process p3 = pb3.start();

			BufferedReader in3 = new BufferedReader(new InputStreamReader(p3.getInputStream()));
			String plugEnergy3 = in3.readLine();

			double plugEnergyTemp = (Double.parseDouble(plugEnergy2)) + (Double.parseDouble(plugEnergy3));
			System.out.println("PlugEnergy of DevID-2 : " + plugEnergy2);
			System.out.println("PlugEnergy of DevID-3 : " + plugEnergy3);

			String plugEnergy = Double.toString(plugEnergyTemp);
			////////////////////////////////////////////////////////////////////////

			System.out.println("writing data to file");
			File file = new File(root + "\\EnergyConsumption.csv");
			FileWriter outputfile = new FileWriter(file, true);
			CSVWriter writer = new CSVWriter(outputfile);
			/*
			 * String[] header = { "TimeStamp", "EnergyConsumptionKWH" };
			 * writer.writeNext(header);
			 */

			final long MSEC_SINCE_EPOCH = System.currentTimeMillis();

			Date instant = new Date(MSEC_SINCE_EPOCH);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String time = sdf.format(instant);

			String[] data = { time, plugEnergy };
			writer.writeNext(data);
			writer.close();

			return plugEnergy;

		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

}