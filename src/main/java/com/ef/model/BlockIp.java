package com.ef.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Edward P. Legaspi
 * 
 *         Storage for IP appearance in log with count on a given date range.
 */
@Entity
@Table(name = "block_ip")
public class BlockIp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ip")
	private String ip;

	@Column(name = "ip_count")
	private Long count;

	@Column(name = "reason")
	private String reason;

	public BlockIp() {

	}

	public BlockIp(String ip, Long count) {
		this.ip = ip;
		this.count = count;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "BlockIp [ip=" + ip + ", count=" + count + ", reason=" + reason + "]";
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
