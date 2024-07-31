create table account (
    id uuid not null default gen_random_uuid(),
    cash numeric(10,2) not null default 0,
    food numeric(10,2) not null default 0,
    meal numeric(10,2) not null default 0,

    constraint account_pk primary key (id)
)