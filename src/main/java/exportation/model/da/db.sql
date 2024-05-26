create table person_table
(
    person_id          number primary key,
    name        nvarchar2(30),
    family      nvarchar2(30),
    gender      varchar2(6),
    email       nvarchar2(30),
    phoneNumber nvarchar2(11),
    city        nvarchar2(20),
    nationalId  nvarchar2(10),
    position    nvarchar2(30),
    address     nvarchar2(30)
);

create table company_table
(
    company_id          number primary key,
    name        nvarchar2(30),
    type        nvarchar2(30),
    product     nvarchar2(30),
    address     nvarchar2(250),
    email       nvarchar2(30),
    phone_number nvarchar2(11)
);

create table country_table
(
    country_id             number primary key,
    name           nvarchar2(30),
    phone_code      nvarchar2(4),
    related_market nvarchar2(30)
);

create table itemTable
(
    item_id                number primary key,
    name              nvarchar2(30),
    model             nvarchar2(30),
    dimensionOfUnite  nvarchar2(30),
    dimensionOfPallet nvarchar2(30),
    weightOfUnit      number,
    weightOfPallet    number,
    palletCapacity    number,
    cost              number
);

create table country_company(
    int number primary key,
    c_country_id references country_table,
    c_company_id references company_table
);

create view country_company_report as
select * from country_table
join country_company  on country_table.id = country_company.c_country_id
join company_table  on company_table.id = country_company.c_company_id

-- todo : create sequences