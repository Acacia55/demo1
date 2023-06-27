onload = () => {
  $('#headerDivB').text('项目详情')

  let projectId = $util.getPageParam('seeProject')
  console.log(projectId, 'projectId')

  fetchProjectInfo(projectId)
}

const fetchProjectInfo = (id) => {

  let params = {
    id
  }
  // 请求项目详情
  $.ajax({
    url: API_BASE_URL + '/queryProjectList',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let info = res.data[0]
      console.log(info, 'res')
      $('#projectName').text(info.projectName)
      $('#createTime').text(info.creationDate)
      $('#projectDescription').text(info.projectContent)
      $('#personInCharge').text(info.createdBy)

      // 请求项目成员
      $.ajax({
        url: API_BASE_URL + '/queryQuestionnaires',
        type: "POST",
        data: JSON.stringify({
          projectName: $('#projectName').text()
        }),
        dataType: "json",
        contentType: "application/json",
        success(res) {
          res.data.forEach((questionnaire, index) => {
            $('#table').append(`
          <tbody>
            <tr>
                <td>${index + 1}</td>
                <td>${questionnaire.questionnaireName}</td>
                <td>${questionnaire.startTime}</td>
                <td>
                    <button class="btn btn-primary" onclick="preview('${questionnaire.id}')">预览</button>
                    <button class="btn btn-primary">发布</button>
                    <button class="btn btn-primary">删除</button>
                    <button class="btn btn-primary">统计</button>
                </td>
            </tr>
          </tbody>
        `)
          })
        }
      })
    }
  })


}
const preview = (questionnaireId) => {
  let userInfo = JSON.parse(localStorage.getItem('userInfo'))
  localStorage.setItem('answerName', "")
  localStorage.setItem('questionnaireId', questionnaireId)
  localStorage.setItem('currentUserName', userInfo.username)
  location.href = '/pages/answerSheet/index.html'
}