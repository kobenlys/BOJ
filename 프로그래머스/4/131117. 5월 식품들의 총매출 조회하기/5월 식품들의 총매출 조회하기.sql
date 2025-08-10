-- 코드를 입력하세요
SELECT a.product_id, a.product_name, sum(a.price * b.amount)
as total_sales
from food_product a join food_order b
on a.product_id = b.product_id
where b.produce_date >= '2022-05-01' AND b.produce_date < '2022-06-01'
group by a.product_id
order by total_sales desc, a.product_id; 