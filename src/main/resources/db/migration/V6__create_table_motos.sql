CREATE TABLE T_MOTOS (
  moto_id      BIGINT IDENTITY(1,1) PRIMARY KEY,
  placa        VARCHAR(7)    NOT NULL,
  marca        VARCHAR(100)  NOT NULL,
  modelo       VARCHAR(120)  NOT NULL,
  ano          INT           NOT NULL,
  status       VARCHAR(20)   NOT NULL, -- Enum STRING

  filial_id    BIGINT        NULL,

  CONSTRAINT fk_moto_filial
    FOREIGN KEY (filial_id) REFERENCES T_FILIAIS(filial_id),

  CONSTRAINT ck_moto_ano CHECK (ano >= 1990)
);

CREATE UNIQUE INDEX un_motos_placa ON T_MOTOS(placa);
