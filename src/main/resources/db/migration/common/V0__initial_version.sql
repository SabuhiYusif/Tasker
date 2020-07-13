CREATE SCHEMA tasker;

CREATE TABLE tasker.boards
(
    id          uuid PRIMARY KEY,
    title       varchar(50) NOT NULL,
    created_at  timestamptz NOT NULL,
    description varchar(2000)
);

CREATE TABLE tasker.board_columns
(
    id       uuid PRIMARY KEY,
    title    varchar(50) NOT NULL,
    board_id uuid REFERENCES tasker.boards (id) ON DELETE CASCADE
);

CREATE TABLE tasker.cards
(
    id          uuid PRIMARY KEY,
    column_id   uuid REFERENCES tasker.board_columns (id) ON DELETE CASCADE,
    board_id    uuid REFERENCES tasker.boards (id) ON DELETE CASCADE,
    title       varchar(50) NOT NULL,
    description varchar(2000),
    priority    int,
    due_date    timestamptz
);

CREATE TABLE tasker.board_labels
(
    id       uuid PRIMARY KEY,
    board_id uuid REFERENCES tasker.boards (id) ON DELETE CASCADE,
    title    varchar(50) NOT NULL,
    color    varchar(50)
);

CREATE TABLE tasker.cards_labels
(
    card_id  uuid REFERENCES tasker.cards (id) ON DELETE CASCADE,
    label_id uuid REFERENCES tasker.board_labels (id) ON DELETE CASCADE
);
