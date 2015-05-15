DELIMITER $$

CREATE PROCEDURE sp_level_2(OUT p_message VARCHAR(255), OUT p_status TINYINT, OUT p_started_time DATETIME, OUT p_stopped_time DATETIME, 
	OUT p_level TINYINT, OUT p_id_initial INT(11), OUT p_id_final INT(11), OUT p_affected_records INT(8))
BEGIN 
	DECLARE ID_loop BIGINT(20);
	DECLARE MctAddress_loop INT(11);
	DECLARE MacroNumber_loop TINYINT;
	DECLARE Text_loop VARCHAR(255);
	DECLARE PositionTime_loop DATETIME;
	
	DECLARE v_motorista_id BIGINT(20);
	DECLARE v_motorista_matricula BIGINT(7);
	DECLARE v_mctaddress INT(11);
	DECLARE v_positionTime DATETIME;
    DECLARE v_macroNumber TINYINT;
    DECLARE v_jornadaId BIGINT(20);
    
    DECLARE v_statusJornada TINYINT; 
    DECLARE v_log_subject VARCHAR(40);
    DECLARE v_log_message VARCHAR(255);    
    
    DECLARE v_mctaddress_last INT(11);
    DECLARE v_macroNumber_last TINYINT;
	DECLARE v_positionTime_last DATETIME;

    DECLARE v_ult_jornada_mctaddress_last INT(11);
    DECLARE v_ult_jornada_macroNumber_last TINYINT;
	DECLARE v_ult_jornada_positionTime_last DATETIME;
    
    DECLARE v_count_motorista BIGINT(20);

	DECLARE v_dataJornada DATE DEFAULT NULL;
	DECLARE v_inicioJornada DATETIME DEFAULT NULL;
	DECLARE v_terminoJornada DATETIME DEFAULT NULL;
	DECLARE v_tempoJornada VARCHAR(10) DEFAULT NULL;
--  DECLARE v_inicioDirecao DATETIME DEFAULT NULL;
--  DECLARE v_terminoDirecao DATETIME DEFAULT NULL;
--  DECLARE v_tempoDirecao TIME DEFAULT 0;
-- 	DECLARE v_inicioDescanso DATETIME DEFAULT NULL;
-- 	DECLARE v_terminoDescanso DATETIME DEFAULT NULL;
-- 	DECLARE v_tempoDescanso TIME DEFAULT 0;
	DECLARE v_inicioRefeicao1 DATETIME DEFAULT NULL;
	DECLARE v_terminoRefeicao1 DATETIME DEFAULT NULL;
	DECLARE v_tempoRefeicao1 TIME DEFAULT NULL;
	DECLARE v_inicioRefeicao2 DATETIME DEFAULT NULL;
	DECLARE v_terminoRefeicao2 DATETIME DEFAULT NULL;
	DECLARE v_tempoRefeicao2 TIME DEFAULT NULL;
    DECLARE v_inicioForaServico DATETIME DEFAULT NULL;
    DECLARE v_terminoForaServico DATETIME DEFAULT NULL;
	DECLARE v_tempoForaServico TIME DEFAULT NULL;
	DECLARE v_inicioInterjornada DATETIME DEFAULT NULL;
	DECLARE v_terminoInterjornada DATETIME DEFAULT NULL;
	DECLARE v_tempoInterjornada TIME DEFAULT NULL;
	DECLARE v_inicioCarregamento DATETIME DEFAULT NULL;
	DECLARE v_terminoCarregamento DATETIME DEFAULT NULL;
	DECLARE v_tempoCarregamento TIME DEFAULT NULL;
	DECLARE v_inicioDescarregamento DATETIME DEFAULT NULL;
	DECLARE v_terminoDescarregamento DATETIME DEFAULT NULL;
	DECLARE v_tempoDescarregamento TIME DEFAULT NULL;
