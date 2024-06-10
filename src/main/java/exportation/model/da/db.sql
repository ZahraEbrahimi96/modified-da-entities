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
    PERSON_POSITION     nvarchar2(30)
);
create sequence PERSON_SEQ start with 1 increment by 1;

--COUNTRY
create table COUNTRY_TABLE
(
    COUNTRY_ID            number primary key,
    COUNTRY_NAME          nvarchar2(30),
    COUNTRY_PHONE_CODE    nvarchar2(4),
    COUNTRY_RELATEDMARKET nvarchar2(30)
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
    COMPANY_PHONE_NUMBER nvarchar2(11)
);
create sequence COMPANY_SEQ start with 1 increment by 1;

--TRADE
create table TRADE_TABLE
(
    TRADE_ID              number primary key,
    TRADE_STATUS          nvarchar2(30),
    TRADE_CORRESPONDENCES nvarchar2(500),
    TRADE_CONTRACT        nvarchar2(500),
    TRADE_AGREEMENT       nvarchar2(500),
    TRADE_INVOICE         nvarchar2(500)
);
create sequence TRADE_SEQ start with 1 increment by 1;

--PAYMENT
create table PAYMENT_TABLE
(
    PAYMENT_ID         number primary key,
    PAYMENT_TOTAL_COST number,
    PAYMENT_TAX        number,
    PAYMENT_INSURANCE  number,
    PAYMENT_COST references ITEM_TABLE,
    PAYMENT_FREIGHT references TRANSPORTATION_TABLE,
    PAYMENT_TARIFF references INFO_TABLE
);
create sequence PAYMENT_SEQ start with 1 increment by 1;

--ITEM
create table ITEM_TABLE
(
    ITEM_ID             number primary key,
    PALLET_CAPACITY     number,
    ITEM_NAME           nvarchar2(40),
    ITEM_BRAND          nvarchar2(40)
        CHECK ( ITEM_BRAND IN ('hipile', 'carpile', 'handle', 'tino')),
    ITEM_MODEL          nvarchar2(40),
    DIMENSION_OF_UNIT   nvarchar2(40),
    DIMENSION_OF_PALLET nvarchar2(40),
    ITEM_HS_CODE        number,
    WEIGHT_OF_UNIT      number,
    WEIGHT_OF_PALLET    number,
    ITEM_COST           number
);
create sequence ITEM_SEQ start with 1 increment by 1;

--TRANSPORTATION
create table TRANSPORTATION_TABLE
(
    TRANSPORTATION_ID        number primary key,
    TRANSPORTATION_DIRECTION nvarchar2(30),
    TRANSPORTATION_ORIGIN    nvarchar2(30),
    TRANSPORTATION_FREIGHT   number
);
create sequence TRANSPORTATION_SEQ start with 1 increment by 1;

--IMPORTS
create table IMPORTS_TABLE
(
    IMPORTS_ID        number primary key,
    IMPORTS_HS_CODE   number,
    IMPORTS_QUANTITY  number,
    IMPORTS_USD_VALUE number
);
create sequence IMPORTS_SEQ start with 1 increment by 1;

--EXPORTS
create table EXPORTS_TABLE
(
    EXPORTS_ID        number primary key,
    EXPORTS_HS_CODE   number,
    EXPORTS_QUANTITY  number,
    EXPORTS_USD_VALUE number
);
create sequence EXPORTS_SEQ start with 1 increment by 1;

--EXPORT_TRACING
create table EXPORT_TRACING_TABLE
(
    TRACING_ID             number primary key,
    TRACING_LOADING_STATUS char check (TRACING_LOADING_STATUS in (0, 1)),
    TRACING_INVOICE        nvarchar2(100),
    TRACING_WAYBILL        nvarchar2(100),
    TRACING_PREPAYMENT     char check (TRACING_PREPAYMENT in (0, 1)),
    TRACING_CHECKOUT       char check (TRACING_CHECKOUT in (0, 1))
);
create sequence EXPORT_TRACING_SEQ start with 1 increment by 1;

--INFO
create table INFO_TABLE
(
    INFO_ID         number primary key,
    INFO_POPULATION number,
    INFO_CAR_RATE   number,
    INFO_CLIMATE    nvarchar2(30),
    INFO_DEMAND     nvarchar2(30),
    INFO_TARIFF     nvarchar2(4)
);
create sequence INFO_SEQ start with 1 increment by 1;

--ACCESSPATH
CREATE TABLE ACCESS_PATH_TABLE
(
    ACCESS_PATH_ID       number primary key,
    ACCESS_PATH_CITY     nvarchar2(20),
    ACCESS_PATH_TYPE     nvarchar2(20),
    CHECK ( ACCESS_PATH_TYPE IN ('air', 'rail', 'road', 'sea')),
    ACCESS_PATH_DISTANCE number,
    ACCESS_NAVIGATION    nvarchar2(20) CHECK ( ACCESS_NAVIGATION IN
                                               ('north', 'northeast', 'northwest', 'south', 'southeast', 'southwest',
                                                'east', 'west'))
);
create sequence ACCESSPATH_SEQ start with 1 increment by 1;

--MANUFACTURE
create table MANUFACTURER_TABLE
(
    MANUFACTURER_ID      number primary key,
    MANUFACTURER_NAME    nvarchar2(30),
    MANUFACTURER_PRODUCT nvarchar2(30),
    MANUFACTURER_ADDRESS nvarchar2(300),
    MANUFACTURER_EMAIL   nvarchar2(250),
    MANUFACTURER_PHONE   nvarchar2(30),
    PRODUCTION_RATE      number
);
create sequence MANUFACTURE_SEQ start with 1 increment by 1;

--SUPPLIER
create table SUPPLIER_TABLE
(
    SUPPLIER_ID      number primary key,
    SUPPLIER_NAME    nvarchar2(30),
    SUPPLIER_PRODUCT nvarchar2(30),
    SUPPLIER_ADDRESS nvarchar2(300),
    SUPPLIER_EMAIL   nvarchar2(250),
    SUPPLIER_PHONE   nvarchar2(30),
    ONLINE_SALE      char check (ONLINE_SALE in (0, 1))
);
create sequence SUPPLIER_SEQ start with 1 increment by 1;

