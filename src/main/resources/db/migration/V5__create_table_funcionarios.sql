CREATE TABLE T_FUNCIONARIOS (
  funcionario_id   BIGINT IDENTITY(1,1) PRIMARY KEY,
  nome_completo    VARCHAR(200)   NOT NULL,
  cpf              VARCHAR(11)    NOT NULL,
  cargo            VARCHAR(40)    NOT NULL, -- Enum STRING
  is_active        BIT            NOT NULL CONSTRAINT df_func_is_active DEFAULT (1),

  filial_id        BIGINT         NULL,

  CONSTRAINT fk_func_filial
    FOREIGN KEY (filial_id) REFERENCES T_FILIAIS(filial_id)
);

CREATE UNIQUE INDEX uq_funcionarios_cpf ON T_FUNCIONARIOS(cpf);