--    DECLARE v_tempoDescansoTotal TIME DEFAULT NULL;
    DECLARE v_EOF TINYINT;
    DECLARE v_loop_count BIGINT(11);
    DECLARE v_loop_exit TINYINT;
	
    DECLARE v_list_LoopID VARCHAR(1000);
    
	DECLARE record_not_found INT DEFAULT 0;
	DECLARE cursor_id CURSOR FOR SELECT ID, Mctaddress, MacroNumber, Text, PositionTime FROM returnmessage WHERE proc is null order by Mctaddress asc, PositionTime asc;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET record_not_found = 1;
		
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
		GET DIAGNOSTICS CONDITION 1 @sqlstate = RETURNED_SQLSTATE, @errno = MYSQL_ERRNO, @text = MESSAGE_TEXT;
		SET @full_error = CONCAT("ERROR ", @errno, " (", @sqlstate, "): ", @text, " [ID = ", ID_loop, "]");
		SET p_message = CONCAT("SQLEXCEPTION sp_level_2 - ", @full_error);
		SET p_status = -1;
		SET p_stopped_time = NOW();
		ROLLBACK;
	END;
    
	SET p_message = 'sp_level_2 run ok';
    SET p_level = 2;
    SET p_started_time = NOW();

	select min(ID), max(ID), count(ID) 
      into p_id_initial, p_id_final, p_affected_records 
      from returnmessage
	 where proc is null;
     
    START TRANSACTION;
    
    SET p_status = 0;

	OPEN cursor_id;

	SET v_EOF = 0;

	FETCH cursor_id INTO ID_loop, MctAddress_loop, MacroNumber_loop, Text_loop, PositionTime_loop;

	IF record_not_found THEN 
		SET v_EOF = 1;
	END IF;    

	SET v_ult_jornada_mctaddress_last =  -1;
	SET v_ult_jornada_macroNumber_last =  -1;
	SET v_ult_jornada_positionTime_last = '1900-01-01';
		 
	all_id:LOOP
		IF v_EOF = 1 THEN 
			LEAVE all_id;
		END IF;        

		-- Reinicializa as variáveis de trabalho para a proxima jornada
        SET v_list_LoopID  = "";
		SET v_loop_exit = 0;
		SET v_macroNumber_last =  -1;
		SET v_positionTime_last = '1900-01-01';
--        SET v_tempoDescanso = 0;
--        SET v_tempoDescansoTotal = 0;
		SET v_motorista_id = NULL;
		SET v_motorista_matricula = NULL;
        SET v_inicioJornada = NULL;
        SET v_terminoJornada = NULL;
        SET v_tempoJornada = NULL;
