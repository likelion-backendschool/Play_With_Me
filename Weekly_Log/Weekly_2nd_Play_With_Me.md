## 팀 구성원, 개인 별 역할

- 우민식
    - 각 이벤트의 장소, 이름을 저장하기 위한 크롤링 기능 구현
- 박범서
    - 게시글에서 동행에 참여하려는 사람이 신청할 수 있는 댓글 기능 구현
- 주민지
    - 각 이벤트들을 일정에 맞춰 보여줄 수 있는 달력 기능 구현
- 최승근
    - 각 사용자들이 카카오 로그인을 통해 서비스를 사용할 수 있는 로그인 기능 구현
- 한승연
    - 각 게시판에서 참여를 원하는 사람을 모집할 수 있는 게시판 기능 구현

## 팀 내부 회의 진행 회차 및 일자

 ### 6회차 (08-08) 디스코드 진행(모두 참여)</h3>

- 멘토님과 피드백 진행 : 5회차 회의에서 정리한 질문 해결
  - 질문 :  https://www.notion.so/1-08-07-938d51830bd34e8eb5dfee2cf5ef4103
  - 답변 : https://wiken.io/ken/9413

### 7회차 (08-10) 디스코드 진행(모두 참여)</h3>

- 각 파트별 분담 후 각자 개발 진행
  - 우민식 : 크롤링
  - 박범서 : 댓글
  - 주민지 : 달력
  - 최승근 : 로그인
  - 한승연 : 게시판

- 피드백에 맞춰 프로젝트 DB 구조 및 URI 주소 수정
  - 수정 : https://www.notion.so/API-Specification-74f1b278e7344cbe9f533a429d900ca9

   
 ### 8회차 (08-12) 디스코드 진행(모두 참여)</h3>

- 게시판 구현 중 데이터베이스 구조 수정 및 이름 변경
  - 데이터베이스 구조 : https://app.diagrams.net/#G1QfLBCYrKT1-w0ny97cxApPXC9Nc0T1R2



## 현재까지 개발 과정 요약 (최소 500자 이상)
>우민식 : 
>- 이벤트 크롤링 기능
>- 로컬 테스트를 위한 h2 DB 연결
>- 콘서트와 뮤지컬 크롤링을 위한 객체 생성
>- 스포츠 일정 크롤링을 위한 객체 생성
>- 각 크롤링 기능 테스트 코드 작성
>- 크롤링을 통해 정보 가공 후 해당 정보를 통한 객체 저장

>박범서 : 
>- 댓글 작성, 리스트 및 상세조회, 수정, 삭제 기능 구현
>- 댓글 작성 : @RequestBody로 넘어온 Dto를 통해 작성 구현.
>- 댓글 리스트 조회 : url로 넘어온 articleId를 통해 연관된 댓글 (생성날짜, 수정날짜, 내용, 비밀 상태)들을 담은 Dto 리스트 반환
>- 댓글 상세조회 : 댓글 id를 통해 (생성날짜, 수정날짜, 내용, 비밀 상태)를 담은 Dto 반환
>- 댓글 수정 : @RequestBody로 넘어온 Dto를 통해 수정 구현
>- 게시글과의 외래키 지정 확인을 위해 테스트용 articleService 추가 구현

>주민지 : 
>- 카테고리별 이벤트 캘린더 기능
>- 로컬 테스트를 위한 h2 DB 연결
>- 부트스트랩 적용하여 캘린더 생성
>- CalendarController를 통헤 각 카테고리 페이지 요청 처리 구현
>- EventRepository와 EventService 구현하여 이벤트 일정 저장 및 반환 구현
>- 해당 기능 테스트를 위해 테스트 코드 작성

>최승근 : 
>-카카오 로그인 인증 및 사용자 정보 조회 구현
>- MemberController 내에 카카오 인증 서버에 로그인 요청 처리 및 콜백을 위한 요청 처리 메서드를 추가
>- 카카오 로그인 인증 후 Access Token 요청 로직과 Access Token을 이용한 사용자 정보 수신 요청 로직을 추가
>- 수신한 사용자 정보를 KakaoUser(DTO)로 파싱하였고, 웹 테스트를 통해 로그인 시 정상적으로 사용자 정보가 들어오는 것을 확인

>한승연 : 
>- 게시글 작성, 상세조회 기능
>- 로컬 테스트를 위한 h2 DB 연결(JPA 사용)
>- Article, Board, Event, Member, Comment Entity 설계 및 추가(with 범서님)
>- ArticleRepository, BoardRepository, MemberRepository 추가
>- 게시글 기능 구현을 위한 ArticleController, ArticleService, ArticleDto 추가
>- ArticleServiceTest 에서 Article 등록 테스트 및 게시글 등록 기능 완료
>- 게시글 상세 조회 기능 완료(Event 정보를 제외한 게시글 정보 조회)


## 개발 과정에서 나왔던 질문 (최소 200자 이상)

1. Article -> Comment 방향으로는 접근할 일이 없다고 생각해서 Article Entity에서는 @OneToMany를 포함하지 않았는데, @OneToMany 를 포함하는 게 나을까요??
    - 조회를 할때 게시글 위주로 조회를하기때문에 OneToMany를 넣는게 편할 것이다. 
2. 회원매너 평가폼 주소에 memberId 를 url 에 넣어주는 것과 memberId를 body에 넣어주는 것 중 어떤 방식이 더 좋은가?
    - 보안 상 문제는 둘 다 상관 없을 것이다 -> 프로젝트 내에서 memberId를 보여주는 것이 나을 경우 Url에 넣는 것이 낫다.
3. 회원 매너 평가 시 해당 회원의 memberId를 url로 보여주는 것이 좋은 지, body로 숨기는 것이 나은지?
    - 마찬가지로 보안 상 문제점은 X -> 누구를 평가하는 지 보여주기 위해 url에 보여준다.
4. 카카오 로그인을 통해 소셜 로그인 기능만을 사용할건데 USER 테이블에 loginID, loginPw 필요한 지?
    - 관리자와 member를 한 테이블로 사용 시에는 필요 -> 프로젝트 내 관리자 테이블 추가하여 loginPw과 loginID는 생략하였다.
5. Review 테이블 내에서 User의 Pk인 user_id를 서로 다르게 두번 참조해야 하는 데 문제가 되는 지?
    - 이 부분에서는 문제가 생기지 않는다. 

## 개발 결과물 공유

Github Repository URL: [https://github.com/likelion-backendschool/Play_With_Me](https://github.com/likelion-backendschool/Play_With_Me)
