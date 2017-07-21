SET SERVEROUTPUT ON
/*3.1 System Defined Functions */
CREATE OR REPLACE FUNCTION currentTime
RETURN TIMESTAMP AS
    BEGIN
        RETURN SYSTIMESTAMP;
    END;
/
    
SELECT CURRENTTIME FROM DUAL;
SELECT SYSTIMESTAMP FROM DUAL;

CREATE OR REPLACE FUNCTION getLength(NAME IN VARCHAR2)
RETURN NUMBER AS
BEGIN
   RETURN LENGTH(NAME);
END;
/

DECLARE
    NAME VARCHAR2(255);
    V_RETURN NUMBER;
BEGIN
    SELECT MAX(NAME) INTO NAME FROM MEDIATYPE;
    V_RETURN := GETLENGTH(NAME);
    DBMS_OUTPUT.PUT_LINE(V_RETURN);
END;
/

/*3.2 System Defined Aggregate Functions */
CREATE OR REPLACE FUNCTION AVG_TOTAL(TOTAL IN NUMBER)
RETURN NUMBER AS
T_SUM NUMBER;
T_COUNT NUMBER;
BEGIN
    SELECT SUM(TOTAL) INTO T_SUM FROM INVOICE;
    SELECT COUNT(TOTAL) INTO T_COUNT FROM INVOICE;
    RETURN T_SUM/T_COUNT;
END;
/

SELECT AVG_TOTAL(TOTAL) FROM INVOICE;--DOES WORK
DECLARE--DOESN'T WORK
    I_TOTAL NUMBER;
    V_RETURN NUMBER;
BEGIN
    FOR I IN(SELECT TOTAL INTO I_TOTAL FROM INVOICE)
    LOOP
        I_TOTAL := I.TOTAL;
    END LOOP;
    V_RETURN := AVG_TOTAL(I_TOTAL);
    DBMS_OUTPUT.PUT_LINE(V_RETURN);
END;
/

CREATE OR REPLACE FUNCTION MOST_EXPENSIVE(PRICE IN NUMBER)
RETURN NUMBER AS
P_RETURN NUMBER;
BEGIN
    SELECT MAX(PRICE) INTO P_RETURN FROM TRACK;
    RETURN P_RETURN;
END;
/

SELECT MOST_EXPENSIVE(UNITPRICE) FROM TRACK;
    