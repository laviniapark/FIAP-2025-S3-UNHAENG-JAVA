INSERT INTO T_FILIAIS (nome, cnpj, is_active, cep, logradouro, numero, complemento, cidade, uf, pais)
SELECT 'Matriz', '12345678000190', 1, '01001000', 'Rua Alfa', '100', NULL, 'São Paulo', 'SP', 'Brasil'
WHERE NOT EXISTS (SELECT 1 FROM T_FILIAIS WHERE cnpj = '12.345.678/0001-90');

INSERT INTO T_FILIAIS (nome, cnpj, is_active, cep, logradouro, numero, complemento, cidade, uf, pais)
SELECT 'Filial Centro', '98765432000109', 1, '01310930', 'Av. Beta', '200', '2º andar', 'São Paulo', 'SP', 'Brasil'
WHERE NOT EXISTS (SELECT 1 FROM T_FILIAIS WHERE cnpj = '98.765.432/0001-09');
