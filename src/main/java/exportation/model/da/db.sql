create table Person
(
    id          number primary key,
    name        nvarchar2(30),
    family      nvarchar2(30),
    gender      varchar2(6),
    email       nvarchar2(30),
    phoneNumber nvarchar2(11),
    city        nvarchar2(20),
    nationalId  nvarchar2(10),
    position    nvarchar2(30),
    address     nvarchar(250)
);

create table Company
(
    id          number primary key,
    name        nvarchar2(30),
    type        nvarchar2(30),
    product     nvarchar2(30),
    address     nvarchar2(250),
    email       nvarchar2(30),
    phoneNumber nvarchar2(11),
);

create table Country
(
    id             number primary key,
    name           nvarchar2(30),
    phoneCode      nvarchar2(4),
    realatedMarket nvarchar2(30),
);

create table Item
(
    id                number primary key,
    name              nvarchar2(30),
    model             nvarchar2(30),
    dimensionOfUnite  nvarchar2(30),
    dimensionOfPallet nvarchar2(30),
    weightOfUnit      number,
    weightOfPallet    number,
    palletCapacity    number,
    cost              number,
);

