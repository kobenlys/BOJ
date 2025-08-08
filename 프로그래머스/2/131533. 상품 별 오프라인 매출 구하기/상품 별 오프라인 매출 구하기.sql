-- 코드를 입력하세요
SELECT PRODUCT_CODE, sum(a.price * b.sales_amount) as SALES
from product a join offline_sale b
ON a.product_id = b.product_id
GROUP BY a.product_code
ORDER BY sales DESC, a.product_code ASC;