DELIMITER $$

CREATE PROCEDURE sp_level_2_2(
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
-- 	INOUT v_inicioDirecao DATETIME, 
-- 	INOUT v_terminoDirecao DATETIME, 
-- 	INOUT v_tempoDirecao TIME, 
-- 	INOUT v_inicioDescanso DATETIME, 
-- 	INOUT v_terminoDescanso DATETIME, 
-- 	INOUT v_tempoDescanso TIME, 
-- 	INOUT v_tempoDescansoTotal TIME, 
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
	INOUT v_tempoDescarregamento TIME
)
BEGIN 
	DECLARE v_log_subject VARCHAR(40);
	DECLARE v_log_message VARCHAR(255);

	DECLARE v_diasForaServico BIGINT(20);
    DECLARE v_diasInterjornada BIGINT(20);

	SET v_statusJornada = 1;

/*
	-- Valida o Início da Direcao
	IF v_inicioDirecao is null and v_terminoDirecao is not null THEN
		SET v_statusJornada = 0;
		SET v_log_subject = "Início Direção Faltante";
		SET v_log_message = "Jornada sem Início de Direção";
		INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, v_jornadaId, ID_loop);
	END IF;

	-- Valida se deve criar um Término da Direcao automático
	IF v_terminoJornada is not null and v_terminoDirecao is null and v_inicioDirecao is not null THEN
		SET v_statusJornada = 0;
		SET v_terminoDirecao = v_terminoJornada;
		SET v_log_subject = "Criado Término Direção";
		SET v_log_message = "O Término de Direção foi criado usando o valor do Término de Jornada";
		INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, v_jornadaId, ID_loop);
	END IF;
*/

	IF v_inicioRefeicao1 is not null and v_terminoRefeicao1 is null THEN
		SET v_statusJornada = 0;
		SET v_tempoRefeicao1 = null;
		SET v_tempoRefeicao2 = null;
		SET v_log_subject = "Refeição 1 sem Reinício Jornada";
		SET v_log_message = "Parada para refeição sem Reinício de Jornada";
		INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, v_jornadaId, ID_loop);
	END IF;
	
	IF v_inicioRefeicao2 is not null and v_terminoRefeicao2 is null THEN
		SET v_statusJornada = 0;
		SET v_tempoRefeicao2 = null;
		SET v_log_subject = "Refeição 2 sem Reinício Jornada";
		SET v_log_message = "Segunda parada para refeição sem Reinício de Jornada";
		INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, v_jornadaId, ID_loop);
	END IF;

	-- Valida o Término da Jornada
	IF v_terminoJornada is null THEN
		SET v_statusJornada = 0;
		SET v_log_subject = "Jornada sem encerramento";
		SET v_log_message = "Esta jornada não teve uma macro Fim de Jornada";
		INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, v_jornadaId, ID_loop);
	END IF;

	-- Calcula o tempo da Interjornada            
	SET v_terminoInterjornada = v_inicioJornada;
	
	SELECT Max(rm.PositionTime) into v_inicioInterjornada
	 FROM returnmessage rm
	WHERE rm.MacroNumber = 4
	  AND rm.Mctaddress = v_mctaddress_last
	  AND rm.PositionTime < v_inicioJornada;

	IF v_inicioInterjornada is not null THEN
		SET v_diasInterjornada = DATEDIFF(v_terminoInterjornada, v_inicioInterjornada);
        
        IF v_diasInterjornada < 31 THEN
			SET v_tempoInterjornada = TIMEDIFF(v_terminoInterjornada, v_inicioInterjornada);
		ELSE
			SET v_statusJornada = 0;
			SET v_log_subject = "Interjornada maior 30 dias";
			SET v_log_message = concat("Tempo interjornada de ", v_inicioInterjornada, " até ", v_terminoInterjornada, " maior que 30 dias");
			INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, v_jornadaId, ID_loop);
		END IF;
    END IF;

	-- Calcula o tempo da fora de serviço
	SET v_inicioForaServico = v_terminoJornada;

	SELECT Min(rm.PositionTime) into v_terminoForaServico
	 FROM returnmessage rm
	WHERE rm.MacroNumber = 1
	  AND rm.Mctaddress = v_mctaddress_last
	  AND rm.PositionTime > v_terminoJornada;

	IF v_terminoForaServico is not null THEN
		SET v_diasForaServico = DATEDIFF(v_terminoForaServico, v_inicioForaServico);
        
        IF v_diasForaServico < 31 THEN
			SET v_tempoForaServico = TIMEDIFF(v_terminoForaServico, v_inicioForaServico);
		ELSE
			SET v_statusJornada = 0;
			SET v_log_subject = "Fora Serviço maior 30 dias";
			SET v_log_message = concat("Tempo fora de serviço de ", v_inicioForaServico, " até ", v_terminoForaServico, " maior que 30 dias");
			INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, v_jornadaId, ID_loop);
		END IF;
	ELSE
		SET v_diasForaServico = DATEDIFF(NOW(), v_inicioForaServico);
		
        IF v_diasForaServico < 31 THEN
			SET v_tempoForaServico = TIMEDIFF(NOW(), v_inicioForaServico);
		ELSE
			SET v_statusJornada = 0;
			SET v_log_subject = "Fora Serviço atual maior 30 dias";
			SET v_log_message = concat("Tempo fora de serviço de ", v_inicioForaServico, " até a data atual maior que 30 dias");
			INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, v_jornadaId, ID_loop);
		END IF;
        
    END IF;

/*
	-- Valida o Término da Direcao
	IF v_inicioDirecao is not null and v_terminoDirecao is null THEN
		SET v_statusJornada = 0;
		SET v_log_subject = "Término de Direção Faltante";
		SET v_log_message = "Não foi detectado o Término de Direção para esta Jornada";
		INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, v_jornadaId, ID_loop);
	ELSEIF v_tempoDirecao is not null THEN 
		-- Calcula o tempo de direcao
		-- Diferença entre inicio e fim de direção 
		-- Descontado dos tempos de: carregamento, descarregamento, descanso e refeicoes
		
        IF v_inicioDirecao < v_inicioCarregamento THEN
			SET v_tempoDirecao = subtime(v_tempoDirecao, ifnull(v_tempoCarregamento, '00:00:00'));
		END IF;
        
        IF v_inicioDirecao < v_inicioDescarregamento THEN
			SET v_tempoDirecao = subtime(v_tempoDirecao, ifnull(v_tempoDescarregamento, '00:00:00'));
		END IF;
        
        IF v_inicioDirecao < v_inicioDescanso THEN
			SET v_tempoDirecao = subtime(v_tempoDirecao, ifnull(v_tempoDescanso, '00:00:00'));
		END IF;
        
        IF v_inicioDirecao < v_inicioRefeicao1 THEN
			SET v_tempoDirecao = subtime(v_tempoDirecao, ifnull(v_temporefeicao1, '00:00:00'));
		END IF; 
        
        IF v_inicioDirecao < v_inicioRefeicao1 THEN
			SET v_tempoDirecao = subtime(v_tempoDirecao, ifnull(v_temporefeicao2, '00:00:00'));
		END IF;
        
	END IF;
*/

END $$ 

DELIMITER ;
