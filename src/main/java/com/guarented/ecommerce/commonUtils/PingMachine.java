package com.guarented.ecommerce.commonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PingMachine {

	public String[] pingMachine(String machine) {
		String[] returnValues = new String[3];

		//String[] uri = requestURL.split("/");
		//String[] uri1 = uri[2].split(":");
		//String ip = uri1[0];
		//System.out.println("Machine ip: " + ip);
		//returnValues[0] = ip;
		returnValues[0] = machine;
		String pingResult = "";
		String output;
		String machineUp;

		if (System.getProperty("os.name").contains("Windows")) {

			String pingCmd = "ping " + machine; // windows
			
			try {
				Process p = Runtime.getRuntime().exec(pingCmd);

				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((output = br.readLine()) != null) {
					pingResult += output;
				}
				br.close();
				System.out.println("Ping Result : " + pingResult);
				if (pingResult.contains("bytes=")) {
					machineUp = "Yes";
				} else {
					machineUp = "No";
				}

				String[] output1 = pingResult.split("%");
				System.out.println(output1[0]);
				String[] output2 = output1[0].split("\\(");
				String loss_percent = output2[1];
				System.out.println("loss_percent : "+loss_percent);
				returnValues[1] = machineUp;
				returnValues[2] = loss_percent;
				System.out.println("machineUp : " + machineUp);
			} catch (IOException e) {
				System.out.println(e);
			}
		} else if (System.getProperty("os.name").contains("Linux")) {
			String pingCmd = "ping " + machine + " -c 4"; // linux
			
			try {
				Process p = Runtime.getRuntime().exec(pingCmd);

				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while ((output = br.readLine()) != null) {
					pingResult += output;
				}
				br.close();
				System.out.println("Ping Result : " + pingResult);
				if (pingResult.contains("bytes")) {
					machineUp = "Yes";
				} else {
					machineUp = "No";
				}

				String[] output1 = pingResult.split("---");
				System.out.println(output1[2]);
				String[] output2 = output1[2].split("\\%");
				String[] output3 = output2[0].split(" ");
				String loss_percent = output3[5];
				System.out.println("loss_percent : "+loss_percent);
				returnValues[1] = machineUp;
				returnValues[2] = loss_percent;
				System.out.println("machineUp : " + machineUp);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return returnValues;
	}
	
}
