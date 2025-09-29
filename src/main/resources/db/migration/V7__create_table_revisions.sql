CREATE TABLE T_REVISIONS (
  revision_id BIGINT IDENTITY(1,1) PRIMARY KEY,

  filial_id   BIGINT       NULL,
  canvas_id   BIGINT       NULL,

  created_at  DATETIME2(0) NOT NULL CONSTRAINT df_revision_created DEFAULT (SYSUTCDATETIME()),

  shapes_json NVARCHAR(MAX) NULL,
  svg_text    NVARCHAR(MAX) NULL,

  CONSTRAINT fk_revision_filial FOREIGN KEY (filial_id) REFERENCES T_FILIAIS(filial_id),
  CONSTRAINT fk_revision_canvas FOREIGN KEY (canvas_id) REFERENCES T_CANVAS(canvas_id)
);
