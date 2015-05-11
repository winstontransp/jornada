DELIMITER $$

CREATE PROCEDURE sp_level_1(OUT p_message VARCHAR(255), OUT p_status TINYINT, OUT p_started_time DATETIME, OUT p_stopped_time DATETIME, 
	OUT p_level TINYINT, OUT p_id_initial INT(11), OUT p_id_final INT(11), OUT p_affected_records INT(8))
BEGIN 
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
		GET DIAGNOSTICS CONDITION 1 @sqlstate = RETURNED_SQLSTATE, @errno = MYSQL_ERRNO, @text = MESSAGE_TEXT;
		SET @full_error = CONCAT("ERROR ", @errno, " (", @sqlstate, "): ", @text);
		SET p_message = CONCAT("SQLEXCEPTION na sp_level_1 - ", @full_error);
		SET p_status = -1;
		SET p_stopped_time = NOW();
		ROLLBACK;
	END;
    
	DECLARE EXIT HANDLER FOR SQLWARNING 
    BEGIN 
		GET DIAGNOSTICS CONDITION 1 @sqlstate = RETURNED_SQLSTATE, @errno = MYSQL_ERRNO, @text = MESSAGE_TEXT;
		SET @full_error = CONCAT("ERROR ", @errno, " (", @sqlstate, "): ", @text);
		SET p_message = CONCAT("SQLWARNING na sp_level_1 - ", @full_error);
		SET p_status = -1;
		SET p_stopped_time = NOW();
		ROLLBACK;
	END;
    
	SET p_message = 'sp_level_1 run ok';
    SET p_level = 1;
    SET p_started_time = NOW();

	select min(IIRTN_ID), max(IIRTN_ID), count(IIRTN_ID) 
      into p_id_initial, p_id_final, p_affected_records 
      from returnmessage_iirtn
	 where proc is null;
        
    START TRANSACTION;

	INSERT INTO returnmessage 
		select IIRTN_ID, IIRTN_MctAddress, IIRTN_MacroNumber, IIRTN_Latitude, IIRTN_Longitude, IIRTN_PositionTime, IIRTN_Landmark, IIRTN_Text, NULL, NULL
          from returnmessage_iirtn
         where proc is null;
    
	UPDATE returnmessage_iirtn SET proc = 1 WHERE proc is null;

	INSERT INTO positionhistory
		select IIPOS_ID, IIPOS_MctAddress, IIPOS_Latitude, IIPOS_Longitude, IIPOS_TimePosition, IIPOS_Landmark, NULL, NULL
          from positionhistory_iipos
         where proc is null;
    
	UPDATE positionhistory_iipos SET proc = 1 WHERE proc is null;

	SET p_status = 1;
    
    COMMIT;

    SET p_stopped_time = NOW();
END $$ 

DELIMITER ;
