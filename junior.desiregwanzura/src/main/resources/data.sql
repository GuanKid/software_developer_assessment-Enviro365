INSERT INTO INVESTOR (ID, NAME, AGE)
VALUES (1, 'John Smith', 67);

INSERT INTO PRODUCT (
    ID,
    PRODUCT_NAME,
    PRODUCT_TYPE,
    INVESTMENT_VALUE,
    INVESTOR_ID
)
VALUES (
           1,
           'Retirement Annuity',
           'RETIREMENT_ANNUITY',
           300000.00,
           1
       );

INSERT INTO PRODUCT (
    ID,
    PRODUCT_NAME,
    PRODUCT_TYPE,
    INVESTMENT_VALUE,
    INVESTOR_ID
)
VALUES (
           2,
           'Tax Free Savings',
           'TAX_FREE_SAVINGS',
           150000.00,
           1
       );
