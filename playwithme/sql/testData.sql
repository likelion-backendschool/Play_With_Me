drop database if exists playwithme;
create database playwithme;
use playwithme;

ALTER DATABASE playwithme DEFAULT CHARACTER SET utf8;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE EVENT;
TRUNCATE TABLE article;
TRUNCATE TABLE board;
TRUNCATE TABLE COMMENT;
TRUNCATE TABLE together;
TRUNCATE TABLE timeline;
SET FOREIGN_KEY_CHECKS = 1;

# event
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-07-15', '잠실종합운동장 내 보조경기장', '싸이 흠뻑쇼 SUMMER SWAG - 서울');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-07-09', '인천아시아드 주경기장', '싸이 흠뻑쇼 SUMMER SWAG 2022 - 인천');

INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(2, '2022-09-23', '난지 한강공원 일대', '2022 렛츠락 페스티벌');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(2, '2022-09-24', '난지 한강공원 일대', '2022 렛츠락 페스티벌');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(3, '2022-09-07', '서울숲', '서울숲 재즈 페스티벌');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(4, '2022-10-15', '잠실종합운동장', '자이브 슈퍼라이브');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(5, '2022-10-28', '세종문화회관', '폴킴 전국투어');


# board
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 1);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 2);

INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 3);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 4);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 5);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 6);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 7);




# article
# event=1,2는 같은 카테고리 개수는 event=1이 더 많음
# board = 1
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
# board = 2
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~29', '흠뻑쇼 가실분 구합니다!', NOW(), '성별무관', 1, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 2, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~39', '흠뻑쇼 가실분 구합니다!', NOW(), '여성', 2, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 2, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~49', '흠뻑쇼 가실분 구합니다!', NOW(), '남성', 3, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 2, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~59', '흠뻑쇼 가실분 구합니다!', NOW(), '성별무관', 4, TRUE, '흠뻑쇼 가실분~', NOW(), 0, 2, 1);

# event=3,4 는 같은 카테고리 category_id=2, event=3이 더 많음
# board = 3
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '2022 뎃츠락 페스트벌 가실 분 구합니다!', NOW(), '여성', 1, TRUE, '뎃츠락 페스티벌 가실분~', NOW(), 0, 3, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '2022 뎃츠락 페스트벌 가실 분 구합니다!', NOW(), '남성', 1, TRUE, '뎃츠락 페스티벌 가실분~', NOW(), 0, 3, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~39', '2022 뎃츠락 페스트벌 가실 분 구합니다!', NOW(), '성별무관', 1, TRUE, '뎃츠락 페스티벌 가실분~', NOW(), 0, 3, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('40~49', '2022 뎃츠락 페스트벌 가실 분 구합니다!', NOW(), '여성', 1, TRUE, '뎃츠락 페스티벌 가실분~', NOW(), 0, 3, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('50~59', '2022 뎃츠락 페스트벌 가실 분 구합니다!', NOW(), '남성', 1, TRUE, '뎃츠락 페스티벌 가실분~', NOW(), 0, 3, 1);
# board = 4
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~29', '2022 뎃츠락 페스트벌 가실 분 구합니다!', NOW(), '성별무관', 1, TRUE, '뎃츠락 페스티벌 가실분~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~39', '2022 뎃츠락 페스트벌 가실 분 구합니다!', NOW(), '여성', 1, TRUE, '뎃츠락 페스티벌 가실분~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~49', '2022 뎃츠락 페스트벌 가실 분 구합니다!', NOW(), '남성', 1, TRUE, '뎃츠락 페스티벌 가실분~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~59', '2022 뎃츠락 페스트벌 가실 분 구합니다!', NOW(), '성별무관', 1, TRUE, '뎃츠락 페스티벌 가실분~', NOW(), 0, 4, 1);

