DROP TABLE IF EXISTS execute_history;

CREATE TABLE IF NOT EXISTS execute_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    execution_message VARCHAR(255) NOT NULL,
    execution_status TINYINT NOT NULL,
    execution_started DATETIME NOT NULL,
    execution_stopped DATETIME NOT NULL,
    execution_level TINYINT NOT NULL,
    id_initial BIGINT(20) NOT NULL,
    id_final BIGINT(20) NOT NULL,
    affected_records INT(8) NOT NULL
);