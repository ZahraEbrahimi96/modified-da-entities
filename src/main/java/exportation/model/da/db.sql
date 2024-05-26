create table person_table
(
    person_id          number primary key,
    person_name        nvarchar2(30),
    person_family      nvarchar2(30),
    person_gender      varchar2(6),
    person_email       nvarchar2(30),
    person_phoneNumber nvarchar2(11),
    person_city        nvarchar2(20),
    person_nationalId  nvarchar2(10),
    person_position    nvarchar2(30),
    person_address     nvarchar2(30)
);

create table company_table
(
    company_id           number primary key,
    company_name         nvarchar2(30),
    company_type         nvarchar2(30),
    company_product      nvarchar2(30),
    company_address      nvarchar2(250),
    company_email        nvarchar2(30),
    company_phone_number nvarchar2(11)
);

create table country_table
(
    country_id             number primary key,
    country_name           nvarchar2(30),
    country_phone_code     nvarchar2(4),
    country_related_market nvarchar2(30)
);

create table trade_table
(
    trade_id              number primary key,
    trade_client          nvarchar2(30),
    trade_status          nvarchar2(30),
    trade_correspondences nvarchar2(30),
    trade_contract        nvarchar2(30),
    trade_agreement       nvarchar2(30),
    trade_invoice         nvarchar2(30)
);

create table payment_table
(
    payment_id         number primary key,
    payment_total_cost number,
    payment_tax        number,
    payment_insurance   number
);

create table itemTable
(
    item_id                number primary key,
    item_name              nvarchar2(30),
    item_model             nvarchar2(30),
    item_dimensionOfUnite  nvarchar2(30),
    item_dimensionOfPallet nvarchar2(30),
    item_weightOfUnit      number,
    item_weightOfPallet    number,
    item_palletCapacity    number,
    item_cost              number
);

create table country_company
(
    int number primary key,
    c_country_id references country_table,
    c_company_id references company_table
);

create view country_company_report as
select *
from country_table
         join country_company on country_table.country_id = country_company.c_country_id
         join company_table on company_table.company_id = country_company.c_company_id

-- todo : create sequences