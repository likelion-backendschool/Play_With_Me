drop database if exists playwithme;
create database playwithme;
use playwithme;

-- event-baseball
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-08-02', '한화생명이글스파크', 'KIA vs 한화');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-10-01', '인천SSG랜더스필드', 'NC vs LG');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(1, '2022-10-08', '잠실야구장', '키움 vs 두산');
-- event-soccer
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(2, '2022-09-17', '창원 축구센터', 'K리그2 경남 VS 부천');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(2, '2022-10-09', '수원 월드컵', 'K리그1 수원 VS 서울');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(2, '2022-10-09', '김천 종합', 'K리그1 김천 VS 성남');
-- event-basketball
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(3, '2022-09-20', '농구1', '농구1');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(3, '2022-10-08', '대학로 아트원씨어터', '농구2');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(3, '2022-10-15', 'KT＆G 상상마당 대치아트홀', '농구3');
-- event-musical
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(4, '2022-10-20', '잠실종합운동장 내 빅탑', '태양의서커스 <뉴 알레그리아>');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(4, '2022-10-05', '대성 디큐브아트센터', '마틸다');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(4, '2022-10-18', '충무아트센터 대극장', '킹키부츠');
-- event-concert
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(5, '2022-09-07', '서울숲', '서울숲 재즈 페스티벌');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(5, '2022-10-15', '부산 아시아드 주경기장', '2030 부산세계박람회 유치 기원 콘서트 BTS 〈Yet To Come〉 in BUSAN');
INSERT INTO EVENT(category_id, DATE, location, NAME) VALUES(5, '2022-10-16', 'KSPO DOME (올림픽체조경기장)', 'BLACKPINK WORLD TOUR ［BORN PINK］SEOU6');


-- board-baseball
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 1);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 2);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 3);
-- board-soccer
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 4);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 5);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 6);
-- board-basketball
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 7);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 8);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 9);
-- board-musical
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 10);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 11);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 12);
-- board-concert
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 13);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 14);
INSERT INTO board(created_at, is_blind, event_id) VALUES(NOW(), FALSE, 15);

-- 게시글(총 74개)
-- article-baseball
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '8월 2일 같이 기아 응원하실분 구해요!', NOW(), '여성', 3, TRUE, '저는 고1 여자인데 같이 응원하실 분 구해요~ 댓글 남겨주세요!', NOW(), 0, 1, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '인천 LG 응원 같이해요~~', NOW(), '성별무관', 5, TRUE, '같이 치맥하면서 응원해요~ 여자 남자 상관 없습니다', NOW(), 0, 2, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '두산 팬분들 같이 응원하실 분 있으신가요??', NOW(), '남성', 2, TRUE, '같이 오래오래 야구경기 보러다닐 분 구해요!!', NOW(), 0, 3, 1);
-- article-soccer
-- 4번 게시판 게시글 시작(65개)
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
-- 10
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
-- 20
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
-- 30
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
-- 40
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
-- 50
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
-- 60
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~19', '9월 17일 경남 경기 같이 보러 가실분!', NOW(), '여성', 3, TRUE, '경남 경기 같이 보러 가실분!!!!!!', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '부천 응원하시는 대학생분들 있나요??', NOW(), '성별무관', 5, TRUE, '부천 응원하시는 대학생분들 있나요??', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('30~49', '즐겁게 경기 보러가실 분 구합니다.', NOW(), '남자', 2, TRUE, '즐겁게 경기 보러가실 분 구합니다.', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 경기보고 저녁드실 분 구해요~', NOW(), '여성', 1, TRUE, '같이 경기보고 저녁드실 분 구해요~', NOW(), 0, 4, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~59', '경남 응원 같이 해요!', NOW(), '성별무관', 4, TRUE, '경남 응원 같이 해요!', NOW(), 0, 4, 1);
-- 4번 게시판 게시글 끝
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '수원 vs 서울 같이 가실 남성분 구합니다~~', NOW(), '남성', 2, TRUE, '같이 응원도 하고 재밌게 놀아요', NOW(), 0, 5, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '김천 종합에서 김천 경기 보실분 있으실까요??', NOW(), '남성', 3, TRUE, '20대 남성분끼리 3명 모집합니다!!', NOW(), 0, 6, 1);
-- article-musical
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '태양의 서커스 10월 20일 20:00시 공연 같이 보러가실 분 구합니다!', NOW(), '여성', 2, TRUE, '10월 20일 태양의 서커스 뮤지컬 보러가실 분 구합니다!', NOW(), 0, 10, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~29', '뮤지컬 마틸다 19:30분 같이 예매해서 보러가실 분 구하고 있습니다. 좌석은 S석 이상으로 생각하고 있습니다. ', NOW(), '남성', 2, TRUE, '마틸다 뮤지컬 같이 보러가실 분 한 분 구합니다', NOW(), 0, 11, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 보러 예매한 친구 못가게 되어서 같이보러갈 사람 구하고 있습니다. 킹키부츠 10월 18일 공연 R석입니다. 편하게 댓글 남겨주세요', NOW(), '남성', 2, TRUE, '함께 킹키부츠보러 가실 분 구합니다.', NOW(), 0, 12, 1);
-- article-concert
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~29', '태양의 서커스 10월 20일 20:00시 공연 같이 보러가실 분 구합니다!', NOW(), '여성', 2, TRUE, '10월 20일 태양의 서커스 뮤지컬 보러가실 분 구합니다!', NOW(), 0, 13, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('10~29', '뮤지컬 마틸다 19:30분 같이 예매해서 보러가실 분 구하고 있습니다. 좌석은 S석 이상으로 생각하고 있습니다. ', NOW(), '남성', 2, TRUE, '마틸다 뮤지컬 같이 보러가실 분 한 분 구합니다', NOW(), 0, 14, 1);
INSERT INTO article(age_range, contents, created_at, gender, max_recruit_num, recruit_status, title, updated_at, views, board_id, member_id)
VALUES('20~39', '같이 보러 예매한 친구 못가게 되어서 같이보러갈 사람 구하고 있습니다. 킹키부츠 10월 18일 공연 R석입니다. 편하게 댓글 남겨주세요', NOW(), '남성', 2, TRUE, '함께 킹키부츠보러 가실 분 구합니다.', NOW(), 0, 15, 1);


