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
        <script src="https://cdn.iamport.kr/v1/iamport.js"></script>
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
                <input placeholder="가격" v-model="price">
                <button @click="fnPayment">결제</button>
            </div>
        </div>
    </body>

    </html>

    <script>
        IMP.init("imp11372521");
        const app = Vue.createApp({
            data() {
                return {
                    // 변수 - (key : value)
                    price : 1

                };
            },
            methods: {
                // 함수(메소드) - (key : function())
                fnPayment: function () {
                    IMP.request_pay(
                        {
                            channelKey: "channel-key-d7380da7-4a8e-42dd-ab47-3d86afa250c8",
                            pay_method: "card",
                            merchant_uid: new Date().toLocaleString(), // 주문 고유 번호, 유니크해야함. PK로 쓰인다
                            name: "짜장면",
                            amount: 1,
                            buyer_email: "gildong@gmail.com",
                            buyer_name: "홍길동",
                            buyer_tel: "010-4242-4242",
                            buyer_addr: "서울특별시 강남구 신사동",
                            buyer_postcode: "01181",
                        },
                        function (response) {
                            console.log(response);
                            // 결제 종료 시 호출되는 콜백 함수
                            // response.imp_uid 값으로 결제 단건조회 API를 호출하여 결제 결과를 확인하고,
                            // 결제 결과를 처리하는 로직을 작성합니다.

                            if(response.success){
                                alert("결제되었습니다.");
                                // 우리쪽 db에 결제 정보 저장
                                // 페이지 이동 필요하면 페이지 이동
                            } else{
                                alert("결제 실패!");
                            }
                        },
                    );
                },

            }, // methods
            mounted() {
                // 처음 시작할 때 실행되는 부분
                let self = this;
            }
        });

        app.mount('#app');
    </script>