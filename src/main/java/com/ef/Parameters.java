package com.ef;

import java.text.ParseException;
import java.util.Date;

import com.ef.model.DurationEnum;
import com.ef.utils.DateUtils;

/**
 * @author Edward P. Legaspi
 * 
 * Command line parameters.
 */
public class Parameters {

	private String startDate;
	private DurationEnum duration;
	private int threshold;
	private String accessLog;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public DurationEnum getDuration() {
		return duration;
	}

	public void setDuration(DurationEnum duration) {
		this.duration = duration;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public Date getStartDateAsDate() throws ParseException {
		return DateUtils.parse(DateUtils.format2, startDate);
	}

	public String getAccessLog() {
		return accessLog;
	}

	public void setAccessLog(String accessLog) {
		this.accessLog = accessLog;
	}

}
