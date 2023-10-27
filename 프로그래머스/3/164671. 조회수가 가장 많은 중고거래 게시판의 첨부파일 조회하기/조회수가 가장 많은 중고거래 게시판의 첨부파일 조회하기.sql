-- 코드를 입력하세요
# SELECT *
#   from used_goods_board b join used_goods_file f
#     on b.board_id = f.board_id

# select concat("/home/grep/src/",board_id,"/",file_id,file_ext) as FILE_PATH
#   from used_goods_file
#  where board_id = (SELECT board_id
#                       from used_goods_board b
#                      order by views desc
#                      limit 1)
#  order by FILE_ID desc

select concat("/home/grep/src/",board_id,"/",file_id,file_name,file_ext) as FILE_PATH
  from used_goods_file
 where board_id = (SELECT board_id
                      from used_goods_board b
                     order by views desc
                     limit 1)
 order by FILE_ID desc
 