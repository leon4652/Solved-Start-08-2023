SELECT car_id, 
MAX(case
 when start_date <= '2022-10-16' and '2022-10-16' <= end_date then '대여중' 
 else '대여 가능'
end) as `AVAILABILITY`
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
GROUP BY car_id
ORDER BY car_id DESC
