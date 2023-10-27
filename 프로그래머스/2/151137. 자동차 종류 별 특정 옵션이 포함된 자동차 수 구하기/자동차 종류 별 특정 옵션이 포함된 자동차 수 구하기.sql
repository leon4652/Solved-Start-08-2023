-- 1
SELECT CAR_TYPE, COUNT(CAR_ID) AS CARS  
  from CAR_RENTAL_COMPANY_CAR
where options like "%가죽시트%" or options like "%열선시트%" or options like "%통풍시트%"
group by car_type
order by car_type

# SELECT CAR_TYPE, COUNT(CAR_ID) AS CARS 
# FROM CAR_RENTAL_COMPANY_CAR 
# WHERE OPTIONS LIKE '%통풍시트%' OR OPTIONS LIKE '%가죽시트%' OR OPTIONS LIKE '%열선시트%'   
# GROUP BY CAR_TYPE ORDER BY CAR_TYPE;