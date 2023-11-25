-- 코드를 입력하세요
SELECT ROUND(avg(daily_fee)) as AVERAGE_FEE
  from CAR_RENTAL_COMPANY_CAR
 group by car_type
 having car_type = 'SUV'