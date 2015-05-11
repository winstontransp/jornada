CREATE TABLE positionhistory(
  ID bigint(20) NOT NULL,
  MctAddress int(11) NOT NULL,
  Latitude decimal(8,6) DEFAULT NULL,
  Longitude decimal(8,6) DEFAULT NULL,
  TimePosition datetime DEFAULT NULL,
  Landmark varchar(255) COLLATE latin1_general_ci DEFAULT NULL,
  jornada_id bigint(20) DEFAULT NULL,
  proc tinyint(1) DEFAULT '0',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;
