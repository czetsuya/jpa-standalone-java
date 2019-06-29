--Exer SQL 1
SELECT x.* FROM
	(SELECT l.ip, COUNT(l.id) AS countip
	FROM wallethub_exer.access_log AS l
	WHERE log_date BETWEEN '2017-01-01.13:00:00' 
	AND '2017-01-01.14:00:00'
GROUP BY ip) AS x
WHERE x.countip > 100;

--Version 2
SELECT ip, count(id) 
FROM wallethub_exer.access_log
WHERE log_date BETWEEN '2017-01-01.13:00:00' 
	AND '2017-01-01.14:00:00'
GROUP BY ip
HAVING COUNT(id)>100;

--Exer SQL 2
SELECT * FROM wallethub_exer.access_log WHERE ip='192.168.11.231';

--Use for testing
SELECT * 
FROM wallethub_exer.access_log 
WHERE ip = '192.168.11.231' 
	AND log_date BETWEEN '2017-01-01.15:00:00' AND '2017-01-01.15:59:59';

SELECT * FROM wallethub_exer.access_log;

--startDate=2017-01-01.15:00:00 --duration=hourly --threshold=200
SELECT x.* FROM
	(SELECT l.ip, COUNT(l.id) AS countip
	FROM wallethub_exer.access_log AS l
	WHERE log_date BETWEEN '2017-01-01.15:00:00' 
	AND '2017-01-01.15:59:59'
GROUP BY ip) AS x
WHERE x.countip > 200;

--startDate=2017-01-01.00:00:00 --duration=daily --threshold=500
SELECT x.* FROM
	(SELECT l.ip, COUNT(l.id) AS countip
	FROM wallethub_exer.access_log AS l
	WHERE log_date BETWEEN '2017-01-01.00:00:00' 
	AND '2017-01-01.23:59:59'
GROUP BY ip) AS x
WHERE x.countip > 500;

SELECT * FROM block_ip;
