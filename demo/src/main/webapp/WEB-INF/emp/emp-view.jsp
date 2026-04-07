<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
        <script src="/js/page-change.js"></script>
        <style>
            table,
            tr,
            td,
            th {
                border: 1px solid black;
                border-collapse: collapse;
                padding: 5px 10px;
                text-align: center;
            }

            th {
                background-color: beige;
            }

            tr:nth-child(even) {
                background-color: azure;
            }
        </style>
    </head>

    <body>
        <div id="app">
            <!-- html 코드는 id가 app인 태그 안에서 작업 -->
            <div>
                <div>
                    <label>사번 : <input v-model="info.empNo"></label>
                </div>
                <div>
                    <label>이름 : <input v-model="info.eName"></label>
                </div>
                <div>
                    <label>직급 : <input v-model="info.job"></label>
                </div>
                <div>
                    <label>사수 이름 : <input v-model="info.mgrName"></label>
                </div>
                <div>
                    <label>급여등급 : <input v-model="info.grade"></label>
                </div>
                <div>
                    <label>부서이름 : <input v-model="info.dName"></label>
                </div>
            </div>
            <div>
                <button @click="fnRemove">삭제</button>
            </div>
        </div>
    </body>

    </html>

    <script>
        const app = Vue.createApp({
            data() {
                return {
                    // 변수 - (key : value)
                    empNo: "${empNo}",
                    info: {}
                };
            },
            methods: {
                // 함수(메소드) - (key : function())
                fnView: function () {
                    let self = this;
                    let param = {
                        empNo: self.empNo
                    };
                    $.ajax({
                        url: "http://localhost:8080/emp-view.dox",
                        dataType: "json",
                        type: "POST",
                        data: param,
                        success: function (data) {
                            console.log(data);
                            console.log(data.info);

                            // 콘솔에 찍는거까지 성공! -> 그이후 (+)

                            self.info = data.info;
                        }
                    });
                },
                fnRemove: function () {

                    if (confirm("정말 삭제?")) {
                        alert("삭제할게~");

                        let self = this;
                        let param = {
                            empNo: self.empNo
                        };
                        $.ajax({
                            url: "http://localhost:8080/emp-remove.dox",
                            dataType: "json",
                            type: "POST",
                            data: param,
                            success: function (data) {
                                console.log(data);
                                console.log(data.result);

                                if(data.result == 'success'){
                                    alert(data.message);
                                    location.href="/emp-list.do"
                                } 
                            }
                        });



                    } else {
                        alert("삭제 취소");
                    }
                }
            }, // methods
            mounted() {
                // 처음 시작할 때 실행되는 부분
                let self = this;
                console.log(self.empNo);
                self.fnView();
            }
        });

        app.mount('#app');
    </script>