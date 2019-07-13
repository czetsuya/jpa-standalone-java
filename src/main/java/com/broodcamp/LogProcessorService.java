package com.broodcamp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.broodcamp.model.AccessLog;
import com.broodcamp.model.BlockIp;
import com.broodcamp.model.DurationEnum;
import com.broodcamp.persistence.PersistenceManager;
import com.broodcamp.utils.DateUtils;

/**
 * @author Edward P. Legaspi
 * 
 *         Service class that do the reading, processing and saving of log file.
 */
public class LogProcessorService {

	/**
	 * Process the logs.
	 * 
	 * @param params program arguments
	 */
	public void processLogs(Parameters params) {

		System.out.println("Begin processing file");
		try {
			EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

			readFromFileAndSaveToDb(em, params.getAccessLog());
			filterIPAndSaveToDb(em, params);

			em.close();
			PersistenceManager.INSTANCE.close();

		} catch (Exception e) {
			System.out.println("Failed reading from file " + e.getMessage());
		}
	}

	/**
	 * Read, parse from a file and save to database.
	 * 
	 * @param em
	 * 
	 * @param logFile physical access log file
	 * @throws IOException file is not found
	 */
	private void readFromFileAndSaveToDb(EntityManager em, String logFile) throws IOException {

		System.out.println("Read from file and save to db");
		File file = null;
		if (logFile != null) {
			file = new File(logFile);

		} else {
			file = new File(getClass().getClassLoader().getResource(Parser.LOG_FILE).getFile());
		}

		em.getTransaction().begin();

		try (FileReader reader = new FileReader(file); BufferedReader br = new BufferedReader(reader)) {
			String line;
			while ((line = br.readLine()) != null) {
				// save to db
				final String[] tokens = line.split(Pattern.quote("|"));
				AccessLog accessLog = new AccessLog(tokens);
				em.persist(accessLog);
			}
		}

		em.getTransaction().commit();
	}

	/**
	 * Filter the log database with a given criteria and write the output into
	 * another database table.
	 * 
	 * @param em
	 * 
	 * @param params filter criteria
	 * @throws ParseException invalid date format
	 */
	@SuppressWarnings("unchecked")
	private void filterIPAndSaveToDb(EntityManager em, Parameters params) throws ParseException {

		System.out.println("Filter by IP and save to db");
		Date startDate = params.getStartDateAsDate();
		Date endDate = DateUtils.addHours(startDate, 1);
		String blockReason = "Hourly threshold crossed";
		if (params.getDuration().equals(DurationEnum.DAILY)) {
			blockReason = "Daily threshold crossed";
			endDate = DateUtils.addHours(startDate, 24);
		}

		System.out.println(String.format("start=%s, end=%s, threshold=%s", startDate, endDate, params.getThreshold()));

		// filter database

		em.getTransaction().begin();
		Query query = em.createNamedQuery("AccessLog.filter") //
				.setParameter("startDate", startDate) //
				.setParameter("endDate", endDate) //
				.setParameter("threshold", Long.valueOf(params.getThreshold()));
		List<BlockIp> result = query.getResultList();

		if (result != null) {
			System.out.println("Record found=" + result.size());
			for (BlockIp blockIp : result) {
				blockIp.setReason(blockReason);
				System.out.println(blockIp.toString());
				em.persist(blockIp);
			}
		}

		em.getTransaction().commit();
	}
}
