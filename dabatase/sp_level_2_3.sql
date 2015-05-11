DELIMITER $$

CREATE PROCEDURE sp_level_2_3 (
	IN ID_loop BIGINT(20), 
	IN MctAddress_loop INT(11), 
	IN MacroNumber_loop TINYINT, 
	IN Text_loop VARCHAR(255), 
	IN PositionTime_loop DATETIME, 

	IN v_mctaddress INT(11), 
	IN v_positionTime DATETIME, 
	IN v_macroNumber TINYINT, 
	IN v_jornadaId BIGINT(20), 
	IN v_statusJornada TINYINT,  
	IN v_mctaddress_last INT(11), 
	IN v_macroNumber_last TINYINT, 
    IN v_positionTime_last DATETIME,

	IN v_dataJornada DATE, 
	IN v_inicioJornada DATETIME, 
	IN v_terminoJornada DATETIME, 
	IN v_tempoJornada VARCHAR(10), 
	IN v_inicioDirecao DATETIME, 
	IN v_terminoDirecao DATETIME, 
	IN v_tempoDirecao TIME, 
	IN v_inicioDescanso DATETIME, 
	IN v_terminoDescanso DATETIME, 
	IN v_tempoDescanso TIME, 
	IN v_inicioRefeicao1 DATETIME, 
	IN v_terminoRefeicao1 DATETIME, 
	IN v_tempoRefeicao1 TIME, 
	IN v_inicioRefeicao2 DATETIME, 
	IN v_terminoRefeicao2 DATETIME, 
	IN v_tempoRefeicao2 TIME, 
	IN v_inicioForaServico DATETIME,
	IN v_terminoForaServico DATETIME,
	IN v_tempoForaServico TIME, 
	IN v_inicioInterjornada DATETIME, 
	IN v_terminoInterjornada DATETIME, 
	IN v_tempoInterjornada TIME, 
	IN v_inicioCarregamento DATETIME, 
	IN v_terminoCarregamento DATETIME, 
	IN v_tempoCarregamento TIME, 
	IN v_inicioDescarregamento DATETIME, 
	IN v_terminoDescarregamento DATETIME, 
	IN v_tempoDescarregamento TIME,
    IN v_tempoDescansoTotal TIME
)
BEGIN 
	DECLARE v_log_subject VARCHAR(40);
	DECLARE v_log_message VARCHAR(255);

	DECLARE v_horario_minimo_inicio_jornada TIME;
    DECLARE v_horario_maximo_termino_jornada TIME;
    DECLARE v_intervalo_minimo_refeicao TIME;
    DECLARE v_intervalo_minimo_interjornada TIME;
    DECLARE v_num_horas_duracao_max_jornada TIME;
    DECLARE v_num_dias_maximo_fora_servico INT(3);
    
    DECLARE v_criticaId BIGINT(20);
    DECLARE v_mensagem VARCHAR(255);

    DECLARE v_num_horas_duracao_jornada TIME;
    DECLARE v_num_dias_fora_servico INT(3);
    
    DECLARE v_inicio_jornada TIME;
    DECLARE v_termino_jornada TIME;
    
    -- Somente realiza as críticas para as jornadas com status OK.
