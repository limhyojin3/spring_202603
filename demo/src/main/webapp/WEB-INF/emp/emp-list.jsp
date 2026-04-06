<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/vue@3/dist/vue.global.js"></script>
    <script src="/js/page-change.js"></script>
    <style>
        table, tr, td, th{
            border : 1px solid black;
            border-collapse: collapse;
            padding : 5px 10px;
            text-align: center;
        }
        th{
            background-color: beige;
        }
        tr:nth-child(even){
            background-color: azure;
        }

        #index {
            text-decoration: none;
            color: black;
            padding: 3px;
            margin: 3px;
        }
        #index .active{
            font-weight: bold;
            color: blue;
        }
    </style>
</head>
<body>
    <div id="app">
        <!-- html 코드는 id가 app인 태그 안에서 작업 -->
        <div>
            <select v-model="pageSize" @click="fnGetList">
                <option value="5">5개씩</option>
                <option value="10">10개씩</option>
                <option value="20">20개씩</option>
            </select>
        </div>


        <div>
            <table>
                <tr>
                    <th>사번</th>
                    <th>이름</th>
                    <th>직급(job)</th>
                    <th>사수이름(mgr이름)</th>
                    <th>급여 등급</th>
                    <th>부서이름</th>
                </tr>
                <tr v-for="item in empList" >
                    <td>{{item.empNo}}</td>
                    <td>{{item.eName}}</td>
                    <td>{{item.job}}</td>
                    <td>{{item.mgrName}}</td>
                    <td>{{item.grade}}</td>
                    <td>{{item.dName}}</td>
                </tr>

            </table>
            <div>
                <a @click="fnPage(num)" id="index" href="javascript:;" v-for="num in index" >
                    <span :class="{active : currentPage == num}">{{num}}</span>
                    <!-- :class 는 문법이다 -->
                </a>
            </div>

            <div>
                <button @click="fnAdd">사원추가</button>
            </div>
        </div>
    </div>
</body>
</html>

<script>
    const app = Vue.createApp({
        data() {
            return {
                // 변수 - (key : value)
                empList : [],
                pageSize: 5,
                index : 1,    //총페이지사이즈 초기화
                currentPage : 1 // 현재페이지
            };
        },
        methods: {
            // 함수(메소드) - (key : function())
            fnGetList: function () {
                //페이지 누르고 param 넘기고 ajax 실행

                let self = this;
                let param = {
                    pageSize : self.pageSize,  //페이지사이즈
                    offSet : self.pageSize * (self.currentPage - 1)
                    //오프셋은 페이지사이즈 * (현재페이지 -1)
                    //규칙을 찾는것이 중요함!
                };
                $.ajax({
                    url: "http://localhost:8080/emp-list.dox",
                    dataType: "json",
                    type: "POST",
                    data: param,
                    success: function (data) {
                        console.log(data);

                        self.empList = data.list;
                        console.log(self.empList);

                        self.index = Math.ceil(data.totalCount / self.pageSize); //13/5
                        //index => 총 페이지수
                        console.log(self.index);
                    }
                });
            },
            fnAdd: function(){
                location.href="/emp-add.do"
            },
            fnPage : function(page){
                let self = this;
                self.currentPage = page;
                self.fnGetList();

            }
        }, // methods
        mounted() {
            // 처음 시작할 때 실행되는 부분
            let self = this;
            self.fnGetList();
        }
    });

    app.mount('#app');
</script>