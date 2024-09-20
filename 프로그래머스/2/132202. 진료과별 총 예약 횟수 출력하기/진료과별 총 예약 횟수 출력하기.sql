select MCDP_CD as `진료과코드`, count(*) as `5월예약건수` 
  from APPOINTMENT 
 where year(apnt_ymd) = 2022 and month(apnt_ymd) = 5
 group by MCDP_CD
 order by `5월예약건수`, `진료과코드`