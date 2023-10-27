-- 코드를 입력하세요
# SELECT f.flavor, sum(f.total_order) + sum(j.total_order)
#   from first_half f, july j
#  group by f.flavor
#  limit 3

SELECT a.FLAVOR
FROM FIRST_HALF a JOIN JULY b
ON a.FLAVOR = b.FLAVOR
GROUP BY FLAVOR
ORDER BY SUM(a.TOTAL_ORDER + b.TOTAL_ORDER) DESC
LIMIT 3