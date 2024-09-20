select g.year, (g.MAX_SIZE - e.SIZE_OF_COLONY ) as `YEAR_DEV`, e.id
  from ecoli_data e
  join (select YEAR(differentiation_date) as year, max(size_of_colony) as `MAX_SIZE`
        from ecoli_data
        group by YEAR(differentiation_date)) g
    on year(e.differentiation_date) = g.year
order by g.year, `YEAR_DEV`


