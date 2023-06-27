onload = () => {
  $('#headerDivB').text('问卷详情')
  let projectId = $util.getPageParam('seeProject')
  fetchProjectInfo(projectId)
}

const fetchProjectInfo = (id) => {
  let params = {
    id
  }
  $.ajax({
    url: API_BASE_URL + '/queryQuestionnaireList',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      res.data.map(item => {
        $('#table').append(`
          <tbody>
         <tr>
          <td>${item.questionnaireName}</td>
          <td>${item.username}</td>
          <td>${item.answertime}</td>
          <td>
            <button type="button" class="btn btn-link">查看</button>
          </td>
          </tr>
            </tbody>
        `)
      })
    }
  })
}