CREATE SCHEMA tasker;

CREATE TABLE tasker.boards (
                               "id" SERIAL PRIMARY KEY,
                               "title" varchar,
                               "created_at" timestamp,
                               "description" varchar
);

CREATE TABLE tasker.lists (
                              "id" SERIAL PRIMARY KEY,
                              "title" varchar,
                              "board_id" int
);

CREATE TABLE tasker.cards (
                              "id" SERIAL PRIMARY KEY,
                              "list_id" int,
                              "board_id" int,
                              "title" varchar,
                              "description" varchar,
                              "priority" int,
                              "due_date" date
);

CREATE TABLE tasker.labels (
                               "id" SERIAL PRIMARY KEY,
                               "board_id" int,
                               "title" varchar,
                               "color" varchar
);

CREATE TABLE tasker.cards_labels (
                                     "card_id" int,
                                     "label_id" int
);

ALTER TABLE tasker.lists ADD FOREIGN KEY ("board_id") REFERENCES boards ("id");

ALTER TABLE tasker.cards ADD FOREIGN KEY ("board_id") REFERENCES boards ("id");

ALTER TABLE tasker.cards ADD FOREIGN KEY ("list_id") REFERENCES lists ("id");

ALTER TABLE tasker.labels ADD FOREIGN KEY ("board_id") REFERENCES boards ("id");

ALTER TABLE tasker.cards_labels ADD FOREIGN KEY ("label_id") REFERENCES labels ("id");

ALTER TABLE tasker.cards_labels ADD FOREIGN KEY ("card_id") REFERENCES cards ("id");
