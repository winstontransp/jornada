DELIMITER $$

CREATE EVENT ev_exec_sp_level_2
ON SCHEDULE EVERY 1 HOUR
STARTS CURRENT_TIMESTAMP
DO
BEGIN

	CALL sp_level_2(@execMessage, @execStatus, @execStartedTime, @execStoppedTime, @execLevel, @idInitial, @idFinal, @affectedRecords);
    
	INSERT INTO execute_history(execution_message, execution_status, execution_started, execution_stopped, execution_level, id_initial, id_final, affected_records) 
    VALUES (@execMessage, @execStatus, @execStartedTime, @execStoppedTime, @execLevel, @idInitial, @idFinal, @affectedRecords);

END $$

DELIMITER ;
