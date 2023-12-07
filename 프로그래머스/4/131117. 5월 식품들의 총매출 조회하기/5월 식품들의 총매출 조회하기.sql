-- 코드를 입력하세요
SELECT o.product_id, p.product_name, sum(o.amount * p.price) as `total_sales`
  from food_order o join food_product p
    on o.product_id = p.product_id
 where year(o.produce_date) = 2022 and month(o.produce_date) = 5
 group by p.product_name
 order by `total_sales` desc, p.product_id asc

