-- SQL UYGULAMA
/*
Aşağıdaki tabloyu SQL kodlarını kullanarak oluşturunuz.
+----+-------+-----+--------+------------------+
| id | isim  | yas | maas   |  email		   | 
+----+-------+-----+--------+------------------+
| 1  | Ali   | 39  | 12500  |ali@gmail.com	   |
| 2  | Derya | 28  | 15000  |derya@yahoo.com   |
| 3  | Sevim | 24  | 25000  |sevim@hotmail.com |
| 4  | Yusuf | 32  | 18000  |yusuf@gmail.com   |
| 5  | Halil | 48  | 22000  |halil@gmail.com   |
| 6  | Ece   | 54  | 21000  |ece@gmail.com     |
| 7  | Can   | 38  | 19000  |can@gmail.com     |
| 8  | Elif  | 27  | 14000  |elif@gmail.com    |
| 9  | Ezgi  | 35  | 21000  |ezgi@gmail.com    |
+----+-------+-----+--------+------------------+
*/
CREATE TABLE calisanlar1(
id SERIAL,
isim VARCHAR(15),
yas int,
maas int,
mail VARCHAR(40)
)
INSERT INTO calisanlar1(isim,yas,maas,mail)
VALUES
('Ali' , 39  , 12500  ,'ali@gmail.com'),
('Derya' , 28  , 15000  ,'derya@yahoo.com' ),
('Sevim' , 24  , 25000  ,'sevim@hotmail.com' ),
('Yusuf' , 32  , 18000  ,'yusuf@gmail.com' ),
('Halil' , 48  , 22000  ,'halil@gmail.com' ),
('Ece' , 54  , 21000  ,'ece@gmail.com' ),
('Can' , 38  , 19000  ,'can@gmail.com' ),
('Elif' , 27  , 14000  ,'elif@gmail.com' ),
('Ezgi' , 35  , 21000  ,'ezgi@gmail.com');

-- 1) Tablo bilgilerini listeleyiniz.
SELECT * FROM calisanlar1;

-- 2) isim, yaş ve maaş bilgilerini listeleyiniz
SELECT isim,yas,maas FROM calisanlar1;

-- 3) id'si 8 olan personel bilgilerini listeleyiniz
SELECT * FROM  calisanlar1 WHERE (id=8);

-- 4) id'si 5 olan personelin isim, yaş ve email bilgilerini listeleyiniz
SELECT isim,yas,mail FROM calisanlar1 WHERE (id=5);

-- 5) 30 yaşından büyük personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1 WHERE (yas>30);

-- 6) maası 21000 olmayan personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1  WHERE (maas<>21000);

-- 7) ismi a harfi ile başlayan personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1  WHERE isim LIKE 'A%';

-- 8) ismi n harfi ile biten personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1  WHERE isim LIKE '%n';

-- 9) email adresi gmail olan personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1 WHERE mail LIKE '%gmail.com';

-- 10) email adresi gmail olmayan personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1 WHERE mail NOT LIKE '%gmail.com';

-- 11) id'si 3,5,7 ve 9 olan personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1 WHERE id IN (3,5,7,9);

-- 12) yaşı 39,48 ve 54 olmayan personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1 WHERE yas not IN (39,48,54);

-- 13) yaşı 30 ve 40 arasında olan personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1 WHERE yas BETWEEN 30 AND 40;

-- 14) yaşı 30 ve 40 arasında olmyan personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1 WHERE yas NOT BETWEEN 30 AND 40;

-- 15) emaili olmayan personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1 WHERE mail IS NULL;

-- 16) emaili olan personel bilgilerini listeleyiniz.
SELECT * FROM calisanlar1 WHERE mail IS NOT NULL;

-- 17) personel bilgilerini yaşa göre sıralayınız.
SELECT  * FROM calisanlar1
ORDER BY YAS

-- 18) personel bilgilerini maaşa göre sıralayınız.
SELECT  * FROM calisanlar1
ORDER BY MAAS

-- 19) personelin yaşlarını büyükten küçüğe doğru sıralayınız.
SELECT  * FROM calisanlar1
ORDER BY YAS DESC

-- 20) personelin maaşlarını büyükten küçüğe doğru sıralayınız.
SELECT  * FROM calisanlar1
ORDER BY MAAS DESC

-- 21) En yüksek maaş olan ilk 3 personel bilgilerini sıralayınız
SELECT  * FROM calisanlar1
ORDER BY MAAS DESC
LIMIT 3

