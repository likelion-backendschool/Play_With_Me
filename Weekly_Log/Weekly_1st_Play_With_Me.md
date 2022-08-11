## 팀 구성원, 개인 별 역할

- 우민식
    - 사용자 소셜 로그인 기능 flowChart작성, 데이터베이스 구성
- 박범서
    - 각 event 크롤링 및 데이터베이스 저장, 행사 선택 및 리스팅 기능 고안, flowChart 작성
- 주민지
    - 행사별 게시판 생성 및 게시판 내 게시글 작성 기능 고안, flowChart 작성
- 최승근
    - 행사별 D-Day 알람 기능 고안 및 flowChart 작성, 데이터베이스 구성
- 한승연
    - 각 사용자 별 타임라인 기능 및 매너 점수 기능 고안, flowChart 작성

## 팀 내부 회의 진행 회차 및 일자

### 1회차 (08-01) 디스코드 진행(모두 참여)</h3>

- 프로젝트 기능 구체화 및 선택
    - 팀 회의 진행 후 이벤트 선택을 위한 달력 기능, 게시판 기능, 이벤트 저장을 위한 크롤링,<br>
    사용자 정보 저장을 위한 소셜 로그인 기능, 사용자 각각의 타임라인 기능, 동행온도 기능

### 2회차 (08-02) 디스코드 진행(모두 참여)

- 개인별 기능 분담 후 해당 기능 flowChart 구현
    
    [Flowchart Maker & Online Diagram Software](https://app.diagrams.net/#G1QfLBCYrKT1-w0ny97cxApPXC9Nc0T1R2)
    

### 3회차 (08-03) 디스코드 진행(모두 참여)

- 해당 기능에서 사용할 ER diagram 작성
    
    [Flowchart Maker & Online Diagram Software](https://app.diagrams.net/#G1QfLBCYrKT1-w0ny97cxApPXC9Nc0T1R2)
    

### 4회차 (08-04) 디스코드 진행(모두 참여)

- API Specification 작성 및 URI 정리
    - [API Specification](https://www.notion.so/API-Specification-74f1b278e7344cbe9f533a429d900ca9)

### 6회차 (08-07) 디스코드 진행(모두 참여)

- 08-08일 피드백 대비 질문 정리 및 각자 원하는 도메인 확정

## 현재까지 개발 과정 요약 (최소 500자 이상)

### 1. ER 다이어 그램 설계

> 프로젝의 전체적인 ER 다이어그램을 설계와 JPA의 기반이 될 연관관계를 작성하였습니다.
>
>관계를 규정에 있어 어려움을 느끼는 부분은 멘토님의 조언 내용을 바탕으로 적용해나가고 있습니다.
>
>ERD 설계를 바탕으로 프로젝트에 Entity 설계를 진행할 예정이며, 매너온도와 동행 정보와 같은<br>
>테이블의 세부적인 내용은 개발을 진행하면서 수정해나갈 생각입니다.
> 

### 2. 프로그램 흐름도 설계

> 프로젝트 진행 전, 팀원들과 프로젝트에 대한 이해도를 높이기 위해 진행하였습니다.
>
>이 과정을 통해 프로그램 흐름에 맞게 ER 다이어그램을 수정하면서 프로젝트에 대한 완성도를 높일 수 있었습니다.
>
>또한, 프로젝트를 진행하면서 발생할 오류를 발견하며 사전에 오류가 생길 부분에 대해서 미리 수정할 수 있었습니다.
> 

### 3. API 설계

> 각 기능을 어떤 HTTP method로 받아오고, 어떤 URI와 매핑할지 API 명세서를 작성했습니다. 
>
>메인 페이지와 이벤트 캘린더 관련 API, 게시판 관련 API, 게시글 관련 API, 댓글 관련 API,
>매너 온도를 구현하기 위한 매너 평가 API, 그리고 로그인, 로그아웃, 사용자 정보를 리턴하는 등의 member 관련 API를 설계했습니다. 
>
>요청 경로를 작성해보며 앞으로 진행할 프로젝트에서 어떤 페이지가 필요하고, <br>
>해당 경로에 어떤 request parameter나 path variable이 필요한지 생각해볼 수 있었습니다.
> 

## 개발 과정에서 나왔던 질문 (최소 200자 이상)

1. 회원 매너 평가폼 URL에 memberId(평가대상 회원Id)를 넣어 GET방식으로 요청을 해야하는가? body에 넣어 POST 방식으로 요청해야하는가?
    - GET 방식으로 url에 회원Id를 노출하여어도 보안 문제가 없다. 그러므로 URL 에 명시하고 싶으면 GET 방식으로, 보여주고 싶지 않으면 body에 넣어 POST 방식으로 처리한다.<br>
2. article 에서 event의 name, location, date를 가져오기 위해 event_id(FK)로 바로 접근할 것인가, board를 통해 접근할것인가?
    - select를 여러번 하더라도 순차적으로 하나씩 board를 통해 접근하는 것이 좋다
3. 동행 db 구조에 추가할 것이 있는가?
    - 왠만한 테이블엔 등록, 수정날짜를 넣는 것이 좋음
4. 카카오 로그인만 사용할 때 user에 loginId, loginPw가 필요한가?
    - 관리자 계정때문에 id, pw는 필요함(이 방식이 싫으면 관리자 table을 따로 설계해야함)
5. path variable이 경로의 가운데에 와도 되는가? <br>
    - 멘토님에게 여쭤보니 가능은 하지만 path variable은 경로 뒤쪽에 두는 것이 깔끔할 것 같다고 조언을 주셨습니다. <br>
6. 매너 평가나 회원정보 페이지에서 회원의 ID를 노출해도 되는가?
    - member ID의 경우, 보안상 우려된다면 숨기는 것이 나을 것 같다고 조언해주셨습니다.

## 개발 결과물 공유

Github Repository URL: [https://github.com/likelion-backendschool/Play_With_Me](https://github.com/likelion-backendschool/Play_With_Me)
