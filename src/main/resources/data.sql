INSERT INTO USERS (USERNAME, PASSWORD, ROLE, ENABLED)
VALUES
    ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN', TRUE),
    ('regular', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'REGULAR', TRUE);

INSERT INTO CUSTOMER (NAME, EMAIL)
VALUES
    ('Ash Ketchum', 'ash@example.com'),
    ('Yugi Muto', 'yugi@example.com'),
    ('Luffy D. Monkey', 'luffy@example.com');

INSERT INTO PRODUCT (NAME, CARD_GAME, CATEGORY, PRICE, STOCK, STATUS)
VALUES
    ('Scarlet & Violet Booster Pack', 'POKEMON', 'BOOSTER_PACK', 4.99, 30, 'ACTIVE'),
    ('Paldea Evolved Elite Trainer Box', 'POKEMON', 'ELITE_TRAINER_BOX', 49.99, 10, 'ACTIVE'),
    ('Charizard ex Special Collection', 'POKEMON', 'SPECIAL_COLLECTION', 39.99, 8, 'ACTIVE'),
    ('Modern Horizons III Play Booster', 'MAGIC_THE_GATHERING', 'BOOSTER_PACK', 8.99, 20, 'ACTIVE'),
    ('Commander Starter Deck', 'MAGIC_THE_GATHERING', 'PRECONSTRUCTED_DECK', 24.99, 12, 'ACTIVE'),
    ('Lorcana First Chapter Sleeves', 'LORCANA', 'SLEEVES', 11.99, 15, 'ACTIVE'),
    ('Lorcana Deck Box', 'LORCANA', 'DECK_BOX', 6.99, 18, 'ACTIVE'),
    ('One Piece OP-05 Booster Pack', 'ONE_PIECE', 'BOOSTER_PACK', 5.49, 25, 'ACTIVE'),
    ('One Piece Starter Deck Luffy', 'ONE_PIECE', 'PRECONSTRUCTED_DECK', 17.99, 14, 'ACTIVE'),
    ('Pokemon 9-Pocket Binder', 'POKEMON', 'BINDER', 19.99, 9, 'ACTIVE');