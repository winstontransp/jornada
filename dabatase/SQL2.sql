select count(1) from jornada; -- 581
select count(1) from jornada where status_jornada = 0; -- 215
select count(1) from jornada where status_jornada = 1; -- 366

select rm.ID, rm.MctAddress, rm.MacroNumber, rm.Latitude, rm.Longitude, rm.PositionTime, rm.Landmark, rm.Text, rm.jornada_id, rm.proc
from returnmessage rm
order by MctAddress asc, PositionTime asc;

select rm.ID, rm.MctAddress, rm.MacroNumber, m.nome, rm.Latitude, rm.Longitude, rm.PositionTime, rm.Landmark, rm.Text, rm.jornada_id, rm.proc
from returnmessage rm
	inner join macro m on (rm.MacroNumber = m.codigo)		
order by MctAddress asc, PositionTime asc;

-- CALL sp_prepare_level_2(@execMessage, @execStatus, @execStartedTime, @execStoppedTime, @execLevel, @idInitial, @idFinal, @affectedRecords);

select rm.ID, rm.MctAddress, rm.MacroNumber, m.nome, rm.Latitude, rm.Longitude, rm.PositionTime, rm.Landmark, rm.Text, rm.jornada_id, rm.proc
from returnmessage rm
	inner join macro m on (rm.MacroNumber = m.codigo)		
where jornada_id = 9
order by id asc;


 SELECT rm.ID as returnmessage_id,
    rm.MctAddress,
    rm.Latitude,
    rm.Longitude,
    rm.PositionTime,
    rm.MacroNumber,
    m.nome as MacroName,
    rm.Landmark,
    rm.jornada_id
FROM exporta.returnmessage rm
	inner join macro m on (rm.MacroNumber = m.codigo)		
where proc = 1
order by MctAddress asc, PositionTime asc;

select rm.ID, rm.MctAddress, rm.MacroNumber, m.nome, rm.Latitude, rm.Longitude, rm.PositionTime, rm.Landmark, rm.Text, rm.jornada_id, rm.proc
from returnmessage rm
	inner join macro m on (rm.MacroNumber = m.codigo)		
where rm.MctAddress = 1122539
order by MctAddress asc, PositionTime asc;

select datediff('2015-03-12 00:12:14', '2015-03-11 16:37:26');
select timediff('2015-03-13 00:12:14', '2015-03-11 16:37:26');


-- update returnmessage set proc = 0 where mctaddress = 1075061 and proc is null;

-- '2015-03-03 06:03:36' '2015-03-04 06:38:32'
-- 1075074

-- select * from returnmessage where id = 4541;
-- 	4541	1122539	1	-23.010278	-44.317778	2015-03-12 07:57:37	0.13Km NE de PETROBRAS ANGRA, RJ - Brasil	_200113		


SHOW PROCESSLIST;

SET GLOBAL event_scheduler = ON;



SELECT id as motorista_id,
    matricula as motorista_matricula,
    nome as motorista_nome,
    operacao as operacao_id,
    if (turno = 'V', '24 Horas', 'Normal') as turno
FROM motorista;
