package com.broodcamp.model;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.broodcamp.utils.DateUtils;

/**
 * @author Edward P. Legaspi
 * 
 *         Entity class where access logs are stored.
 */
@Entity
@Table(name = "access_log")
@NamedQueries({ @NamedQuery(name = "AccessLog.filter", query = "SELECT new com.ef.model.BlockIp(ip, count(id))" //
		+ " FROM AccessLog" //
		+ " WHERE log_date BETWEEN :startDate" //
		+ " AND :endDate" //
		+ " GROUP BY ip" //
		+ " HAVING COUNT(id)>:threshold") })
public class AccessLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "log_date")
	private Date date;

	@Column(name = "ip", length = 50)
	private String ip;

	@Column(name = "request", length = 50)
	private String request;

	@Column(name = "status", length = 50)
	private String status;

	@Column(name = "user_agent", length = 255)
	private String userAgent;

	public AccessLog() {

	}

	public AccessLog(String[] tokens) {
		try {
			date = DateUtils.parse(tokens[0]);
		} catch (ParseException e) {
		}
		ip = tokens[1];
		request = tokens[2];
		status = tokens[3];
		userAgent = tokens[4];
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Override
	public String toString() {
		return "AccessLog [id=" + id + ", date=" + date + ", ip=" + ip + ", request=" + request + ", status=" + status + ", userAgent=" + userAgent + "]";
	}

}
