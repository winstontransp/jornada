-- update returnmessage_iirtn set proc = null;
-- update positionhistory_iipos set proc = null;
update returnmessage set jornada_id = null, proc = null;
-- update positionhistory set jornada_id = null, proc = null;
-- truncate table positionhistory;
-- truncate table returnmessage;
truncate table execute_history;
truncate table execute_log;
truncate table critica_jornada;
truncate table calendario;
truncate table jornada;
truncate table jornada_descanso;

drop procedure if exists sp_level_2;

drop procedure if exists sp_level_2_1;
drop procedure if exists sp_level_2_2;
drop procedure if exists sp_level_2_3;

drop event if exists ev_exec_sp_level_2;

-- drop event if exists ev_exec_sp_level_1;

-- SHOW PROCESSLIST;
-- SET GLOBAL event_scheduler = ON;


-- CALL sp_level_2(@execMessage, @execStatus, @execStartedTime, @execStoppedTime, @execLevel, @idInitial, @idFinal, @affectedRecords);

-- SELECT ID, Mctaddress, MacroNumber, Text, PositionTime FROM returnmessage WHERE proc is null order by Mctaddress asc, PositionTime asc;