--    IF v_statusJornada = 1 THEN 
    
		-- Carrega os parâmetros para a crítica da jornada
		select DATE_FORMAT(horario_minimo_inicio_jornada, "%T"), 
			DATE_FORMAT(horario_maximo_termino_jornada,  "%T"), 
			DATE_FORMAT(intervalo_minimo_refeicao, "%T"), 
			DATE_FORMAT(intervalo_minimo_interjornada, "%T"),
            num_dias_maximo_fora_servico,
            num_horas_duracao_max_jornada
		into v_horario_minimo_inicio_jornada, 
			v_horario_maximo_termino_jornada, 
			v_intervalo_minimo_refeicao,
			v_intervalo_minimo_interjornada,
            v_num_dias_maximo_fora_servico,
            v_num_horas_duracao_max_jornada
		from critica_param 
        where id = 1;
		
		-- Critica o Início da Jornada
        set v_inicio_jornada = DATE_FORMAT(v_inicioJornada, "%T");

		IF v_inicio_jornada is not null AND v_inicio_jornada < v_horario_minimo_inicio_jornada THEN
			SET v_mensagem = CONCAT("Início da Jornada: ", v_inicio_jornada, ". Horário mínimo permitido: ", v_horario_minimo_inicio_jornada);
        
			select id into v_criticaId
			from critica 
			where campo_critica_param = "horario_minimo_inicio_jornada";
			
			insert into critica_jornada(critica_id, jornada_id, mensagem) VALUES (v_criticaId, v_jornadaId, v_mensagem);
		END IF;

		-- Critica o Término da Jornada
        set v_termino_jornada = DATE_FORMAT(v_terminoJornada, "%T");
        
		IF v_termino_jornada is not null AND v_termino_jornada > v_horario_maximo_termino_jornada THEN
			SET v_mensagem = CONCAT("Término da Jornada: ", v_termino_jornada, ". Horário máximo permitido: ", v_horario_maximo_termino_jornada);
            
			select id into v_criticaId
			from critica 
			where campo_critica_param = "horario_maximo_termino_jornada";
			
			insert into critica_jornada(critica_id, jornada_id, mensagem) VALUES (v_criticaId, v_jornadaId, v_mensagem);
		END IF;

		-- Critica o tempo da Refeição 1
		IF v_tempoRefeicao1 is not null AND v_tempoRefeicao1 < v_intervalo_minimo_refeicao THEN
			SET v_mensagem = CONCAT("Tempo da Refeição 1: ", v_tempoRefeicao1, ". Tempo mínimo permitido: ", v_intervalo_minimo_refeicao);
            
			select id into v_criticaId
			from critica 
			where campo_critica_param = "intervalo_minimo_refeicao";
			
			insert into critica_jornada(critica_id, jornada_id, mensagem) VALUES (v_criticaId, v_jornadaId, v_mensagem);
		END IF;

		-- Critica o tempo da Refeição 2
		IF v_tempoRefeicao2 is not null AND v_tempoRefeicao2 < v_intervalo_minimo_refeicao THEN
			SET v_mensagem = CONCAT("Tempo da Refeição 2: ", v_tempoRefeicao2, ". Tempo mínimo permitido: ", v_intervalo_minimo_refeicao);

			select id into v_criticaId
			from critica 
			where campo_critica_param = "intervalo_minimo_refeicao";
			
			insert into critica_jornada(critica_id, jornada_id, mensagem) VALUES (v_criticaId, v_jornadaId, v_mensagem);
		END IF;

		-- Critica o tempo de Interjornada
		IF v_tempoInterjornada is not null AND v_tempoInterjornada < v_intervalo_minimo_interjornada THEN
			SET v_mensagem = CONCAT("Tempo de Interjornada: ", v_tempoInterjornada, ". Tempo mínimo permitido: ", v_intervalo_minimo_interjornada);

			select id into v_criticaId
			from critica 
			where campo_critica_param = "intervalo_minimo_interjornada";
			
			insert into critica_jornada(critica_id, jornada_id, mensagem) VALUES (v_criticaId, v_jornadaId, v_mensagem);
		END IF;

		-- Critica se a jornada durou mais de que a duração máxima em dias
		IF v_inicioJornada is not null AND v_terminoJornada is not null THEN
        
			SET v_num_horas_duracao_jornada = timediff(v_terminoJornada, v_inicioJornada);

			IF v_num_horas_duracao_jornada > v_num_horas_duracao_max_jornada THEN
				SET v_mensagem = CONCAT("Duração da Jornada: ", v_num_horas_duracao_jornada, ". Tempo máximo permitido: ", v_num_horas_duracao_max_jornada, " horas");
				
				select id into v_criticaId
				from critica 
				where campo_critica_param = "num_horas_duracao_max_jornada";
				
				insert into critica_jornada(critica_id, jornada_id, mensagem) VALUES (v_criticaId, v_jornadaId, v_mensagem);
			END IF;
            
		END IF;

		-- Critica se o número de dias fora de serviço foi maior que o número máximo de dias permitido
		IF v_inicioForaServico is not null AND v_terminoForaServico is not null THEN
        
			SET v_num_dias_fora_servico = datediff(v_terminoForaServico, v_inicioForaServico);

			IF v_num_dias_fora_servico > v_num_dias_maximo_fora_servico THEN
				SET v_mensagem = CONCAT("Número de dias fora de serivço: ", v_num_dias_fora_servico, ". Número máximo permitido: ", v_num_dias_maximo_fora_servico, " dias");
				
				select id into v_criticaId
				from critica 
				where campo_critica_param = "num_dias_maximo_fora_servico";
				
				insert into critica_jornada(critica_id, jornada_id, mensagem) VALUES (v_criticaId, v_jornadaId, v_mensagem);
			END IF;
            
		END IF;

        
--	END IF;

END $$ 

DELIMITER ;
