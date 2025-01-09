CREATE TABLE users (
    u_id BIGINT PRIMARY KEY,
    u_name VARCHAR(255) NOT NULL,
    u_avatar VARCHAR(255),
    u_email VARCHAR(255) NOT NULL UNIQUE,
    u_username VARCHAR(255) NOT NULL UNIQUE,
    u_password VARCHAR(255) NOT NULL,
    u_role VARCHAR(255) NOT NULL,
    u_phone_number VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE companies (
    c_id BIGINT PRIMARY KEY,
    c_name VARCHAR(255) NOT NULL,
    c_tax_number VARCHAR(255) NOT NULL UNIQUE,
    c_registration_number VARCHAR(255) NOT NULL UNIQUE,
    c_bank_account_number VARCHAR(255) NOT NULL,
    c_bank_name VARCHAR(255) NOT NULL,
    c_email VARCHAR(255) NOT NULL,
    c_phone_number VARCHAR(20) NOT NULL,
    c_address VARCHAR(255) NOT NULL,
    c_verify_status INT NOT NULL,
    c_user_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (c_user_id) REFERENCES users(u_id)
);

CREATE TABLE events (
    e_id BIGINT PRIMARY KEY,
    e_name VARCHAR(255) NOT NULL,
    e_description TEXT NOT NULL,
    e_start_time TIMESTAMP NOT NULL,
    e_end_time TIMESTAMP NOT NULL,
    e_type VARCHAR(255) NOT NULL,
    e_address VARCHAR(255) NOT NULL,
    e_map_url TEXT,
    e_status INT NOT NULL,
    e_introduction JSONB,
    e_thumbnail VARCHAR(255),
    e_company_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (e_company_id) REFERENCES companies(c_id) ON DELETE CASCADE
);

CREATE TABLE venues (
    v_id BIGINT PRIMARY KEY,
    v_name VARCHAR(255) NOT NULL,
    v_capacity INT NOT NULL,
    v_position VARCHAR(255) NOT NULL,
    v_width FLOAT,
    v_height FLOAT,
    v_radius FLOAT,
    v_shape INT NOT NULL,
    v_description VARCHAR(255),
    v_event_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (v_event_id) REFERENCES events(e_id)
);

CREATE TABLE sections (
    s_id BIGINT PRIMARY KEY,
    s_venue_id BIGINT NOT NULL,
    s_name VARCHAR(255) NOT NULL,
    s_capacity INT NOT NULL,
    s_shape INT NOT NULL,
    s_x_position FLOAT NOT NULL,
    s_y_position FLOAT NOT NULL,
    s_width FLOAT DEFAULT NULL,
    s_height FLOAT DEFAULT NULL,
    s_radius FLOAT DEFAULT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (s_venue_id) REFERENCES venues(v_id) ON DELETE CASCADE
);

CREATE TABLE ticket_types (
    tt_id BIGINT PRIMARY KEY,
    tt_name VARCHAR(255) NOT NULL,
    tt_price DECIMAL(10, 2) NOT NULL,
    tt_quantity INT NOT NULL,
    tt_benefit JSONB,
    tt_event_id BIGINT NOT NULL,
    tt_section_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tt_event_id) REFERENCES events(e_id)
);

CREATE TABLE transactions (
    tr_id BIGINT PRIMARY KEY,
    tr_ticket_id BIGINT NOT NULL,
    tr_buyer_id BIGINT NOT NULL,
    tr_amount DECIMAL(10, 2) NOT NULL,
    tr_status INT NOT NULL,
    tr_payment_method VARCHAR(255) NOT NULL,
    tr_transaction_code VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tr_ticket_id) REFERENCES ticket_types(tt_id),
    FOREIGN KEY (tr_buyer_id) REFERENCES users(u_id)
);

CREATE TABLE tickets (
    t_id BIGINT PRIMARY KEY,
    t_status INT NOT NULL,
    t_event_id BIGINT NOT NULL,
    t_buyer_id BIGINT NOT NULL,
    t_transaction_id BIGINT NOT NULL,
    t_ticket_type_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (t_event_id) REFERENCES events(e_id),
    FOREIGN KEY (t_buyer_id) REFERENCES users(u_id),
    FOREIGN KEY (t_transaction_id) REFERENCES transactions(tr_id),
    FOREIGN KEY (t_ticket_type_id) REFERENCES ticket_types(tt_id)
);
