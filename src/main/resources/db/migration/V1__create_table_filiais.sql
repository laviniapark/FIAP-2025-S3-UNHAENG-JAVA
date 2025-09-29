CREATE TABLE T_FILIAIS (
  filial_id       BIGINT IDENTITY(1,1) PRIMARY KEY,
  nome            VARCHAR(120)     NOT NULL,
  cnpj            VARCHAR(14)      NOT NULL,
  is_active       BIT              NOT NULL CONSTRAINT df_filiais_is_active DEFAULT (1),

  -- Endereco
  cep             VARCHAR(8)       NOT NULL,
  logradouro      VARCHAR(150)     NOT NULL,
  numero          VARCHAR(20)      NOT NULL,
  complemento     VARCHAR(120)     NULL,
  cidade          VARCHAR(80)      NOT NULL,
  uf              CHAR(2)          NOT NULL,
  pais            VARCHAR(80)      NOT NULL
);

CREATE UNIQUE INDEX un_filiais_cnpj ON T_FILIAIS(cnpj);

