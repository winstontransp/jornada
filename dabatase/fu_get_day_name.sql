CREATE FUNCTION get_day_name(p_data_calendar DATE, p_prefix INT(1)) RETURNS varchar(20)
BEGIN
DECLARE v_nome VARCHAR(20) DEFAULT NULL;
DECLARE v_dia INT(1);

SET v_dia = dayofweek(p_data_calendar);

CASE v_dia
    WHEN 1 THEN SET v_nome = "Domingo";    
    WHEN 2 THEN SET v_nome = "Segunda-Feira";
    WHEN 3 THEN SET v_nome = "Terça-Feira";
    WHEN 4 THEN SET v_nome = "Quarta-Feira";
    WHEN 5 THEN SET v_nome = "Quinta-Feira";
    WHEN 6 THEN SET v_nome = "Sexta-Feira";
    WHEN 7 THEN SET v_nome = "Sábado";
END CASE;

IF p_prefix IS NOT NULL AND p_prefix = 1 THEN
	SET v_nome = concat(v_dia, "-", v_nome);
END IF;

RETURN v_nome;
END