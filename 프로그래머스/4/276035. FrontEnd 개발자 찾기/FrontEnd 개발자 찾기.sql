-- 코드를 작성해주세요

SELECT DISTINCT b.id, b.email, b.first_name, b.last_name
from skillcodes a join developers b
on (b.skill_code & a.code) != 0
where a.category = "Front End"
order by b.id asc;