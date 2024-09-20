select count(*) as `FISH_COUNT`
  from FISH_INFO fi
  join FISH_NAME_INFO fn
    on fi.fish_type = fn.fish_type
  where fish_name in ('BASS', 'SNAPPER')
  
  ;