INSERT INTO users (name, email, password) VALUES ('Marcos', 'marcos@email.com', '$2a$10$C1q4jBhTYRVgQMxHl9sgHOfwxkslGA4LPp1OmOmwf9UkLdC1ddXWC');
INSERT INTO users (name, email, password) VALUES ('José', 'jose@email.com', '123jose');

INSERT INTO campings (name, state, city, address, description, contact, id_user) VALUES ('Acampamento Floresta', 'SP', 'Eldorado', 'Rua Salgueiro, 22', 'Árvores e mata', 11987654321, 1);
INSERT INTO campings (name, state, city, address, description, contact, id_user) VALUES ('Acampamento Montanha', 'RJ', 'Parati', 'Tenente José Augusto Mendes Peixoto, 1500', 'Montanha', 11987656532, 1);
INSERT INTO campings (name, state, city, address, description, contact, id_user) VALUES ('Acampamento Areia', 'SP', 'Colinas', 'Praia Uberaba', 'Dunas de areia', 11911156532, 2);

INSERT INTO images (name, url, id_camping) VALUES ('Business-HQ.jpg', 'https://www.googleapis.com/download/storage/v1/b/camping-fire/o/Business-HQ.jpg?generation=1598925247898644&alt=media', 1);
INSERT INTO images (name, url, id_camping) VALUES ('Business-HQ.jpg', 'https://www.googleapis.com/download/storage/v1/b/camping-fire/o/Business-HQ.jpg?generation=1598925247898644&alt=media', 2);
INSERT INTO images (name, url, id_camping) VALUES ('Business-HQ.jpg', 'https://www.googleapis.com/download/storage/v1/b/camping-fire/o/Business-HQ.jpg?generation=1598925247898644&alt=media', 3);

INSERT INTO roles (name) VALUES ('admin');
INSERT INTO users_roles (id_user, id_role) VALUES (1, 1);
