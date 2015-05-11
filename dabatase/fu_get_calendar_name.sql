DELIMITER $$

CREATE FUNCTION get_calendar_name(data_calendar DATE, calendar_type INT(1)) RETURNS VARCHAR(20)
BEGIN
DECLARE v_order_name VARCHAR(20) DEFAULT NULL;
DECLARE v_calendar_type_name VARCHAR(10) DEFAULT NULL;

CASE calendar_type
    WHEN 2 THEN SET v_calendar_type_name = "Bimestre";
    WHEN 3 THEN SET v_calendar_type_name = "Trimestre";
    WHEN 6 THEN SET v_calendar_type_name = "Semestre";
END CASE;

IF v_calendar_type_name IS NOT NULL THEN
	SELECT concat(ordem_nome, " ", v_calendar_type_name) 
	  FROM ordem 
	 WHERE id=ceiling(month(data_calendar) / calendar_type) 
	 INTO v_order_name;
END IF;

RETURN v_order_name;
END $$

DELIMITER ;