CREATE TABLE events_user_view (
    event_id BIGINT,
    user_id BIGINT,
    times INT DEFAULT 0,
    lastView TIMESTAMP,
    PRIMARY KEY (event_id, user_id),
    FOREIGN KEY (user_id) REFERENCES users(u_id) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events(e_id) ON DELETE CASCADE
);