# event=5 는 혼자 카테고리 category_id=3
# board = 5
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '서울숲 재즈 페스티벌 가실 분 구합니다!', NOW(), '여성', 1, TRUE, '서울숲 재즈 페스티벌 가실분~', NOW(), 0, 5, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '서울숲 재즈 페스티벌 가실 분 구합니다!', NOW(), '남성', 1, TRUE, '서울숲 재즈 페스티벌 가실분~', NOW(), 0, 5, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~39', '서울숲 재즈 페스티벌 가실 분 구합니다!', NOW(), '성별무관', 1, TRUE, '서울숲 재즈 페스티벌 가실분~', NOW(), 0, 5, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('40~49', '서울숲 재즈 페스티벌 가실 분 구합니다!', NOW(), '여성', 1, TRUE, '서울숲 재즈 페스티벌 가실분~', NOW(), 0, 5, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('50~59', '서울숲 재즈 페스티벌 가실 분 구합니다!', NOW(), '남성', 1, TRUE, '서울숲 재즈 페스티벌 가실분~', NOW(), 0, 5, 1);

# board = 6
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~29', '자이브 슈퍼라이브 가실 분 구합니다!', NOW(), '성별무관', 1, TRUE, '자이브 슈퍼라이브 가실분~', NOW(), 0, 6, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~39', '자이브 슈퍼라이브 가실 분 구합니다!', NOW(), '여성', 1, TRUE, '자이브 슈퍼라이브 가실분~', NOW(), 0, 6, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~49', '자이브 슈퍼라이브 가실 분 구합니다!', NOW(), '남성', 1, TRUE, '자이브 슈퍼라이브 가실분~', NOW(), 0, 6, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~59', '자이브 슈퍼라이브 가실 분 구합니다!', NOW(), '성별무관', 1, TRUE, '자이브 슈퍼라이브~', NOW(), 0, 6, 1);

# board = 7
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~49', '폴킴 전국투어 가실분 구합니다!', NOW(), '성별무관', 2, TRUE, '폴킴 전국투어 콘서트 가실분~', NOW(), 0, 7, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '폴킴 전국투어 가실분 구합니다!', NOW(), '성별무관', 4, TRUE, '폴킴 전국투어 콘서트 가실분~', NOW(), 0, 7, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '폴킴 전국투어 가실분 구합니다!', NOW(), '성별무관', 3, TRUE, '폴킴 전국투어 콘서트 가실분~', NOW(), 0, 7, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '폴킴 전국투어 가실분 구합니다!', NOW(), '성별무관', 2, TRUE, '폴킴 전국투어 콘서트 가실분~', NOW(), 0, 7, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '폴킴 전국투어 가실분 구합니다!', NOW(), '성별무관', 2, TRUE, '폴킴 전국투어 콘서트 가실분~', NOW(), 0, 7, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '폴킴 전국투어 가실분 구합니다!', NOW(), '성별무관', 2, TRUE, '폴킴 전국투어 콘서트 가실분~', NOW(), 0, 7, 1);



INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
VALUES('저는 작성자입니다', NOW(), FALSE, NOW(), 1, 1);

INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
VALUES('저는 작성자입니다2', NOW(), FALSE, NOW(), 1, 1);


# together
INSERT INTO together(article_id, member_id) VALUES(1,1);
INSERT INTO together(article_id, member_id) VALUES(13,1);
INSERT INTO together(article_id, member_id) VALUES(14,1);
INSERT INTO together(article_id, member_id) VALUES(15,1);
INSERT INTO together(article_id, member_id) VALUES(16,1);
INSERT INTO together(article_id, member_id) VALUES(17,1);
INSERT INTO together(article_id, member_id) VALUES(17,NULL);
INSERT INTO together(article_id, member_id) VALUES(18,1);


#timeline
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NULL, NOW(), 1, 1, 1);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NULL, NOW(), 2, 1, 2);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NULL, NOW(), 3, 1, 3);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NULL, NOW(), 4, 1, 4);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NULL, NOW(), 5, 1, 5);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NULL, NOW(), 7, 1, 6);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NULL, NOW(), 7, NULL, 7);
INSERT INTO timeline(created_at, memo, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NULL, NOW(), 6, 1, 8);