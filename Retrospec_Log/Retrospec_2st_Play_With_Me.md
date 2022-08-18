## 팀 구성원

---

- [팀장] 우민식
- [팀원] 박범서, 주민지, 최승근, 한승연

## 회고 내용 요약 (최소 500자 이상)

---

각 회고내용 정리 내용
<details>
<summary><h3>1.박범서 - CPU 작동 원리, Git 협업 방법 </h1></summary>
<div markdown="1">

- https://lean-weaver-944.notion.site/Git-branch-72bd41990509451b9545cd27efe0c3ed
- https://lean-weaver-944.notion.site/CPU-762b17e9871e47e4af9718fdacbf85c3
</div>
</details>

<details>
<summary><h3>2.우민식 트랜잭션, JPA와 JDBC, Spring과 SpringBoot의 차이 </h1></summary>
<div markdown="1">

- https://honey-worm-a12.notion.site/2-ec840068a9ea4b378ddf4e8d16171d90
</div>
</details>

<details>
<summary><h3>3.한승연 - 인증과 인가, Spring Security, 자바 8 인터페이스</h1></summary>
<div markdown="1">

- https://subsequent-shroud-fd5.notion.site/_-344fbb63d48f44d49d5a2e6b37d3c9a2
</div>
</details>

<details>
<summary><h3>4. 최승근 - DIdhk IoC, TCP/IP, Mysql 아키텍처</h1></summary>
<div markdown="1">
  
- https://heavenly-rubidium-d41.notion.site/2-3637f196a4524e44a77f4213529f88e6
</div>
</details>

<details>
<summary><h3>5.주민지 - 제너릭, 자바 내 this, 상속과 조합</h1></summary>
<div markdown="1">

- https://forhighproductivity.notion.site/8-67791b76121c4f36ba4b59ffe5a75364
</div>
</details>


## 회고 과정에서 나왔던 질문 (최소 200자 이상)

---

상속은 언제 해야 하는가?
1. 상속을 고려한느 두 객체가 서로 IS-A 관계인가?
2. 클라이언트 관점에서 두 객체가 동일한 행동을 할 것이라고 기대하는가?
3. 위 두가지 질문에 모두 참일 경우에만 상속 고려

스프링의 의존성 주입 방법 중 어떤 것을 사용해야 하는 가? (필드 주입, setter주입, 생성자 주입)
1. 생성자 주입을 추천하며 setter주입은 선택적 주입에 사용해야 한다.
2. setter 주입은 주로 클래스 내에서 합리적인 기본 값을 할당할 수 있는 선택적 의존성에만 사용해야 한다.

인터페이스를 사용하는 이유는?
1. 인터페이스는 다중 상속이 가능하다
2. 공통의 조상을 갖지 않는 두 클래스에 관계를 맺을 수 있음
3. 사용자 입장에서는 내부적 구조에 대한 이해 없이 약속된 행위가 보장됨

