CREATE TABLE T_CANVAS (
  canvas_id        BIGINT IDENTITY(1,1) PRIMARY KEY,
  nome             VARCHAR(120)  NOT NULL,
  largura          INT           NOT NULL,
  altura           INT           NOT NULL,
  backgroundColor  VARCHAR(16)   NULL,

  filial_id        BIGINT        NOT NULL,

  grid_size        INT           NOT NULL CONSTRAINT df_canvas_grid_size DEFAULT (10),
  snap_ativo       BIT           NOT NULL CONSTRAINT df_canvas_snap DEFAULT (1),
  locked           BIT           NOT NULL CONSTRAINT df_canvas_locked DEFAULT (0),

  -- checks
  CONSTRAINT ck_canvas_largura CHECK (largura BETWEEN 1 AND 10000),
  CONSTRAINT ck_canvas_altura  CHECK (altura  BETWEEN 1 AND 10000),
  CONSTRAINT ck_canvas_grid    CHECK (grid_size BETWEEN 1 AND 200),

  -- fk
  CONSTRAINT fk_canvas_filial  FOREIGN KEY (filial_id) REFERENCES T_FILIAIS(filial_id),

  -- unique
  CONSTRAINT ix_canvas_nome    UNIQUE (nome),
  CONSTRAINT uk_canvas_filial  UNIQUE (filial_id)
);
