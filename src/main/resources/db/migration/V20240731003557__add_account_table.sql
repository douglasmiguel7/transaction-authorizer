create table account (
    id uuid not null default gen_random_uuid(),
    cash numeric(10,2),
    food numeric(10,2),
    meal numeric(10,2),

    constraint account_pk primary key (id)
)