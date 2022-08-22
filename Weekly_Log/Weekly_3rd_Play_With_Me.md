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

 ### 9회차 (08-08) 디스코드 진행(모두 참여)</h3>

- 멘토님과 피드백 진행 : 5회차 회의에서 정리한 질문 해결
  - 질문 :  https://www.notion.so/1-08-07-938d51830bd34e8eb5dfee2cf5ef4103
  - 답변 : https://wiken.io/ken/9413

### 10회차 (08-19) 디스코드 진행(모두 참여)</h3>

- 프로젝트 진행 중 수정사항 해결
  - 

   




## 현재까지 개발 과정 요약 (최소 500자 이상)
>우민식 : 
>- 크롤링 중 수정사항 해결
>- 크롤링 서비스 구조 리팩터링
>- 크롤링 서비스 인터페이스 구현 후 각 크롤링 서비스들 상속
>- 달력 뷰 일부 수정
>- Controller와 연결하여 날짜와 카테고리를 입력받아 해당 날짜의 이벤트들 검색

>박범서 : 
>- CommentCreateDto에서 CommentCreateForm으로 이름 변경
>- 대댓글 기능을 위한 컬럼 추가 ( parent(부모 댓글), childList )
>- 연관관계 편의 메서드 추가
>- 게시글 상세조회 뷰 추가 및 타임리프 적용(댓글 테스트 용도)

>주민지 : 
>- 캘린더 날짜 클릭시 페이지 갱신 없이 category, date가 반영된 URL로 변경되도록 구현
>- 날짜 클릭시 JS에서 ajax로 URL파라미터 category, date를 controller 인수로 전달
>- ajax로 Event List를 JSON으로 파싱하여 JS에 전달
>- controller에서 전달받은 JSON 데이터 JS에 저장
>- 클릭시 Event List 리턴하도록 구현 (진행중)

>최승근 : 
>- 카카오 로그인 인증 기능 완료
>- 로그인을 위한 메인 메뉴바(navber) 구현
>- Spring Security 의존성 추가 적용
>- 회원 가입 및 인가를 위한 Member Entity 수정 및 Role 추가
>- OAuth 를 이용한 회원 가입 로직 구현 (진행 중)

>한승연 : 
>- ArticleRequestDto 대신 용도에 맞게 ArticleCreateForm, ArticleUpdateForm 적용
>- 게시글 등록 기능 수정
>- 수정된 게시글 등록 기능 테스트 
>- 게시글 수정 기능 구현
>- 게시글 삭제 기능 구현
>- 게시글 상세조회 뷰 추가(값 잘 넘어오는지 확인)


## 개발 과정에서 나왔던 질문 (최소 200자 이상)

1. 게시글 상세 조회의 경우, model.addAttribute(“article”, article)과 같이 article, event 엔티티를 바로 넘겨도 괜찮은 것인가?
2. 바닐라 JS와 Controller가 데이터를 서로 주고 받아야 할 때 어떻게 해야 할까?
3. JS에서 URL 파라미터 처리를 ?으로 split하는 방법 외에 간단한 방법이 없을까?
4. 크롤링 진행하는 것을 서비스로 두어야 할지 컴포넌트로 두어야 할지?

## 개발 결과물 공유

Github Repository URL: [https://github.com/likelion-backendschool/Play_With_Me](https://github.com/likelion-backendschool/Play_With_Me)
