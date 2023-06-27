onload = () => {
  // 获取问卷信息
  $.ajax({
    url: '/query',
    type: 'post',
    data: JSON.stringify({
      answerName: localStorage.getItem("answerName"),
      questionnaireId: localStorage.getItem("questionnaireId")
    }),
    dataType: 'json',
    contentType: 'application/json',
    success: (res) => {
      // 设置问卷标题
      $('.questionnaire-title').text(res.data.questionnaireEntity.questionnaireName)
      // 设置问卷描述
      $('.questionnaire-description').html(`<h4>姓名：${localStorage.getItem("currentUserName")}</h4><h4>描述：${res.data.questionnaireEntity.content}</h4>`)
      // 获取problem数组
      let problemArray = res.data.problems
      // 遍历problem数组
      problemArray.forEach((item, index) => {
        appendProblem(item, index+1)
      })
      // 如果code为201，说明是查看答卷，因此要渲染答案,并且不显示提交按钮
      if (res.code == 201) {
        problemArray.forEach((problem, problemIndex) => {
          problem.answers.forEach((option, optionIndex) => {
            let i = document.getElementById(option.optionid)
            i = $(i)
            i.prop('checked', 'checked')
          })
        })
        $('#submit').css('display', 'none')
      }

      // 定义一个答案类
      class Answer {
        constructor(id, userid, optionid, username, answertime) {
            this.id = id
            this.userid = userid
            this.optionid = optionid
            this.username = username
            this.answertime = answertime
        }
      }

      // 点击提交按钮
      $('#submit').click(() => {
        // 定义一个数组，用来存放答案
        let answerArray = []
        // 循环所有的选项，如果选项被选中，就将选项的id放入数组中
        problemArray.forEach((problem, problemIndex) => {
          problem.options.forEach((option, optionIndex) => {
            let i = document.getElementById(option.id)
            i = $(i)
            let state = i.prop('checked')
            if (state) {
              let userInfo = localStorage.getItem("userInfo")
              userInfo = JSON.parse(userInfo)
              answerArray.push(new Answer(null, userInfo.id, option.id, userInfo.username, new Date()))
            }
          })
        })
        // 发送ajax
        $.ajax({
            url: 'http://localhost:8085' + '/addAnswers',
            type: 'post',
            data: JSON.stringify(answerArray),
            dataType: 'json',
            contentType: 'application/json',
            success: (res) => {
                if (res.code == 200) {
                    alert("提交成功")
                } else {
                    alert("提交失败")
                }
            }
        })
      })



    },
    error: (err) => {
      console.log(err)
    }
  })

  /**
   * 渲染问卷题目
   * @param problem，题目对象
   * @param index，题号
   */
  const appendProblem = (problem, index) => {
    let type
    // 判断题目类型
    if (problem.questionEntity.questionType == "1") {
      type = "单选题"
    }else if (problem.questionEntity.questionType == "2") {
      type = "多选题"
    }

    let appendStr = `
      <div class="question" id="${problem.questionEntity.questionId}" data-type="${problem.questionEntity.questionType}" data-problemIndex="1">
        <div class="top">
          <span class="question-title" id="questionTitle">${index}.${problem.questionEntity.questionTypeName}(${type})</span>
          <!--<span class="must-answer" id="mustAnswer">必答题</span>-->
        </div>
        <div class="bottom">
    `

    // 获取选项数组
    let optionArray = problem.options
    // 遍历选项数组
    optionArray.forEach((item, index) => {
      if (type == "单选题") {
        let str = radioTemplate(item, problem.questionEntity.questionId)
        appendStr += str
      }else if (type == "多选题") {
        let str = checkboxTemplate(item, problem.questionEntity.questionId)
        appendStr += str
      }
    })
    appendStr += `
        </div>
      </div>
        </div>
      </div>
    `
    $('#problem').append(appendStr)
  }

  // 单选题模板
  const radioTemplate = (option, questionId) => {
    return `
      <div style="display: flex; align-items: center; margin-bottom: 3px;">
        <label class="radio-inline">
          <input type="radio" id="${option.id}" name="${questionId}" optionId="${option.id}">${option.optionContent}
        </label>
      </div>
    `
  }

  // 多选题模板
  const checkboxTemplate = (option, questionId) => {
      return `
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="checkbox-inline">
              <input type="checkbox" id="${option.id}" name="${questionId}" optionId="${option.id}">${option.optionContent}
          </label>
          </div>
      `
  }

  /*$('#problem').append(`
    <div class="question" id="question1" data-type="1" data-problemIndex="1">
      <div class="top">
        <span class="question-title" id="questionTitle">1.单选题</span>
        <span class="must-answer" id="mustAnswer">必答题</span>
      </div>
      <div class="bottom">
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="radio-inline">
            <input type="radio" name="chooseTerm">选项1
          </label>
        </div>
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="radio-inline">
            <input type="radio" name="chooseTerm">选项2
          </label>
        </div>
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="radio-inline">
            <input type="radio" name="chooseTerm">选项3
          </label>
        </div>
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="radio-inline">
            <input type="radio" name="chooseTerm">选项4
          </label>
        </div>
      </div>
    </div>
  `)
  $('#problem').append(`
    <div class="question" id="question1" data-type="1" data-problemIndex="1">
      <div class="top">
        <span class="question-title" id="questionTitle">2.多选题</span>
        <span class="must-answer" id="mustAnswer">必答题</span>
      </div>
      <div class="bottom">
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="checkbox-inline">
            <input type="checkbox" name="chooseTerm">选项1
          </label>
        </div>
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="checkbox-inline">
            <input type="checkbox" name="chooseTerm">选项2
          </label>
        </div>
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="checkbox-inline">
            <input type="checkbox" name="chooseTerm">选项3
          </label>
        </div>
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <label class="checkbox-inline">
            <input type="checkbox" name="chooseTerm">选项4
          </label>
        </div>
      </div>
    </div>
  `)
  $('#problem').append(`
    <div class="question" id="question1" data-type="1" data-problemIndex="1">
      <div class="top">
        <span class="question-title" id="questionTitle">3.填空题</span>
        <span class="must-answer" id="mustAnswer">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
    </div>
  `)
  $('#problem').append(`
    <div class="question" id="question1" data-type="1" data-problemIndex="1">
      <div class="top">
        <span class="question-title" id="questionTitle">4.矩阵题</span>
        <span class="must-answer" id="mustAnswer">必答题</span>
      </div>
      <div class="bottom">
        <table class="table">
          <thead>
            <tr>
              <th></th>
              <th>选项1</th>
              <th>选项2</th>
              <th>选项3</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>标题1</td>
              <td><input type="radio" name="chooseTerm1" /></td>
              <td><input type="radio" name="chooseTerm1" /></td>
              <td><input type="radio" name="chooseTerm1" /></td>
            </tr>
            <tr>
              <td>标题2</td>
              <td><input type="radio" name="chooseTerm2" /></td>
              <td><input type="radio" name="chooseTerm2" /></td>
              <td><input type="radio" name="chooseTerm2" /></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  `)
  $('#problem').append(`
    <div class="question" id="question1" data-type="1" data-problemIndex="1">
      <div class="top">
        <span class="question-title" id="questionTitle">5.量表题</span>
        <span class="must-answer" id="mustAnswer">必答题</span>
      </div>
      <div class="bottom" style="display: flex; align-items: center; justify-content: space-between;">
        <div>很满意</div>
        <div>
          <label class="radio-inline">
            <input type="radio" name="fraction" />5
          </label>
        </div>
        <div>
          <label class="radio-inline">
            <input type="radio" name="fraction" />4
          </label>
        </div>
        <div>
          <label class="radio-inline">
            <input type="radio" name="fraction" />3
          </label>
        </div>
        <div>
          <label class="radio-inline">
            <input type="radio" name="fraction" />2
          </label>
        </div>
        <div>
          <label class="radio-inline">
            <input type="radio" name="fraction" />1
          </label>
        </div>
        <div>很不满意</div>
      </div>
    </div>
  `)*/
}
