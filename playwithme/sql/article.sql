# event
insert into event(category_id, date, location, name) values(1, '2022-07-15', '잠실종합운동장 내 보조경기장', '싸이 흠뻑쇼 SUMMER SWAG - 서울');
insert into event(category_id, date, location, name) values(1, '2022-07-09', '인천아시아드 주경기장', '싸이 흠뻑쇼 SUMMER SWAG 2022 - 인천');

# board
insert into board(created_at, is_blind, event_id) values(now(), false, 1);
insert into board(created_at, is_blind, event_id) values(now(), false, 2);

# article
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('10~19', '흠뻑쇼 가실분 구합니다!', now(), '여성', 1, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('20~29', '흠뻑쇼 가실분 구합니다!', now(), '남성', 2, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('30~39', '흠뻑쇼 가실분 구합니다!', now(), '성별무관', 3, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('40~49', '흠뻑쇼 가실분 구합니다!', now(), '여성', 4, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('50~59', '흠뻑쇼 가실분 구합니다!', now(), '남성', 5, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('10~29', '흠뻑쇼 가실분 구합니다!', now(), '성별무관', 1, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('10~39', '흠뻑쇼 가실분 구합니다!', now(), '여성', 2, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('10~49', '흠뻑쇼 가실분 구합니다!', now(), '남성', 3, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('10~59', '흠뻑쇼 가실분 구합니다!', now(), '성별무관', 4, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('10~29', '흠뻑쇼 가실분 구합니다!', now(), '여성', 5, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('10~39', '흠뻑쇼 가실분 구합니다!', now(), '남성', 1, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);
insert into article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
values('10~49', '흠뻑쇼 가실분 구합니다!', now(), '성별무관', 2, true, '흠뻑쇼 가실분~', now(), 0, 1, 1);