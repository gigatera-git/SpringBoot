SpringBoot > QnA Board (basic)

It's qna board 
    and()
      csrf of spring security
    and()
      mapper for maria db
    and() 
      thymeleaf for html template

[주의사항]

*) @PostMapping 시 url에 QueryString으로 값을 넘기고 있다면 굳이 hidden textbox를 만들지 않아도 값이 불러와진다.

*) Get, Post 가 같은 uri을 매핑하여 사용할때
   Post에서 @valid처리후 return 하면 Get 매핑된 곳으로 리턴되는 것이 아니라 현재 Post매핑된 위치에서 값만 넘기는것이다
   (어찌 보면 당연한테 스크립트만 사용해본 사람이면 오해가 가능하다)
   따라서 같은 결과를 내려면 Get, Post 매핑된 함수내 처리 내용이 같아야 한다.
   
*) Spring Security csrf 사용시
   th:action="@{/test}"  타임리프 action태그가 있어야 csrf hidden textbox 자동 생성된다. 없다면 아래처럼 수동으로 입력해야 한다.
   
   <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}">
   
   
*) form tag에 th:object="{boardVO}" 사용시
   해당 프로퍼티에 값이 입력되면 th:field="*{uname}" 등에 자동 연동된다.
   
   
*) th:field = th:name + th:id  + th:value 와 동일하다.
