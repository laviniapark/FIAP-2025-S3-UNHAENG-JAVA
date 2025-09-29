IF EXISTS (
    SELECT 1
    FROM sys.key_constraints kc
    WHERE kc.[name] = 'ix_canvas_nome'
      AND kc.[parent_object_id] = OBJECT_ID('dbo.T_CANVAS')
)
BEGIN
    ALTER TABLE dbo.T_CANVAS DROP CONSTRAINT [ix_canvas_nome];
END

IF EXISTS (
    SELECT 1
    FROM sys.indexes i
    WHERE i.[name] = 'ix_canvas_nome'
      AND i.[object_id] = OBJECT_ID('dbo.T_CANVAS')
)
BEGIN
    DROP INDEX [ix_canvas_nome] ON dbo.T_CANVAS;
END
