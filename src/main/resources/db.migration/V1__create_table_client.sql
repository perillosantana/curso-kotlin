CREATE TABLE client (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    created_in TIMESTAMP DEFAULT NOW(),
    last_interaction_in TIMESTAMP DEFAULT NOW()
);

INSERT INTO client (name, email) VALUES ('PERILLO', 'PERILLO@GMAIL.COM');