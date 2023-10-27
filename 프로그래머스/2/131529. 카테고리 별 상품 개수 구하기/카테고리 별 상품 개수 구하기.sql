-- 코드를 입력하세요
SELECT LEFT(product_code, 2) as CATEGORY, count(*) as PRODUCTS
  from product
 group by CATEGORY
 order by product_code
