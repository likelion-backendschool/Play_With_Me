SELECT * FROM MEMBER;
SELECT * FROM article;
SELECT * FROM COMMENT;
SELECT * FROM board;

# event
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-07-15', '잠실종합운동장 내 보조경기장', '싸이 흠뻑쇼 SUMMER SWAG - 서울');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-07-09', '인천아시아드 주경기장', '싸이 흠뻑쇼 SUMMER SWAG 2022 - 인천');

# board
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 1);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 2);

# article
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '흠뻑쇼 가실분 구합니다!', NOW(), '여성', 1, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '흠뻑쇼 가실분 구합니다!', NOW(), '남성', 2, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~39', '흠뻑쇼 가실분 구합니다!', NOW(), '성별무관', 3, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('40~49', '흠뻑쇼 가실분 구합니다!', NOW(), '여성', 4, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('50~59', '흠뻑쇼 가실분 구합니다!', NOW(), '남성', 5, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~29', '흠뻑쇼 가실분 구합니다!', NOW(), '성별무관', 1, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~39', '흠뻑쇼 가실분 구합니다!', NOW(), '여성', 2, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~49', '흠뻑쇼 가실분 구합니다!', NOW(), '남성', 3, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~59', '흠뻑쇼 가실분 구합니다!', NOW(), '성별무관', 4, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~29', '흠뻑쇼 가실분 구합니다!', NOW(), '여성', 5, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~39', '흠뻑쇼 가실분 구합니다!', NOW(), '남성', 1, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~49', '흠뻑쇼 가실분 구합니다!', NOW(), '성별무관', 2, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 1, 1);

INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
VALUES('저는 작성자입니다', NOW(), FALSE, NOW(), 1, 1);

INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
VALUES('저는 작성자입니다2', NOW(), FALSE, NOW(), 1, 1);

-- 카카오톡 로그인 ID 2개 있으면 아래 쿼리 실행해도 됨.
-- INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
-- VALUES('저는 유저입니다. ㅎ2ㅎ2', NOW(), TRUE, NOW(), 1, 2);
--
-- INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id, parent_id)
-- VALUES('안녕하세요', NOW(), TRUE, NOW(), 1, 2,3);






