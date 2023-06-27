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
            <button type="button" class="btn btn-link" onclick="seeQuestionnaire('${item.id}', '${item.username}')">查看</button>
          </td>
          </tr>
            </tbody>
        `)
      })
    }
  })
}

const seeQuestionnaire = (id, name) => {
    localStorage.setItem("questionnaireId", id)
    localStorage.setItem("answerName", name)
    localStorage.setItem('currentUserName', name)
    location.href = '/pages/answerSheet/index.html'
}