CREATE SCHEMA tasker;

CREATE TABLE tasker.boards
(
    id          uuid PRIMARY KEY,
    title       varchar(50),
    created_at  timestamptz,
    description varchar(2000)
);

CREATE TABLE tasker.columns
(
    id       uuid PRIMARY KEY,
    title    varchar(50),
    board_id uuid REFERENCES boards (id) ON DELETE CASCADE
);

CREATE TABLE tasker.cards
(
    id          uuid PRIMARY KEY,
    column_id   uuid REFERENCES columns (id) ON DELETE CASCADE,
    board_id    uuid REFERENCES boards (id) ON DELETE CASCADE,
    title       varchar(50),
    description varchar(2000),
    priority    int,
    due_date    date
);

CREATE TABLE tasker.board_labels
(
    id       uuid PRIMARY KEY,
    board_id uuid REFERENCES boards (id) ON DELETE CASCADE,
    title    varchar(50),
    color    varchar
);

CREATE TABLE tasker.cards_labels
(
    card_id  uuid REFERENCES cards (id) ON DELETE CASCADE,
    label_id uuid REFERENCES board_labels (id) ON DELETE CASCADE
);
