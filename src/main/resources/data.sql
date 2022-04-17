INSERT INTO CUSTOMER(id, firstname, gender, lastname) VALUES ('f05157b5-e9fb-45d1-9242-4553bd742c0c', 'Arthur', 'Man', 'Cuvit');
INSERT INTO CUSTOMER(id, firstname, gender, lastname) VALUES ('896157b5-e9fb-45d1-9258-4553bd742c0c', 'Bob', 'Man', 'Cuvit');

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

INSERT INTO STOCK(id, quantity, product, seller) VALUES ('5af07b82-e094-4759-aa4a-1962b28c0444', 10, 'd7691b54-5751-4e6c-97b1-39954505094d', '6f19bc24-c001-46dc-8504-88d291984f79');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('492d93eb-e15d-42ce-8370-aed31233bf9e', 50, 'aa886544-66df-4743-80fe-61ba9b95efc3', '6f19bc24-c001-46dc-8504-88d291984f79');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('c736525f-1b42-4e9d-ad75-8feea459ecad', 56, '6b0f5a04-4ad8-45b6-96b4-191abe808d5e', '6f19bc24-c001-46dc-8504-88d291984f79');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('76399961-6181-4c79-9fa3-f0ce59262b8a', 80, 'da9d9c90-7467-4ded-8952-15d9c7cd668b', '6f19bc24-c001-46dc-8504-88d291984f79');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('31d5702a-5940-4778-bc2b-b96c640ac091', 25, '6aeb6450-cc08-4854-abcb-acbcd486822d', '6f19bc24-c001-46dc-8504-88d291984f79');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('d3f230d8-25e7-4f42-b881-7f4bd5a8cdd8', 10, 'd7691b54-5751-4e6c-97b1-39954505094d', '62768a8e-0313-44ad-bfe2-5f358d84f9d7');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('d03de33e-e558-4fef-aa92-eae8cebb0867', 12, 'aa886544-66df-4743-80fe-61ba9b95efc3', '62768a8e-0313-44ad-bfe2-5f358d84f9d7');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('013622c6-8479-4272-9213-31cf0aef4faa', 1, '6b0f5a04-4ad8-45b6-96b4-191abe808d5e', '62768a8e-0313-44ad-bfe2-5f358d84f9d7');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('d6e47e8b-60fd-42f1-9086-5b6740ef3fa6', 20, 'da9d9c90-7467-4ded-8952-15d9c7cd668b', '62768a8e-0313-44ad-bfe2-5f358d84f9d7');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('e8a08fdc-4855-43a2-ad48-6d3924d46c60', 46, '6b0f5a04-4ad8-45b6-96b4-191abe808d5e', '9ec87b9c-1b50-401b-b9e8-20a4fe6693d3');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('c9b734c9-0718-4cdc-936b-ef2c56abce3d', 20, 'da9d9c90-7467-4ded-8952-15d9c7cd668b', '9ec87b9c-1b50-401b-b9e8-20a4fe6693d3');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('fdfce467-f473-43dc-860a-3f1f1b229b99', 9, '6aeb6450-cc08-4854-abcb-acbcd486822d', '9ec87b9c-1b50-401b-b9e8-20a4fe6693d3');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('7c4031b3-2408-46d7-88f4-cf993d298b90', 125, '022e462d-b25a-4758-93bc-2da567e2d166', '436f5d51-41d7-4ed8-9b5e-a9135f52a3a8');
INSERT INTO STOCK(id, quantity, product, seller) VALUES ('a0a53552-ec1a-4996-8f5d-e186c57f4447', 50, '1e2f4303-b4a6-4a97-9bbf-b06494d21e79', '436f5d51-41d7-4ed8-9b5e-a9135f52a3a8');

INSERT INTO ORDERED(id, customer) VALUES ('0c57142d-7356-461d-a004-10b9ccb4ccd0', 'f05157b5-e9fb-45d1-9242-4553bd742c0c');

INSERT INTO ORDEREDITEM(id, quantity, price, stock, ordered) VALUES ('dc0de9ac-7327-427b-b807-2aca760253c5', 1, 30.71, '5af07b82-e094-4759-aa4a-1962b28c0444', '0c57142d-7356-461d-a004-10b9ccb4ccd0');
INSERT INTO ORDEREDITEM(id, quantity, price, stock, ordered) VALUES ('f5e2acd4-8996-42bf-b8b9-e362a15cec43', 1, 39.49, '492d93eb-e15d-42ce-8370-aed31233bf9e', '0c57142d-7356-461d-a004-10b9ccb4ccd0');
INSERT INTO ORDEREDITEM(id, quantity, price, stock, ordered) VALUES ('91b5fc38-db54-4621-a786-994b29fa4e0a', 2, 2.99, 'c736525f-1b42-4e9d-ad75-8feea459ecad', '0c57142d-7356-461d-a004-10b9ccb4ccd0');

INSERT INTO BASKET(id, customer) VALUES ('52eb9f0e-7185-4763-8126-342a1edc580a', 'f05157b5-e9fb-45d1-9242-4553bd742c0c');

INSERT INTO BASKETITEM(id, quantity, price, stock, basket) VALUES ('4d7a9ba7-475e-4cab-bebb-ee195453b985', 11, 66.66, '5af07b82-e094-4759-aa4a-1962b28c0444', '52eb9f0e-7185-4763-8126-342a1edc580a');
INSERT INTO BASKETITEM(id, quantity, price, stock, basket) VALUES ('1ea73316-0ee9-40a1-be61-5d167c8e41ef', 21, 11.11, '492d93eb-e15d-42ce-8370-aed31233bf9e', '52eb9f0e-7185-4763-8126-342a1edc580a');
INSERT INTO BASKETITEM(id, quantity, price, stock, basket) VALUES ('c28ce999-d84e-48d6-8d6b-b8dabe217dae', 22, 2.22, 'c736525f-1b42-4e9d-ad75-8feea459ecad', '52eb9f0e-7185-4763-8126-342a1edc580a');
