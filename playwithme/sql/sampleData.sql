SELECT * FROM MEMBER;
SELECT * FROM article;
SELECT * FROM COMMENT;
SELECT * FROM board;
SELECT * FROM together;

# event
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-07-15', '잠실종합운동장 내 보조경기장', '싸이 흠뻑쇼 SUMMER SWAG - 서울');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-07-09', '인천아시아드 주경기장', '싸이 흠뻑쇼 SUMMER SWAG 2022 - 인천');

INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-09-23', '난지 한강공원 일대', '2022 렛츠락 페스티벌');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-09-24', '난지 한강공원 일대', '2022 렛츠락 페스티벌');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-09-07', '서울숲', '서울숲 재즈 페스티벌');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-10-15', '잠실종합운동장', '자이브 슈퍼라이브');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-10-28', '세종문화회관', '폴킴 전국투어');





# board
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 1);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 2);

INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 3);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 4);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 5);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 6);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 7);




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

INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~49', '인천 흠뻑쇼 가실분 구합니다!', NOW(), '성별무관', 2, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 2, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '렛츠락 가실분 구합니다!', NOW(), '성별무관', 4, TRUE, '23일 렛츠락 가실분~', NOW(), 0, 3, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '렛츠락 가실분 구합니다!', NOW(), '성별무관', 3, TRUE, '24일 렛츠락 가실분~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '서재페 가실분 구합니다!', NOW(), '성별무관', 2, TRUE, '서재페 가실분~', NOW(), 0, 5, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '폴킴 가실분 구합니다!', NOW(), '성별무관', 2, TRUE, '폴킴 가실분~', NOW(), 0, 7, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '자이브 가실분 구합니다!', NOW(), '성별무관', 2, TRUE, '자이브 가실분~', NOW(), 0, 6, 1);



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

# together
INSERT INTO together(article_id, member_id) VALUES(1,1);
INSERT INTO together(article_id, member_id) VALUES(13,1);
INSERT INTO together(article_id, member_id) VALUES(14,1);
INSERT INTO together(article_id, member_id) VALUES(15,1);
INSERT INTO together(article_id, member_id) VALUES(16,1);
INSERT INTO together(article_id, member_id) VALUES(17,1);
INSERT INTO together(article_id, member_id) VALUES(17,null);
INSERT INTO together(article_id, member_id) VALUES(18,1);


#timeline
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), null, NOW(), 1, 1, 1);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), null, NOW(), 2, 1, 2);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), null, NOW(), 3, 1, 3);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), null, NOW(), 4, 1, 4);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), null, NOW(), 5, 1, 5);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), null, NOW(), 7, 1, 6);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), null, NOW(), 7, null, 7);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), null, NOW(), 6, 1, 8);