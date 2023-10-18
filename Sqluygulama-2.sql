CREATE TABLE calisanlar
(
    id CHAR(5),
    name VARCHAR(50),
    maas int,
    CONSTRAINT calisanlar_id_pk PRIMARY KEY(id)
);

INSERT INTO calisanlar VALUES(10001, 'Ali Can', 12000);
INSERT INTO calisanlar VALUES(10002, 'Veli Han', 2000);
INSERT INTO calisanlar VALUES(10003, 'Mary Star', 7000);
INSERT INTO calisanlar VALUES(10004, 'Angie Ocean', 8500);

SELECT * FROM calisanlar;
-- 1) En yüksek maas
SELECT MAX(MAAS) FROM calisanlar

-- 2) En düşük maaş
SELECT MIN(MAAS) FROM calisanlar        


-- 3) En yüksek 2. maaş
SELECT MAX(MAAS) FROM calisanlar
WHERE maas<(SELECT MAX(MAAS) FROM calisanlar)

-- 4) En düşük 2. maaş
SELECT MIN(MAAS) FROM calisanlar 
WHERE maas>(SELECT MIN(MAAS) FROM calisanlar )

-- 5) En yüksek 3. maaş
SELECT maas
FROM calisanlar 
ORDER BY maas DESC
OFFSET 2
LIMIT 1

-- 6) En düşük 3. maaş
SELECT maas
FROM calisanlar 
ORDER BY maas 
OFFSET 2
LIMIT 1

-- 7) Interview Question: En yüksek maaş alan çalışan bilgileri
SELECT * FROM calisanlar
WHERE maas=(SELECT MAX(MAAS) FROM calisanlar )
 
                         
-- 8) Interview Question: En düşük maaş alan calısan bilgileri 
SELECT * FROM calisanlar
WHERE maas=(SELECT MIN(MAAS) FROM calisanlar)


-- 9) En yüksek 3. maaşı alan calisan bilgileri
SELECT * FROM calisanlar
WHERE maas=(SELECT maas
			FROM calisanlar 
			ORDER BY maas DESC
			OFFSET 2
			LIMIT 1)

