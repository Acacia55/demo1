onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建调查问卷')

  $('#startTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  $('#endTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
}

/*TODO 跳转到designQuestionnaire函数*/
const gotoDesignQuestionnaire = () => {
  $.ajax({
    url: 'http://127.0.0.1:8085' + '/insertQuestionnaire',
    type: 'POST',
    data: JSON.stringify({
        projectName: localStorage.getItem('projectName'),
        answertype: localStorage.getItem('surveyType'),
        questionnaireName: $('#surveyName').val(),
        content: $('#surveyDescription').val(),
        startTime: $('#startT').val(),
        endTime: $('#endT').val()
    }),
    dataType: 'json',
    contentType: 'application/json;charset=UTF-8',
    success(res) {
      if (res.code === '666') {
        console.log('创建成功')
        localStorage.setItem('questionnaireId', res.data.id)
        location.href = '/pages/designQuestionnaire/index.html'
      }else {
        console.log(res.message)
      }
    }
  })

}
