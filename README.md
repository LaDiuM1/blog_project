<h1>2024-02-20</h1>
<h3>리팩토리 진행</h3>
<table>
  <tr>
    <th>클래스</th><th>메서드</th><th>변경 내용</th>
  </tr>
  <tr>
    <td>AdminPostController</td><td>getPostList</th><td>게시글 리스트 요청 시 Http 요청 메서드 Post -> Get 변경<br/>기존 json 객체는 @RequestParam 이용 쿼리스트링으로 변경</td>
  </tr>
  <tr>
    <td>AdminCategoryService</td><td>updateCategorySequence</th><td>카테고리 순서 변경 시 updateCategoryValid 및 updateCategorySequence 커스텀 쿼리문 추가하여 검증 및 업데이트 진행으로 변경</td>
  </tr>
  <tr>
    <td>AdminPostService</td><td>postAddTags</th><td>포스트에 태그 추가하는 로직 변경 및 접근 제어 private로 설정</td>
  </tr>
  <tr>
    <td>AdminPostService</td><td>createPost</th><td>게시글 리스트 호출 로직 변경, 포스트와 댓글을 가져오는 로직과 포스트 연관 태그를 조회하는 쿼리문 분리하여 쿼리 실행 최소화 진행</td>
  </tr>
</table>
