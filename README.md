<h1><strong>:: SpringBoot ::</strong></h1>
<p><strong><span style="background-color: #ffcc00;">1. 개발환경</span></strong><br />&nbsp; &nbsp;- Spring Security, Mapper, Thymeleaf</p>
<p><strong><span style="background-color: #ffcc00;">2. 폴더 설명</span></strong> <br />&nbsp; &nbsp;- Qna Board &gt; 답변형 게시판 <br />&nbsp; &nbsp;- login n out with security &gt; 로그인/아웃 처리(시큐리티)</p>
<p><strong><span style="background-color: #ffcc00;">※ 주의사항</span></strong> <br />&nbsp; &nbsp;1) Get, Post 가 같은 uri을 매핑하여 사용할때 <br />&nbsp; &nbsp; &nbsp; &nbsp;@PostMapping 시 url에 QueryString으로 값을 넘기고 있다면 <br />&nbsp; &nbsp; &nbsp; &nbsp;굳이 hidden textbox를 만들지 않아도 값이 불러와진다. <br />&nbsp; &nbsp;2) Post에서 @valid처리후 return 하면 Get 매핑된 곳으로 리턴되는 것이 아니라 <br />&nbsp; &nbsp; &nbsp; &nbsp;현재 Post매핑된 위치에서 값만 넘기는것이다 <br />&nbsp; &nbsp; &nbsp; &nbsp;(어찌 보면 당연한테 스크립트만 사용해본 사람이면 오해가 가능하다) <br />&nbsp; &nbsp; &nbsp; &nbsp;따라서 같은 결과를 내려면 Get, Post 매핑된 함수내 처리 내용이 같아야 한다. <br />&nbsp; &nbsp;3) Spring Security csrf 사용시 th:action="@{/test}" 타임리프 action태그가 있어야 <br />&nbsp; &nbsp; &nbsp; &nbsp;csrf hidden textbox 자동 생성된다.&nbsp;없다면 수동으로 입력해야 한다. <br />&nbsp; &nbsp; &nbsp; &nbsp;input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" <br />&nbsp; &nbsp;4) form tag에 th:object="{boardVO}" 사용시 <br />&nbsp; &nbsp; &nbsp; &nbsp;해당 프로퍼티에 값이 입력되면 th:field="*{uname}" 등에 자동 연동된다. <br />&nbsp; &nbsp;5) th:field = th:name + th:id + th:value 와 동일하다.&nbsp;</p>
