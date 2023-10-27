
SELECT board_id, writer_id, title, price, 
  case status
  when "SALE" then "판매중"
  when "RESERVED" then "예약중"
  when "DONE" then "거래완료"
  else "NONE"
  end as STATUS
  from used_goods_board
 where year(created_date) = 2022 and month(created_date) = 10 and day(created_date) = 5
 order by board_id desc
 