--HOMEWORK 5---

/*1-Tüm filmleri film adı ve dili ile birlikte  listeleyiniz.
hint:film ve language tablolarını kullanınız.*/
SELECT title,name
FROM film
FULL JOIN language
ON film.language_id=language.language_id

SELECT title,(SELECT name 
			  FROM language
			  WHERE film.language_id=language.language_id)
FROM film;

SELECT *  FROM film;
SELECT * FROM language; 

/*2-Ödeme miktarı ve müşteri adıyla birlikte en yüksek ödeme yapan müşteriyi bulunuz.
hint:payment ve customer tablolarını kullanınız.*/
--BÜTÜN MAX DEĞERDE OLAN İSİMLERİ GETİRİYOR
SELECT amount,CONCAT(first_name,' ',last_name) AS İS
FROM payment P FULL JOIN  customer C
ON C.customer_id=P.customer_id
WHERE amount=(SELECT MAX(amount) FROM payment);

--SADECE MAXIN EKLEME SIRALAMASINA GÖRE EN YUKARIDAKİNİ VERİYOR
SELECT MAX(amount),CONCAT(first_name,' ',last_name) AS İS
FROM payment P FULL JOIN  customer C
ON C.customer_id=P.customer_id
GROUP BY amount,CONCAT(first_name,' ',last_name)
ORDER BY amount DESC
LIMIT 1

--

SELECT* FROM payment  ;
SELECT * FROM customer ;

/*3-Tüm kategorilerin isimlerini ve kategoriye ait film sayısını birlikte  listeleyiniz.
hint:category,film_category tablolarını kullanınız.*/
SELECT name,COUNT(FC.film_id)as film_sayisi
FROM category C
FULL JOIN film_category FC
ON C.category_id=FC.category_id
GROUP BY name
ORDER BY film_sayisi DESC

SELECT *  FROM category;
SELECT * FROM film_category

/*4-customer_id'ye göre en çok film kiralayan müşterilerin ad-soyad ve kiraladığı film sayısını birlikte listeleyiniz.
hint:customer,rental tablolarını kullanınız.*/
SELECT CONCAT(first_name,' ',last_name) AS İS,COUNT(R.rental_id) AS film_sayisi
FROM customer C
JOIN rental R
ON C.customer_id=R.customer_id
GROUP BY C.customer_id, C.first_name, C.last_name
ORDER BY film_sayisi DESC
LIMIT 1

--CHATGBP ÇÖZÜMÜ
WITH RT AS(SELECT CONCAT(first_name,' ',last_name) AS İS,COUNT(R.rental_id) AS film_sayisi
			FROM customer C
			JOIN rental R
			ON C.customer_id=R.customer_id
			GROUP BY C.customer_id, C.first_name, C.last_name)
SELECT İS,film_sayisi
FROM RT
WHERE film_sayisi=(SELECT MAX(film_sayisi)FROM RT)


--RENTAL DE customer_id'LERİN SAYISI
SELECT DISTINCT (customer_id),COUNT(customer_id) FROM rental GROUP BY customer_id

SELECT * FROM customer ;
EXCEPT
SELECT * FROM rental ;


--5-Tüm dilleri dil adı ve dildeki film sayısıyla birlikte listeleyiniz.
--hint:film ve language tablolarını kullanınız.

SELECT name,COUNT(title)
FROM film
FULL JOIN language
ON film.language_id=language.language_id
GROUP BY name



SELECT NAME FROM language GROUP BY name

SELECT *  FROM film;
SELECT * FROM language; 


--6- Tüm personelleri personel adı-soyadı ve personelin yönettiği müşteri sayısıyla birlikte listeleyiniz.
--hint:staff ve customer tablolarını kullanınız.
SELECT CONCAT(S.first_name,' ',S.last_name) AS İS,COUNT(C.store_id) AS müsteri_sayisi
FROM customer C
FULL JOIN staff S
ON C.store_id=S.store_id
GROUP BY S.first_name,S.last_name 

