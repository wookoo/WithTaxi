# WithTaxi
안드로이드 스튜디오로 만든 택시 합승 앱 입니다.

안드로이드 어플 개발 환경 : 안드로이드 스튜디오, Java, Windows 10

- 사용 라이브러리 : org.java-websocket:Java-WebSocket:1.3.0

  

어플리케이션 서버 개발 환경 : Django 2 이상, Python 3 이상, ubuntu / Windows 10

 - 사용 라이브러리 : Django Channels

 - 사전 설치 프로그램 : Redis

   

어플리케이션 서버는 [여기서](<https://github.com/wookoo/WithTaxi-Server>) 상세 정보를 확인 가능합니다.

어플리케이션 알고리즘은 다음과 같습니다.

1. 출발지 선택
2. 도착지 선택
3. 출발지와 도착지를 Json 형식으로 만들어, 출발지(영어)\_to\_도착지(영어) 형으로 만듭니다.
4. 만든 출발지 도착지를 링크 형태로 만듭니다.
5. 해당 링크에 webSocket 을 연결합니다.
6. 서버에서 반환값이 있으면 WebSocket 객체를 강제적으로 끊고, 가비지 컬렉터로 객체를 초기화 시킵니다.
7. 받아온 반환값은 전화번호입니다. 전화번호를 Dialog 를 통해 보여주고, 메세지를 전송할지 말지 보여줍니다.