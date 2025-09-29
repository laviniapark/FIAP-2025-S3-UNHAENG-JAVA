IF COL_LENGTH('dbo.T_CANVAS','backgroundColor') IS NOT NULL
AND COL_LENGTH('dbo.T_CANVAS','background_color') IS NULL
    EXEC sp_rename 'dbo.T_CANVAS.backgroundColor', 'background_color', 'COLUMN';