SELECT *  FROM staff;
SELECT * FROM customer; 

/*7- En fazla actor oynayan 10 tane filmin ismini ve filmde oynayan actor sayısını bulunuz.
hint:film ve film_actor tablolarını kullanınız.
*/
-- İLK 10 FİLM İSMİ
SELECT DISTINCT(title)
FROM film F
FULL JOIN film_actor FA
ON F.film_id=FA.film_id
LIMIT 10

SELECT *  FROM film;
SELECT * FROM film_actor; 
--BİR FİLMDE KAÇ AKTOR OYNAR
SELECT DISTINCT(film_id),COUNT(film_id) FROM  film_actor
GROUP BY film_id
ORDER BY COUNT(film_id) DESC
LIMIT 10

--AKTOR KAÇ FİLMDE OYNAMIŞ
SELECT DISTINCT(actor_id),COUNT(actor_id) FROM  film_actor
GROUP BY actor_id
ORDER BY actor_id
--ASIL CEVAP
SELECT DISTINCT(title),COUNT(FA.film_id)
FROM film F
FULL JOIN film_actor FA
ON F.film_id=FA.film_id
GROUP BY FA.film_id,F.film_id
ORDER BY COUNT(FA.film_id) DESC
LIMIT 10

/*
8-Tüm actorlerin ad-soyadını ve oynadığı filmlerin adını birlikte  listeleyiniz.
hint:actor, film_actor, film tablolarını kullanınız.*/
SELECT  * FROM actor
SELECT * FROM film_actor
SELECT * FROM film
--film_id leri actor id ve film isimlerini birleştirdik -YENİ BİR TABLO YAPTIK
CREATE VIEW film_actor_id  AS(SELECT F.film_id,title,actor_id
						FROM film F
						FULL JOIN film_actor FA
						ON F.film_id=FA.film_id
						order by F.film_id )
--actor tablosu ile film_actor_id birleştirelim
SELECT CONCAT(first_name,' ',last_name) AS isim_soyad,title as film_ismi
FROM film_actor_id
FULL JOIN actor A
ON film_actor_id.actor_id=A.actor_id
GROUP BY CONCAT(first_name,' ',last_name),film_actor_id.title
ORDER BY CONCAT(first_name,' ',last_name)
--BU KISIM ÇALIŞMADI !!
WITH film_actor_id AS(SELECT F.film_id,title,actor_id
						FROM film F
						FULL JOIN film_actor FA
						ON F.film_id=FA.film_id
						order by F.film_id )
						
--9-emaili 'j' ile başlayan ve soyadı 'er' ile biten müşterileri listeleyiniz. 
SELECT * FROM customer
WHERE email LIKE 'j%' AND last_name LIKE '%er'

/*10-İsmi(title) d harfinden sonra e veya a sonrasında ise m veya n içeren ve y harfi ile biten filmlerin
 isimlerini listeleyiniz.(cevap:Victory Academy,Annie Identity)*/
SELECT title FROM film
WHERE title ~ 'd[ea].*[mn]y$' 

/*11-İsmi c ile başlayan kategorideki filmlerin idlerinden en küçük 5 tanesini listeleyiniz.
hint:category, film_category tablolarını kullanınız.*/
SELECT * FROM category
SELECT* FROM film_category

SELECT name,film_id
FROM category C
FULL JOIN film_category FC
ON C.category_id=FC.category_id
where name ILIKE 'C%' 
ORDER BY film_id 
LIMIT 5

/*12-İsmi a ile başlayıp a ile bitmeyen şehir(city) ve bu şehirlerin bulunduğu ismi a ile başlayan
 ülkeleri birlikte listeleyiniz.(örn: Escobar-Argentina)
hint:city, country tablolarını kullanınız.*/
SELECT * FROM city;
SELECT * FROM country;

SELECT * FROM city CI
JOIN country CO
ON CO.country_id=CI.country_id
WHERE city !~* '^A.*A$' AND country ~* '^A'



						