--        SET v_inicioDirecao = NULL;
--        SET v_terminoDirecao = NULL;
--        SET v_tempoDirecao = NULL;
        SET v_inicioRefeicao1 = NULL;
        SET v_terminoRefeicao1 = NULL;
        SET v_tempoRefeicao1 = NULL;
        SET v_inicioRefeicao2 = NULL;
        SET v_terminoRefeicao2 = NULL;
        SET v_tempoRefeicao2 = NULL;
        SET v_inicioCarregamento = NULL;
        SET v_terminoCarregamento = NULL;
        SET v_tempoCarregamento = NULL;
        SET v_inicioDescarregamento = NULL;
        SET v_terminoDescarregamento = NULL;
        SET v_tempoDescarregamento = NULL;
		SET v_inicioInterjornada = NULL;
		SET v_terminoInterjornada = NULL;
		SET v_tempoInterjornada = NULL;
		SET v_inicioForaServico = NULL;
		SET v_terminoForaServico = NULL;
		SET v_tempoForaServico = NULL;
        SET v_statusJornada = NULL;
        
        -- Só entra no Loop quando for Início de Jornada
		IF MacroNumber_loop = 1 THEN
			
			-- Valida se o início de jornada não tem o código do motorista
            IF isNull(Text_loop) OR Text_loop = "" THEN
                SET v_log_subject = "Matricula Faltante"; 
                SET v_log_message = CONCAT("Início de Jornada sem informar a matrícula do motorista");
				INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), log_subject, v_log_message, null, ID_loop);

				FETCH cursor_id INTO ID_loop, MctAddress_loop, MacroNumber_loop, Text_loop, PositionTime_loop;

				IF record_not_found THEN 
					SET v_EOF = 1;
				END IF;

                ITERATE all_id;
            END IF;
            
			-- Valida se o código do motorista informado não está cadastrado na tabela de motoristas
            SELECT count(1) INTO v_count_motorista 
              FROM motorista
			 WHERE matricula = Replace(Text_loop, '_', '') ;
             
            IF v_count_motorista = 0 THEN
                SET v_log_subject = "Matricula Inválida"; 
                SET v_log_message = CONCAT("Matrícula ", Replace(Text_loop, '_', ''), " não consta no cadastro de motoristas!");
				INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, null, ID_loop);

				FETCH cursor_id INTO ID_loop, MctAddress_loop, MacroNumber_loop, Text_loop, PositionTime_loop;

				IF record_not_found THEN 
					SET v_EOF = 1;
				END IF;

                ITERATE all_id;
            END IF;

			SET v_motorista_matricula = Replace(Text_loop, '_', '');
            
            SELECT id INTO v_motorista_id
              FROM motorista
			 WHERE matricula = v_motorista_matricula;

			-- Inicializa as variáveis para o Loop
			SET v_mctaddress = MctAddress_loop;
			SET v_positionTime = PositionTime_loop;
            SET v_dataJornada = PositionTime_loop;

			-- Cria a jornada preliminarmente para não violar as Foreing Keys
			INSERT INTO jornada (mctaddress, motorista_id, data_jornada) VALUES (v_mctaddress, v_motorista_id, v_dataJornada);
			
			-- Obtém o ID desta Jornada recém inserida
			SELECT MAX(ID) INTO v_jornadaId FROM jornada;            

			-- Conta loop para previnir processar mais que 500 return message por jornada
			SET v_loop_count = 1;

			-- to_days(v_positionTime) = to_days(PositionTime_loop)
			-- Faz o Loop enquanto estiver na mesma Jornada
			WHILE v_EOF = 0 AND v_mctaddress = MctAddress_loop AND v_macroNumber_last <> 4 AND v_loop_exit = 0 AND v_loop_count < 500
			DO
				SET v_macroNumber = MacroNumber_loop;
				SET v_positionTime = PositionTime_loop;
				SET v_list_LoopID = CONCAT(v_list_LoopID, lpad(ID_loop, 7, "0"), ",");
                
				-- Calcula os valores da Jornada
				CALL sp_level_2_1(ID_loop, MctAddress_loop, MacroNumber_loop, Text_loop, PositionTime_loop, v_mctaddress, v_positionTime, v_macroNumber, 
					v_jornadaId, v_statusJornada, v_mctaddress_last, v_macroNumber_last, v_positionTime_last, v_dataJornada, v_inicioJornada, v_terminoJornada, v_tempoJornada, 
-- 					v_inicioDirecao, v_terminoDirecao, v_tempoDirecao, v_inicioDescanso, v_terminoDescanso, v_tempoDescanso, v_tempoDescansoTotal, 
					v_inicioRefeicao1, v_terminoRefeicao1, v_tempoRefeicao1, 
					v_inicioRefeicao2, v_terminoRefeicao2, v_tempoRefeicao2, v_inicioForaServico, v_terminoForaServico, v_tempoForaServico, v_inicioInterjornada, v_terminoInterjornada, v_tempoInterjornada, 
					v_inicioCarregamento, v_terminoCarregamento, v_tempoCarregamento, v_inicioDescarregamento, v_terminoDescarregamento, v_tempoDescarregamento);

				-- Le o proximo registro
				FETCH cursor_id INTO ID_loop, MctAddress_loop, MacroNumber_loop, Text_loop, PositionTime_loop;

				-- Verifica se atingiu o final do arquivo
                IF record_not_found THEN 
					SET v_EOF = 1;
				END IF;

				-- Se encontrou um início de jornada sem ter encerrado a atual
                IF MacroNumber_loop = 1 THEN 
					-- Força a saída do Loop
					SET v_loop_exit = 1;        
				END IF;
                
                SET v_loop_count = v_loop_count + 1;
			END WHILE;	
		
		ELSE -- Se a macro da primeira mensagem de retorno não for do tipo Início de Jornada
			SET v_log_subject = "Jornada sem Início"; 
			SET v_log_message = CONCAT("Jornada sem Início de Jornada");
			INSERT INTO execute_log (log_date, log_subject, log_message, jornada_id, returnmessage_id) VALUES (NOW(), v_log_subject, v_log_message, null, ID_loop);

			-- Por ter sido o primeiro registro da jornada com a macro diferente de 1, ignora e pega o proximo registro
			FETCH cursor_id INTO ID_loop, MctAddress_loop, MacroNumber_loop, Text_loop, PositionTime_loop;

			-- Verifica se atingiu o final do arquivo
			IF record_not_found THEN 
				SET v_EOF = 1;
			END IF;
            
			-- Volta para processar o proximo registro
			ITERATE all_id;        
        END IF;

		-- select "###### CHEGOU AQUI ####" AS debug;
        
		-- Se terminou uma jornada ou acabaram os registros ao final da última jornada
        -- IF v_EOF = 0 OR v_macroNumber_last = 4 OR (v_EOF = 1 AND v_macroNumber_last = 16) THEN    
        
        -- Se a jornada atual teve um termino nrmal ou se começou outra jornada sem ter terminado a anterior
		IF v_terminoJornada is not null or v_loop_exit = 1 THEN 
        
			-- Faz validações e calculos finais para a jornada
			CALL sp_level_2_2(ID_loop, MctAddress_loop, MacroNumber_loop, Text_loop, PositionTime_loop, v_mctaddress, v_positionTime, v_macroNumber, 
				v_jornadaId, v_statusJornada, v_mctaddress_last, v_macroNumber_last, v_positionTime_last, v_dataJornada, v_inicioJornada, v_terminoJornada, 
                v_tempoJornada, 
-- 				v_inicioDirecao, v_terminoDirecao, v_tempoDirecao, v_inicioDescanso, v_terminoDescanso, v_tempoDescanso, v_tempoDescansoTotal, 
				v_inicioRefeicao1, v_terminoRefeicao1, v_tempoRefeicao1, 
				v_inicioRefeicao2, v_terminoRefeicao2, v_tempoRefeicao2, v_inicioForaServico, v_terminoForaServico, v_tempoForaServico, v_inicioInterjornada, 
                v_terminoInterjornada, v_tempoInterjornada, v_inicioCarregamento, v_terminoCarregamento, v_tempoCarregamento, 
                v_inicioDescarregamento, v_terminoDescarregamento, v_tempoDescarregamento);
            
            -- Atualiza a Jornada no banco de dados
			UPDATE jornada SET 
				-- motorista_id = v_motorista_id, 
                -- mctaddress = v_mctaddress, 
                -- data_jornada = v_dataJornada, 
                status_jornada = v_statusJornada, 
                inicio_jornada = v_inicioJornada, 
                termino_jornada = v_terminoJornada, 
                tempo_jornada = v_tempoJornada, 
