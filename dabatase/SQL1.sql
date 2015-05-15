 SELECT rm.ID as returnmessage_id,
    rm.MctAddress,
    rm.MacroNumber,
    m.nome as MacroName,
    rm.PositionTime,
    rm.jornada_id
FROM exporta.returnmessage rm
	inner join macro m on (rm.MacroNumber = m.codigo)	
	
order by MctAddress asc, PositionTime asc;


-- 2533
select count(j.id) as total_jornadas
from jornada j;

-- 2533
select count(j.id) as total_jornadas_com_criticas
from jornada j 
  inner join critica_jornada cj on j.id = cj.id
  inner join critica c on cj.critica_id = c.id;
  

select j.id as jornda_id, count(cj.id) as total_critica
from jornada j 
  inner join critica_jornada cj on j.id = cj.id
  -- inner join critica c on cj.critica_id = c.id
group by j.id 
	having count(cj.id) > 0
order by count(cj.id) desc;
    

select j.id as jornda_id, count(l.id) as total_log
from jornada j 
  inner join execute_log l on j.id = l.jornada_id
  -- inner join critica c on cj.critica_id = c.id
group by j.id 
	having count(l.id) > 1
order by count(l.id) desc;
    

select c.id as critica_id, c.nome as nome_critica, count(j.id) as total_jornadas
from jornada j 
  inner join critica_jornada cj on j.id = cj.id
  inner join critica c on cj.critica_id = c.id
  inner join Motorista m on j.motorista_id = m.id
  inner join Frota f on j.mctaddress = f.mct
group by c.id, c.nome 
order by count(j.id) desc;
  
  -- 1236
select count(cj.id)
from critica_jornada cj 
  inner join critica c on cj.critica_id = c.id
where c.id = 6 ;

select c.id as critica_id, c.nome as nome_critica, count(j.id) as total_jornadas
from critica_jornada cj 
  inner join critica c on cj.critica_id = c.id
  inner join jornada j on cj.jornada_id = j.id
group by c.id, c.nome 
order by count(j.id) desc;

-- 1236
select count(j.id)
from jornada j 
  inner join critica_jornada cj on (j.id = cj.jornada_id)
  inner join critica c on cj.critica_id = c.id
where c.id = 6 ;

-- 2533    
SELECT count(j.ID) as count_jornada
FROM jornada j;

-- 2496    
SELECT count(j.ID) as count_jornada
FROM exporta.jornada j 
	inner join Motorista m 
		on (j.motorista_id = m.id)
	inner join Frota f
		on (j.mctaddress = f.mct);
        
  
  
  
  