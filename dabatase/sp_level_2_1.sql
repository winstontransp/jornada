DELIMITER $$

CREATE PROCEDURE sp_level_2_1(
	INOUT ID_loop BIGINT(20), 
	INOUT MctAddress_loop INT(11), 
	INOUT MacroNumber_loop TINYINT, 
	INOUT Text_loop VARCHAR(255), 
	INOUT PositionTime_loop DATETIME, 

	INOUT v_mctaddress INT(11), 
	INOUT v_positionTime DATETIME, 
	INOUT v_macroNumber TINYINT, 
	INOUT v_jornadaId BIGINT(20), 
	INOUT v_statusJornada TINYINT,  
	INOUT v_mctaddress_last INT(11), 
	INOUT v_macroNumber_last TINYINT,
    INOUT v_positionTime_last DATETIME,

	INOUT v_dataJornada DATE, 
	INOUT v_inicioJornada DATETIME, 
	INOUT v_terminoJornada DATETIME, 
	INOUT v_tempoJornada VARCHAR(10), 
	INOUT v_inicioDirecao DATETIME, 
	INOUT v_terminoDirecao DATETIME, 
	INOUT v_tempoDirecao TIME, 
	INOUT v_inicioDescanso DATETIME, 
	INOUT v_terminoDescanso DATETIME, 
	INOUT v_tempoDescanso TIME, 
	INOUT v_inicioRefeicao1 DATETIME, 
	INOUT v_terminoRefeicao1 DATETIME, 
	INOUT v_tempoRefeicao1 TIME, 
	INOUT v_inicioRefeicao2 DATETIME, 
	INOUT v_terminoRefeicao2 DATETIME, 
	INOUT v_tempoRefeicao2 TIME, 
	INOUT v_inicioForaServico DATETIME,
	INOUT v_terminoForaServico DATETIME,
	INOUT v_tempoForaServico TIME, 
	INOUT v_inicioInterjornada DATETIME, 
	INOUT v_terminoInterjornada DATETIME, 
	INOUT v_tempoInterjornada TIME, 
	INOUT v_inicioCarregamento DATETIME, 
	INOUT v_terminoCarregamento DATETIME, 
	INOUT v_tempoCarregamento TIME, 
	INOUT v_inicioDescarregamento DATETIME, 
	INOUT v_terminoDescarregamento DATETIME, 
	INOUT v_tempoDescarregamento TIME,
    INOUT v_tempoDescansoTotal TIME
)
BEGIN 
	DECLARE v_log_subject VARCHAR(40);
	DECLARE v_log_message VARCHAR(255);

	-- Início de Jornada
	IF v_macroNumber = 1 THEN
		SET v_inicioJornada = v_positionTime;
	END IF;

	-- Início de Direcao (também previne vários inícios de direção na mesma jornada. Considera somente o primeiro)
	IF v_macroNumber = 15 and v_inicioDirecao is null THEN
		SET v_inicioDirecao = v_positionTime;
	END IF;

	-- Fim de Direcao
	IF v_macroNumber = 16 THEN
		SET v_terminoDirecao = v_positionTime;
		
		IF v_inicioDirecao is not null THEN
			SET v_tempoDirecao = TIMEDIFF(v_terminoDirecao, v_inicioDirecao);
		END IF;
		
	END IF;
	
	-- Intervalo para refeicao
	IF v_macroNumber = 2 THEN 
	
		IF v_inicioRefeicao1 is null THEN
			SET v_inicioRefeicao1 = v_positionTime;
		ELSE
			SET v_inicioRefeicao2 = v_positionTime;
		END IF;
		
	END IF;

	-- Reinicio de Direcao
	IF v_macroNumber = 3 THEN 
	
		-- Se estava em intervalo para Refeicao
		IF v_macroNumber_last = 2 THEN 
			
			IF v_inicioRefeicao2 is null AND v_terminoRefeicao1 is null THEN
				SET v_terminoRefeicao1 = v_positionTime;
				SET v_tempoRefeicao1 = TIMEDIFF(v_terminoRefeicao1, v_inicioRefeicao1);
			ELSEIF v_inicioRefeicao2 is not null AND v_terminoRefeicao2 is null THEN 
				SET v_terminoRefeicao2 = v_positionTime;
				SET v_tempoRefeicao2 = TIMEDIFF(v_terminoRefeicao2, v_inicioRefeicao2);
			END IF;
			
		END IF;
		
		-- Se estava em intervalo de Descanso
		IF v_macroNumber_last = 11 and v_terminoDescanso is null THEN 
			SET v_terminoDescanso = v_positionTime;
			
			SET v_tempoDescanso = TIMEDIFF(v_terminoDescanso, v_inicioDescanso);
            
            insert into jornada_descanso (inicio_descanso, termino_descanso, tempo_descanso, jornada_id) 
            values (v_inicioDescanso, v_terminoDescanso, v_tempoDescanso, v_jornadaId);
            
			SET v_tempoDescansoTotal = ADDTIME(v_tempoDescansoTotal, v_tempoDescanso);
            
            SET v_inicioDescanso = NULL;
            SET v_terminoDescanso = NULL;
            SET v_tempoDescanso = NULL;
		END IF;        
		
	END IF;
	
	-- Fim de Jornada
	IF v_macroNumber = 4 THEN
		SET v_terminoJornada = v_positionTime;
		SET v_tempoJornada = concat(time_format(timediff(v_terminoJornada, v_inicioJornada), "%H:%i"), " ");
	END IF;

	-- Início de Carregamento
	IF v_macroNumber = 5 THEN
		SET v_inicioCarregamento = v_positionTime;

		-- Se estava em intervalo para Refeicao
		IF v_macroNumber_last = 2 THEN 
			
			IF v_inicioRefeicao2 is null THEN
				SET v_terminoRefeicao1 = v_positionTime;
				SET v_tempoRefeicao1 = TIMEDIFF(v_terminoRefeicao1, v_inicioRefeicao1);
			ELSE
				SET v_terminoRefeicao2 = v_positionTime;
				SET v_tempoRefeicao2 = TIMEDIFF(v_terminoRefeicao2, v_inicioRefeicao2);
			END IF;
			
		END IF;
		
		-- Se estava em intervalo de Descanso
		IF v_macroNumber_last = 11 and v_terminoDescanso is null THEN 
			SET v_terminoDescanso = v_positionTime;
			
			SET v_tempoDescanso = TIMEDIFF(v_terminoDescanso, v_inicioDescanso);
            
            insert into jornada_descanso (inicio_descanso, termino_descanso, tempo_descanso, jornada_id) 
            values (v_inicioDescanso, v_terminoDescanso, v_tempoDescanso, v_jornadaId);
            
			SET v_tempoDescansoTotal = ADDTIME(v_tempoDescansoTotal, v_tempoDescanso);
            
            SET v_inicioDescanso = NULL;
            SET v_terminoDescanso = NULL;
            SET v_tempoDescanso = NULL;
		END IF;        
        
	END IF;

	-- Termino de Carregamento
	IF v_macroNumber = 6 THEN
		SET v_terminoCarregamento = v_positionTime;
		SET v_tempoCarregamento = TIMEDIFF(v_terminoCarregamento, v_inicioCarregamento);
	END IF;
	
	-- Início de Descarregamento
	IF v_macroNumber = 7 THEN
		SET v_inicioDescarregamento = v_positionTime;

		-- Se estava em intervalo para Refeicao
		IF v_macroNumber_last = 2 THEN 
			
			IF v_inicioRefeicao2 is null THEN
				SET v_terminoRefeicao1 = v_positionTime;
				SET v_tempoRefeicao1 = TIMEDIFF(v_terminoRefeicao1, v_inicioRefeicao1);
			ELSE
				SET v_terminoRefeicao2 = v_positionTime;
				SET v_tempoRefeicao2 = TIMEDIFF(v_terminoRefeicao2, v_inicioRefeicao2);
			END IF;
			
		END IF;
		
		-- Se estava em intervalo de Descanso
		IF v_macroNumber_last = 11 and v_terminoDescanso is null THEN 
			SET v_terminoDescanso = v_positionTime;
			
			SET v_tempoDescanso = TIMEDIFF(v_terminoDescanso, v_inicioDescanso);
            
            insert into jornada_descanso (inicio_descanso, termino_descanso, tempo_descanso, jornada_id) 
            values (v_inicioDescanso, v_terminoDescanso, v_tempoDescanso, v_jornadaId);
            
			SET v_tempoDescansoTotal = ADDTIME(v_tempoDescansoTotal, v_tempoDescanso);
            
            SET v_inicioDescanso = NULL;
            SET v_terminoDescanso = NULL;
            SET v_tempoDescanso = NULL;
		END IF;        
        
	END IF;

	-- Termino de Descarregamento
	IF v_macroNumber = 8 THEN
		SET v_terminoDescarregamento = v_positionTime;
		SET v_tempoDescarregamento = TIMEDIFF(v_terminoDescarregamento, v_inicioDescarregamento);
	END IF;

	-- Descanso
	IF v_macroNumber = 11 THEN
		SET v_inicioDescanso = v_positionTime;
	END IF;

	SET v_macroNumber_last = v_macroNumber;
	SET v_mctaddress_last = v_mctaddress;
    SET v_positionTime_last = v_positionTime;
END $$ 

DELIMITER ;
