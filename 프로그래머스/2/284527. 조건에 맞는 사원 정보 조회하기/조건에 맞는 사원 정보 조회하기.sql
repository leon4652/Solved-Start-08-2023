-- 코드를 작성해주세요

# select he.emp_name, res.sum(score)
#   from HR_EMPLOYEES he
#     on (select emp_no, sum(score) from HR_GRADE where YEAR = 2022 group by emp_no limit 1) res
#   join res.emp_no = he.emp_no


SELECT res.total_score as score, he.emp_no, he.emp_name, he.position, he.email 
FROM HR_EMPLOYEES he
JOIN (
    SELECT emp_no, SUM(score) AS total_score
    FROM HR_GRADE
    WHERE YEAR = 2022
    GROUP BY emp_no
    ORDER BY total_score desc
    LIMIT 1
) res ON res.emp_no = he.emp_no;

