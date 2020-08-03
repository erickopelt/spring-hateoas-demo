insert into user
select RANDOM_UUID()
from system_range(0, 1000);
