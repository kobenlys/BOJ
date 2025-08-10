-- 코드를 입력하세요
SELECT a.flavor
from first_half a right join july b
USING (flavor)
group by a.flavor
order by sum(a.total_order) + sum(b.total_order) desc
limit 3