--comment
-- comment (유저A)
INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
VALUES('참여하고 싶어요!!', NOW(), FALSE, NOW(), 4, 2);
-- comment (작성자)대댓글
INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id, parent_id)
VALUES('넵넵 오픈채팅방 링크 보내드리겠습니다!! http: ~', NOW(), TRUE, NOW(), 4, 1, 1);
-- comment (유저B)
INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
VALUES('저 가고싶습니다!! 오픈채팅방 링크 남겨주시면 감사하겠습니다!!', NOW(), TRUE, NOW(), 4, 3);

-- comment (유저A)
INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
VALUES('경기 같이 보실래요??', NOW(), FALSE, NOW(), 5, 2);
-- comment (작성자)대댓글
INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id, parent_id)
VALUES('넵넵 자세한거는 오픈채팅방에서 말씀 드리겠습니다!! http : ~', NOW(), TRUE, NOW(), 5, 1, 4);
-- comment (유저B)
INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
VALUES('저도 마침 동행할 사람 없었는데 같이 가고싶습니다!!', NOW(), TRUE, NOW(), 5, 3);

-- comment (유저A)
INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
VALUES('참여 원합니다!! 어떻게 소통하나요??', NOW(), FALSE, NOW(), 6, 2);
-- comment (작성자)대댓글
INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id, parent_id)
VALUES('오프채팅방 입니다!! http : ~', NOW(), TRUE, NOW(), 6, 1, 7);
-- comment (유저B)
INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id)
VALUES('저도 마침 동행할 사람 없었는데 같이 가고싶습니다!!', NOW(), TRUE, NOW(), 6, 3);
-- comment (작성자)대댓글
INSERT INTO COMMENT(contents, created_at, secret_status, updated_at, article_id, member_id, parent_id)
VALUES('오프채팅방 입니다!! http : ~', NOW(), TRUE, NOW(), 6, 1, 9);

-- togetherA(4번 게시글 동행)
INSERT INTO together(article_id, member_id, event_id, created_at)
VALUES(4, 1, 4, NOW());
INSERT INTO together(article_id, member_id, event_id, created_at)
VALUES(4, 2, 4, NOW());
INSERT INTO together(article_id, member_id, event_id, created_at)
VALUES(4, 3, 4, NOW());

-- reviewA(4번 게시글 동행 리뷰) 3 * 2 = 6개
INSERT INTO review(score, article_id, reviewee_id, reviewer_id)
VALUES(0, 4, 1, 2);
INSERT INTO review(score, article_id, reviewee_id, reviewer_id)
VALUES(0, 4, 1, 3);
INSERT INTO review(score, article_id, reviewee_id, reviewer_id)
VALUES(0, 4, 2, 1);
INSERT INTO review(score, article_id, reviewee_id, reviewer_id)
VALUES(0, 4, 2, 3);
INSERT INTO review(score, article_id, reviewee_id, reviewer_id)
VALUES(0, 4, 3, 1);
INSERT INTO review(score, article_id, reviewee_id, reviewer_id)
VALUES(0, 4, 3, 2);

-- timelineA
INSERT INTO timeline(created_at, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NOW(), 4, 1, 1);
INSERT INTO timeline(created_at, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NOW(), 4, 2, 2);
INSERT INTO timeline(created_at, updated_at, event_id, member_id, together_id)
VALUES(NOW(), NOW(), 4, 3, 3);
