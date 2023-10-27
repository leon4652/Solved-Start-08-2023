-- 코드를 입력하세요
SELECT HOUR(datetime), count(datetime)
  from animal_outs
 where HOUR(datetime) between 9 and 19
 group by HOUR(datetime)
 order by HOUR(datetime)