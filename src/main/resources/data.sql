INSERT INTO CUSTOMER(id, firstname, gender, lastname) VALUES ('f05157b5-e9fb-45d1-9242-4553bd742c0c', 'Arthur', 'Man', 'Cuvit');

INSERT INTO SELLER(id, name)	VALUES (1, 'Philibert');
INSERT INTO SELLER(id, name)	VALUES (2, 'Space Cowboys');
INSERT INTO SELLER(id, name)	VALUES (3, 'Domino');
INSERT INTO SELLER(id, name)	VALUES (4, 'Saturn');
INSERT INTO SELLER(id, name)	VALUES (5, 'Ikea');

INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES (1, 'Unlock ! Game Adventures', 30.71, 'Jeux', 'Dans Unlock! Games Adventures, plongez dans l univers de Mysterium, Aventuriers du Rail et Pandemic');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES (2, 'Thorgun', 2.99, 'Inconnu', 'Plaid, gris-vert clair120x160 cm');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES (3, 'GODMORGON / ODENSVIK', 559.00, 'Meuble', 'Meuble lavabo 4tir, effet chêne blanchi/Dalskär mitigeur lavabo123x49x64 cm');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES (4, 'Clank! - Legacy', 85.48, 'Jeux', 'Pénétrez dans l univers fantastique de Clank à travers une campagne de plusieurs parties. Après celle-ci, le jeu reste entièrement rejouable.');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES (5, '7 Wonders', 39.49, 'Jeux', 'Cette boîte est la 2ème édition de ce jeu devenu un classique');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES (6, 'Les Aventuriers du Rail', 41.88, 'Jeux', 'Prenez le contrôle du réseau ferroviaire américain en reliant un maximum de villes entre elles.');
INSERT INTO PRODUCT(id, name, price, categorie, description) VALUES (7, 'Carcassonne : 20th Anniversary Edition Limitée', 35.10, 'Jeux', ' Construisez un paysage à l’aide de tuiles, puis tentez de contrôler un maximum de territoires. un jeu où la stratégie s allie à la simplicité.');

INSERT INTO STOCK(id, quantity, product, seller) VALUES (1, 10, 1, 1);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (2, 50, 4, 1);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (3, 56, 5, 1);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (4, 80, 6, 1);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (5, 25, 7, 1);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (6, 10, 1, 2);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (7, 12, 4, 2);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (8, 1, 5, 2);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (9, 20, 6, 2);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (10, 46, 5, 3);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (11, 20, 6, 3);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (12, 9, 7, 3);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (13, 125, 2, 5);
INSERT INTO STOCK(id, quantity, product, seller) VALUES (14, 50, 3, 5);