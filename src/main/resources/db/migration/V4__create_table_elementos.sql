CREATE TABLE T_ELEMENTOS (
  elemento_id  BIGINT IDENTITY(1,1) PRIMARY KEY,

  canvas_id    BIGINT        NOT NULL,
  tipo         VARCHAR(16)   NOT NULL,  -- Enum STRING
  z_index      INT           NOT NULL CONSTRAINT df_elemento_z DEFAULT (0),

  props_json   NVARCHAR(MAX) NOT NULL,  -- @Lob (CLOB)

  CONSTRAINT fk_elemento_canvas
    FOREIGN KEY (canvas_id) REFERENCES T_CANVAS(canvas_id)
);

CREATE INDEX ix_elemento_canvas ON T_ELEMENTOS(canvas_id);
CREATE INDEX ix_elemento_tipo   ON T_ELEMENTOS(tipo);
CREATE INDEX ix_elemento_zindex ON T_ELEMENTOS(z_index);

