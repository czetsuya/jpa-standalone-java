DROP TABLE IF EXISTS access_log;
DROP TABLE IF EXISTS block_ip;
DROP DATABASE IF EXISTS wallethub_exer;
DROP USER IF EXISTS 'wallethub'@'localhost';

CREATE DATABASE wallethub_exer;
USE wallethub_exer;

CREATE USER 'wallethub'@'localhost' IDENTIFIED BY 'wallethub';
GRANT ALL PRIVILEGES ON wallethub_exer.* TO 'wallethub'@'localhost';

CREATE TABLE access_log (
	id INT8 UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	log_date TIMESTAMP,
	ip VARCHAR(50),
	request VARCHAR(50),
	status VARCHAR(50),
	user_agent VARCHAR(255)
);

CREATE TABLE block_ip (
	id INT8 UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	ip VARCHAR(50),
	ip_count INT8,
	reason VARCHAR(255)
);