--                tempo_descanso_total = v_tempoDescansoTotal, 
				inicio_refeicao_1 = v_inicioRefeicao1, 
                termino_refeicao_1 = v_terminoRefeicao1, 
                tempo_refeicao_1 = v_tempoRefeicao1, 
                inicio_refeicao_2 = v_inicioRefeicao2, 
                termino_refeicao_2 = v_terminoRefeicao2, 
                tempo_refeicao_2 = v_tempoRefeicao2,
				tempo_fora_servico = v_tempoForaServico, 
                tempo_interjornada = v_tempoInterjornada, 
--                inicio_direcao = v_inicioDirecao, 
--                termino_direcao = v_terminoDirecao, 
--                tempo_direcao = v_tempoDirecao,
                inicio_carregamento = v_inicioCarregamento, 
                termino_carregamento = v_terminoCarregamento, 
                tempo_carregamento = v_tempoCarregamento, 
                inicio_descarregamento = v_inicioDescarregamento, 
                termino_descarregamento = v_terminoDescarregamento, 
                tempo_descarregamento = v_tempoDescarregamento
			WHERE id = v_jornadaId;
/*
			INSERT INTO jornada (motorista_id, mctaddress, data_jornada, status_jornada, inicio_jornada, termino_jornada, tempo_jornada, tempo_descanso_total, 
				inicio_refeicao_1, termino_refeicao_1, tempo_refeicao_1, inicio_refeicao_2, termino_refeicao_2, tempo_refeicao_2,
				tempo_fora_servico, tempo_interjornada, inicio_direcao, termino_direcao, tempo_direcao,
                inicio_carregamento, termino_carregamento, tempo_carregamento, inicio_descarregamento, termino_descarregamento, tempo_descarregamento)
			VALUES (v_motorista_id, v_mctaddress, v_dataJornada, v_statusJornada, v_inicioJornada, v_terminoJornada, v_tempoJornada, v_tempoDescansoTotal, 
				v_inicioRefeicao1, v_terminoRefeicao1, v_tempoRefeicao1, v_inicioRefeicao2, v_terminoRefeicao2, v_tempoRefeicao2,
				v_tempoForaServico, v_tempoInterjornada, v_inicioDirecao, v_terminoDirecao, v_tempoDirecao,
                v_inicioCarregamento, v_terminoCarregamento, v_tempoCarregamento, v_inicioDescarregamento, v_terminoDescarregamento, v_tempoDescarregamento);
*/				
			-- Obtém o ID desta Jornada recém inserida
            -- SELECT MAX(ID) INTO v_jornadaId FROM jornada;            

            -- Atualiza as jornada descanso para a jornada recém inserida
			-- UPDATE jornada_descanso SET jornada_id = v_jornadaId WHERE jornada_id = -1;
            
            -- Faz as críticas para as jornadas validadas ok
			CALL sp_level_2_3(ID_loop, MctAddress_loop, MacroNumber_loop, Text_loop, PositionTime_loop, v_mctaddress, v_positionTime, v_macroNumber, 
				v_jornadaId, v_statusJornada, v_mctaddress_last, v_macroNumber_last, v_positionTime_last, v_dataJornada, v_inicioJornada, v_terminoJornada, 
                v_tempoJornada, 
-- 				v_inicioDirecao, v_terminoDirecao, v_tempoDirecao, v_inicioDescanso, v_terminoDescanso, v_tempoDescanso, v_tempoDescansoTotal, 
                v_inicioRefeicao1, v_terminoRefeicao1, v_tempoRefeicao1, v_inicioRefeicao2, v_terminoRefeicao2, v_tempoRefeicao2, 
                v_inicioForaServico, v_terminoForaServico, v_tempoForaServico, v_inicioInterjornada, v_terminoInterjornada, v_tempoInterjornada, 
				v_inicioCarregamento, v_terminoCarregamento, v_tempoCarregamento, v_inicioDescarregamento, v_terminoDescarregamento, v_tempoDescarregamento );
            
