INSERT INTO T_FILIAIS (nome, cnpj, is_active, cep, logradouro, numero, complemento, bairro, cidade, uf, pais)
SELECT 'Matriz', '12345678000190', 1, '01001000', 'Rua Alfa', '100', NULL, 'Chacara Klabin', 'São Paulo', 'SP', 'Brasil'
WHERE NOT EXISTS (SELECT 1 FROM T_FILIAIS WHERE cnpj = '12345678000190');

INSERT INTO T_FILIAIS (nome, cnpj, is_active, cep, logradouro, numero, complemento, bairro, cidade, uf, pais)
SELECT 'Filial Centro', '98765432000109', 1, '01310930', 'Av. Beta', '200', '2º andar','Sé', 'São Paulo', 'SP', 'Brasil'
WHERE NOT EXISTS (SELECT 1 FROM T_FILIAIS WHERE cnpj = '98765432000109');
