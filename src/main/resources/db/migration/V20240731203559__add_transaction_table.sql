create table transaction (
    id uuid not null default gen_random_uuid(),
    account_id uuid not null,
    total_amount numeric(10,2) not null,
    mcc varchar not null,
    merchant varchar not null,
    code varchar not null,

    constraint transaction_pk primary key (id)
)