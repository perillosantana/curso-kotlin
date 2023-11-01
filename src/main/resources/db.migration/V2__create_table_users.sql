CREATE TABLE users (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    login TEXT NOT NULL,
    password TEXT NOT NULL,
    created_in TIMESTAMP DEFAULT NOW(),
    last_interaction_in TIMESTAMP DEFAULT NOW()
)