/*
            -- Cria log de execução quando existe alguma validação realizada para a jornada
            IF v_statusJornada <= 0 THEN
				UPDATE execute_log SET jornada_id = v_jornadaId WHERE jornada_id = -1;
            END IF;
*/
			-- Retira a última virgula da lista
			SET v_list_LoopID = TRIM(TRAILING ',' FROM v_list_LoopID);
            
            -- Atualiza os registros processados de returnmessage usados para criar esta última jornada
			UPDATE returnmessage 
			   SET jornada_id = v_jornadaId,
                   proc = v_statusJornada
			WHERE proc is null
			  AND jornada_id is null
			  AND mctaddress = v_mctaddress_last
              AND instr(v_list_LoopID, lpad(id, 7, "0")) > 0;

/*
            -- Atualiza os registros processados de positionhistoy usados para criar esta última jornada
			IF v_inicioJornada is not null and v_terminoJornada is not null THEN

				UPDATE positionhistory 
				   SET jornada_id = v_jornadaId,
					   proc = v_statusJornada
				WHERE proc is null
				  AND mctaddress = v_mctaddress_last
				  AND TimePosition between v_inicioJornada and v_terminoJornada;
            END IF;
*/
            -- Cria o calendário para as jornadas recém inseridas
			IF (not exists (select id from calendario where data_jornada = v_dataJornada)) THEN 
            
				INSERT INTO calendario
				   (data_jornada,
					ano_jornada,
					mes_jornada,
					mes_jornada_nome,
					dia_jornada,
					dia_semana,
					bimestre_jornada,
					bimestre_jornada_nome,
					trimestre_jornada,
					trimestre_jornada_nome,
					semestre_jornada,
					semestre_jornada_nome)
				VALUES (v_dataJornada, 
					year(v_dataJornada), 
                    month(v_dataJornada), 
                    get_month_name(v_dataJornada, 1), 
                    DAY(v_dataJornada), 
                    get_day_name(v_dataJornada, 1),
                    ceiling(month(v_dataJornada) / 2) , 
					get_calendar_name(v_dataJornada, 2), 
                    ceiling(month(v_dataJornada) / 3) , 
					get_calendar_name(v_dataJornada, 3), 
                    ceiling(month(v_dataJornada) / 6),
                    get_calendar_name(v_dataJornada, 6) 
                );
            
				SET v_ult_jornada_mctaddress_last =  v_mctaddress_last;
                SET v_ult_jornada_macroNumber_last = v_macroNumber_last;
                SET v_ult_jornada_positionTime_last = v_positionTime_last;
            END IF;
              
        ELSE -- Senão, se a jornada atual não teve um fim
			-- Deleta a jornada em questão
			DELETE FROM jornada WHERE id = v_jornadaId;
        END IF;
		
	END LOOP all_id;

	CLOSE cursor_id;

	SET p_status = 1;
    
    COMMIT;
    
    SET p_stopped_time = NOW();
END $$ 

DELIMITER ;
