package com.broodcamp;

import com.broodcamp.model.DurationEnum;

/**
 * @author Edward P. Legaspi <czetsuya@gmail.com>
 * 
 *         The main runnable class.
 */
public class Parser {

	public static final String LOG_FILE = "access.log";

	public static void main(String[] args) {
		
		String errString = "Usage: " + "\njava -cp \"parser.jar\" com.broodcamp.Parser --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100 --accesslog=/tmp/access.log"
				+ "\njava -cp \"parser.jar\" com.broodcamp.Parser --startDate=2017-01-01.13:00:00 --duration=daily --threshold=250 --accesslog=/tmp/access.log";

		boolean error = true;
		if (args.length == 4) {
			try {
				Parameters params = validateArgs(args);

				LogProcessorService logProcessorService = new LogProcessorService();
				logProcessorService.processLogs(params);
				error = false;

			} catch (NumberFormatException e) {
				System.out.println("Error: Invalid number format");

			} catch (IllegalArgumentException | NullPointerException e) {
				System.out.println("Error: Invalid duration value [hourly, daily]");
			}
			
		} else {
			System.out.println("Error: Invalid no of arguments");
		}

		if (error) {
			System.out.println(errString);
		} else {
			System.out.println("Finished");
		}

		System.exit(0);
	}

	/**
	 * Validates the arguments.
	 * 
	 * @param args program arguments
	 * @return
	 */
	private static Parameters validateArgs(String[] args) {
		
		System.out.println("Validating the arguments");
		Parameters parameters = new Parameters();

		for (String arg : args) {
			String keyValue[] = arg.split("=");

			if (keyValue[0].equals("--startDate")) {
				parameters.setStartDate(keyValue[1]);

			} else if (keyValue[0].equals("--duration")) {
				String duration = keyValue[1].toUpperCase();
				parameters.setDuration(DurationEnum.valueOf(duration));

			} else if (keyValue[0].equals("--threshold")) {
				parameters.setThreshold(Integer.parseInt(keyValue[1]));

			} else if (keyValue[0].equals("--accesslog")) {
				parameters.setAccessLog(keyValue[1]);
			}
		}

		return parameters;
	}
}
