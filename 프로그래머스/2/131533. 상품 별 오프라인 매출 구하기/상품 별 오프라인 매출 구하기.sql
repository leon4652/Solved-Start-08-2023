-- 코드를 입력하세요
SELECT product_code, sum(price * sales_amount) as SALES
  from product p join offline_sale s
    on p.product_id = s.product_id
 group by product_code
 order by SALES desc, product_code