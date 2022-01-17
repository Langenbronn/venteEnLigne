INSERT INTO CUSTOMER(id, firstname, gender, lastname) VALUES ('f05157b5-e9fb-45d1-9242-4553bd742c0c', 'Arthur', 'Man', 'Cuvit');

INSERT INTO SELLER(id, name) VALUES ('6f19bc24-c001-46dc-8504-88d291984f79', 'Philibert');
INSERT INTO SELLER(id, name) VALUES ('62768a8e-0313-44ad-bfe2-5f358d84f9d7', 'Space Cowboys');
INSERT INTO SELLER(id, name) VALUES ('9ec87b9c-1b50-401b-b9e8-20a4fe6693d3', 'Domino');
INSERT INTO SELLER(id, name) VALUES ('1a472feb-2f71-44a4-9015-366cccb8b02d', 'Saturn');
INSERT INTO SELLER(id, name) VALUES ('436f5d51-41d7-4ed8-9b5e-a9135f52a3a8', 'Ikea');

INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES ('d7691b54-5751-4e6c-97b1-39954505094d', 'Unlock ! Game Adventures', 30.71, 'Jeux', 'Dans Unlock! Games Adventures, plongez dans l univers de Mysterium, Aventuriers du Rail et Pandemic');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES ('022e462d-b25a-4758-93bc-2da567e2d166', 'Thorgun', 2.99, 'Inconnu', 'Plaid, gris-vert clair120x160 cm');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES ('1e2f4303-b4a6-4a97-9bbf-b06494d21e79', 'GODMORGON / ODENSVIK', 559.00, 'Meuble', 'Meuble lavabo 4tir, effet chêne blanchi/Dalskär mitigeur lavabo123x49x64 cm');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES ('aa886544-66df-4743-80fe-61ba9b95efc3', 'Clank! - Legacy', 85.48, 'Jeux', 'Pénétrez dans l univers fantastique de Clank à travers une campagne de plusieurs parties. Après celle-ci, le jeu reste entièrement rejouable.');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES ('6b0f5a04-4ad8-45b6-96b4-191abe808d5e', '7 Wonders', 39.49, 'Jeux', 'Cette boîte est la 2ème édition de ce jeu devenu un classique');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES ('da9d9c90-7467-4ded-8952-15d9c7cd668b', 'Les Aventuriers du Rail', 41.88, 'Jeux', 'Prenez le contrôle du réseau ferroviaire américain en reliant un maximum de villes entre elles.');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES ('6aeb6450-cc08-4854-abcb-acbcd486822d', 'Carcassonne : 20th Anniversary Edition Limitée', 35.10, 'Jeux', 'Construisez un paysage à l’aide de tuiles, puis tentez de contrôler un maximum de territoires. un jeu où la stratégie s allie à la simplicité.');

INSERT INTO STOCK(id, quantity, product, seller) VALUES (1, 10, 'd7691b54-5751-4e6c-97b1-39954505094d', '6f19bc24-c001-46dc-8504-88d291984f79');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (2, 50, 'aa886544-66df-4743-80fe-61ba9b95efc3', '6f19bc24-c001-46dc-8504-88d291984f79');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (3, 56, '6b0f5a04-4ad8-45b6-96b4-191abe808d5e', '6f19bc24-c001-46dc-8504-88d291984f79');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (4, 80, 'da9d9c90-7467-4ded-8952-15d9c7cd668b', '6f19bc24-c001-46dc-8504-88d291984f79');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (5, 25, '6aeb6450-cc08-4854-abcb-acbcd486822d', '6f19bc24-c001-46dc-8504-88d291984f79');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (6, 10, 'd7691b54-5751-4e6c-97b1-39954505094d', '62768a8e-0313-44ad-bfe2-5f358d84f9d7');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (7, 12, 'aa886544-66df-4743-80fe-61ba9b95efc3', '62768a8e-0313-44ad-bfe2-5f358d84f9d7');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (8, 1, '6b0f5a04-4ad8-45b6-96b4-191abe808d5e', '62768a8e-0313-44ad-bfe2-5f358d84f9d7');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (9, 20, 'da9d9c90-7467-4ded-8952-15d9c7cd668b', '62768a8e-0313-44ad-bfe2-5f358d84f9d7');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (10, 46, '6b0f5a04-4ad8-45b6-96b4-191abe808d5e', '9ec87b9c-1b50-401b-b9e8-20a4fe6693d3');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (11, 20, 'da9d9c90-7467-4ded-8952-15d9c7cd668b', '9ec87b9c-1b50-401b-b9e8-20a4fe6693d3');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (12, 9, '6aeb6450-cc08-4854-abcb-acbcd486822d', '9ec87b9c-1b50-401b-b9e8-20a4fe6693d3');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (13, 125, '022e462d-b25a-4758-93bc-2da567e2d166', '436f5d51-41d7-4ed8-9b5e-a9135f52a3a8');
INSERT INTO STOCK(id, quantity, product, seller) VALUES (14, 50, '1e2f4303-b4a6-4a97-9bbf-b06494d21e79', '436f5d51-41d7-4ed8-9b5e-a9135f52a3a8');