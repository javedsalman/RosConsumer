package eu.arrowhead.application.skeleton.provider.connectzwaveadapter;

public class RunPythonScript {

	public static void main(String a[]) {
		try {

			String root = System.getProperty("user.dir");
			String filepath = "\\target\\classes\\SwitchPlug.py";
			String abspath = root + filepath;
			System.out.println("the path is : " + abspath);
			String deviceId2 = "2";
			String deviceId3 = "3";
			String switchPlug = "0";
			ProcessBuilder pb2 = new ProcessBuilder("py ", abspath, "" + deviceId2, "" + switchPlug);
			ProcessBuilder pb3 = new ProcessBuilder("py ", abspath, "" + deviceId3, "" + switchPlug);

			/*
			 * ProcessBuilder pb = new ProcessBuilder("py ",
			 * "C:\\Users\\javsal\\Documents\\GitHub\\client-skeleton-4.4-java-spring\\application-skeleton-provider\\SwitchPlug.py",
			 * "" + deviceId, "" + switchPlug);
			 */

			Process p2 = pb2.start();
			Process p3 = pb3.start();

			/*
			 * BufferedReader in = new BufferedReader(new
			 * InputStreamReader(p.getInputStream())); String response = in.readLine();
			 * double responseValue = Double.parseDouble(response);
			 * System.out.println("Plug Response is : " + responseValue);
			 */

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
