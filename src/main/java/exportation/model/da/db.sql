-- PERSON
create table PERSON_TABLE
(
    PERSON_ID           number primary key,
    PERSON_NAME         nvarchar2(30),
    PERSON_FAMILY       nvarchar2(30),
    PERSON_GENDER       nvarchar2(10) CHECK ( PERSON_GENDER IN ('male', 'female')),
    NATIONAL_ID         nvarchar2(10),
    PERSON_PHONE_NUMBER nvarchar2(11),
    PERSON_EMAIL        nvarchar2(300),
    PERSON_ADDRESS      nvarchar2(300),
    PERSON_POSITION     nvarchar2(30),
    USER_ID references USER_TABLE
);
create sequence PERSON_SEQ start with 1 increment by 1;

--USER
create table USER_TABLE
(
    USER_ID       number primary key,
    USER_NAME     nvarchar2(30) unique,
    USER_PASSWORD nvarchar2(30),
    USER_ENABLED  number(1)
);
create sequence USER_SEQ start with 1 increment by 1;

--COUNTRY
create table COUNTRY_TABLE
(
    COUNTRY_ID         number primary key,
    COUNTRY_TARIFF     nvarchar2(4),
    COUNTRY_NAME       nvarchar2(30),
    COUNTRY_POPULATION number,
    COUNTRY_CAR_RATE   number,
    COUNTRY_PHONE_CODE nvarchar2(4),
    SUPPLIER_ID references SUPPLIER_TABLE,
    MANUFACTURER_ID references MANUFACTURER_TABLE,
    COUNTRY_NEIGHBORS  nvarchar2(30)
);
create sequence COUNTRY_SEQ start with 1 increment by 1;

--COMPANY
create table COMPANY_TABLE
(
    COMPANY_ID           number primary key,
    COMPANY_NAME         nvarchar2(30),
    COMPANY_PRODUCT      nvarchar2(30),
    COMPANY_ADDRESS      nvarchar2(250),
    COMPANY_EMAIL        nvarchar2(30),
    COMPANY_PHONE_NUMBER nvarchar2(11),
    PERSON_ID references PERSON_TABLE,
    COUNTRY_ID references COUNTRY_TABLE
);
create sequence COMPANY_SEQ start with 1 increment by 1;

--TRADE
create table TRADE_TABLE
(
    TRADE_ID              number primary key,
    TRADE_CLIENT references PERSON_TABLE,
    TRADE_STATUS          nvarchar2(30),
    TRADE_CORRESPONDENCES nvarchar2(500),
    TRADE_CONTRACT        nvarchar2(500),
    TRADE_AGREEMENT       nvarchar2(500)
);
create sequence TRADE_SEQ start with 1 increment by 1;

--PAYMENT
create table PAYMENT_TABLE
(
    PAYMENT_ID        number primary key,
    PAYMENT_TAX       number,
    PAYMENT_INSURANCE number,
    ITEM_ID references ITEM_TABLE,
    TRANSPORTATION_ID references TRANSPORTATION_TABLE,
    COUNTRY_ID references COUNTRY_TABLE
);
create sequence PAYMENT_SEQ start with 1 increment by 1;

--ITEM
create table ITEM_TABLE
(
    ITEM_ID             number primary key,
    ITEM_NAME           nvarchar2(40),
    ITEM_BRAND          nvarchar2(40) CHECK ( ITEM_BRAND IN ('hipile', 'carpile', 'handle', 'tino')),
    ITEM_MODEL          nvarchar2(40),
    DIMENSION_OF_UNIT   nvarchar2(40),
    DIMENSION_OF_PALLET nvarchar2(40),
    PALLET_CAPACITY     number,
    ITEM_HS_CODE        number,
    ITEM_COST           number,
    WEIGHT_OF_UNIT      number,
    WEIGHT_OF_PALLET    number,
    ITEM_AMPER          number
);
create sequence ITEM_SEQ start with 1 increment by 1;

--TRANSPORTATION
create table TRANSPORTATION_TABLE
(
    TRANSPORTATION_ID        number primary key,
    TRANSPORTATION_DIRECTION nvarchar2(30),
    TRANSPORTATION_FREIGHT   number,
    ITEM_ID references ITEM_TABLE,
    COUNTRY_ID references COUNTRY_TABLE,
    EXPORT_ID references EXPORT_TRACING_TABLE
);
create sequence TRANSPORTATION_SEQ start with 1 increment by 1;

--EXPORT_TRACING
create table EXPORT_TRACING_TABLE
(
    EXPORT_ID            number primary key,
    EXPORT_LOADINGSTATUS number(1),
    EXPORT_PREPAYMENT    number(1),
    EXPORT_CHECKOUT      number(1),
    TRANSPORTATION_ID references TRANSPORTATION_TABLE,
    TRADE_ID references TRADE_TABLE
);
create sequence EXPORT_TRACING_SEQ start with 1 increment by 1;

--MANUFACTURE
create table MANUFACTURER_TABLE
(
    MANUFACTURER_ID      number primary key,
    MANUFACTURER_NAME    nvarchar2(30),
    MANUFACTURER_PRODUCT nvarchar2(30),
    MANUFACTURER_ADDRESS nvarchar2(300),
    MANUFACTURER_PHONE   nvarchar2(30),
    MANUFACTURER_EMAIL   nvarchar2(250),
    PRODUCTION_RATE      number,
    COUNTRY_ID references COUNTRY_TABLE,
    PERSON_ID references PERSON_TABLE
);
create sequence MANUFACTURE_SEQ start with 1 increment by 1;

--SUPPLIER
create table SUPPLIER_TABLE
(
    SUPPLIER_ID      number primary key,
    SUPPLIER_NAME    nvarchar2(30),
    SUPPLIER_PRODUCT nvarchar2(30),
    SUPPLIER_ADDRESS nvarchar2(300),
    SUPPLIER_PHONE   nvarchar2(30),
    SUPPLIER_EMAIL   nvarchar2(250),
    SUPPLIER_COUNTRY references COUNTRY_TABLE,
    ONLINE_SALE      number(1),
    PERSON_ID references PERSON_TABLE,
    COUNTRY_ID references COUNTRY_TABLE
);
create sequence SUPPLIER_SEQ start with 1 increment by 1;

