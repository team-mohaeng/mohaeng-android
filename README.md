

#  쟈니 <img src="https://user-images.githubusercontent.com/28949235/125798720-ee361799-6821-4460-bd8d-7641254dfb80.png" align=left width=100>
<br>
<br>

![쟈니보드흰버전](https://user-images.githubusercontent.com/28949235/125825984-5d6087d6-e8bd-4b4b-8ad8-004736141a6d.png)
<details>
	<summary><b>잃어버린 당신의 일상을 깨워줄 행복 메이트, 쟈니(Journey)</b><br></summary>
	쟈니는 일상에서 중요하지만 잊고 살아가는 것들을 끊임없이 일깨워주는 ‘행복 찾기’ 서비스입니다.  재미있는 콘셉트의 캐릭터가 사용자의 일상에 쉽게 다가감으로써 행복을 찾아내는 습관이 생길 수 있도록 도와줍니다.  푸시 알림으로 일상의 안부 메시지를 받아보고, 하루 단위 랜덤 챌린지를 완수하여 나만의 소확행을 기록하고 공유해보세요 . 
	</details>

## Journey's Workflow 

<details>
	<summary><b>Preview</b><br></summary>
	<div markdown="1">
    <img src="https://user-images.githubusercontent.com/28949235/125818953-985f2d8b-442d-41e6-833c-c82aaa95f672.png" alt="image" />
	<img src="https://user-images.githubusercontent.com/28949235/125819660-29a88675-1b4d-4a72-b358-5917d71b4f6b.png" />
	<img src="https://user-images.githubusercontent.com/58849278/125906281-1427c872-10af-4cad-b791-c9f6722ee39d.png" />
	<img src="https://user-images.githubusercontent.com/28949235/125819715-3f5a355f-5ee7-4465-999d-455550becd82.png" />
  <img src=" https://user-images.githubusercontent.com/58849278/125941121-64159098-e277-465a-a82c-5d1b152a9e1b.png" />
	<img src="https://user-images.githubusercontent.com/58849278/125941262-a64007c8-62d6-4f45-8188-726ad4ace3e0.png" />
	<img src="https://user-images.githubusercontent.com/58849278/125941379-a457ebc3-fbc8-4d84-a4c7-141d117d1ec7.png" />
  <img src="https://user-images.githubusercontent.com/28949235/125819842-019d3d42-0af6-4775-8b3e-276752416deb.png" />
  </div>
	</details>
	
## Tech Explanation

<details>
	<summary><b>Structure</b><br></summary>

🌹Journey
 ┣ 📑application
 ┣ 📑base
 ┣ 📑data
 ┣ 📑di
 ┣ 📑frame
 ┣ 📑challenge
 ┣ 📑community
 ┣ 📑course
 ┣ 📑diary
 ┣ 📑findpw
 ┣ 📑login
 ┣ 📑main
 ┣ 📑pushalarm
 ┣ 📑reward
 ┣ 📑signup
 ┗📑 utils
	</details>

<details>
	<summary><b>Open Source Library</b><br></summary>
	
| 라이브러리                                                   | 목적                                                    |
| ------------------------------------------------------------ | ------------------------------------------------------- |
| [Activity-KTX](https://developer.android.com/kotlin/ktx/extensions-list) | Activity에서 ViewModel 위임 초기화위해 사용             |
| [Fragment-KTX](https://developer.android.com/kotlin/ktx/extensions-list) | Fragment에서 Shared ViewModel 위임 초기화 위해 사용     |
| [Jetpack Navigation](https://developer.android.com/jetpack/androidx/releases/navigation) | Fragment간 화면 전환 용이                               |
| [LifeCycle](https://github.com/ausichenko/android-lifecycles) | Fragment 생명주기                                |
| [LiveData](https://github.com/ravi8x/LiveData)                   | LifeCycleOwner 관찰자 등록        |    
| [Retrofit2](https://github.com/square/retrofit)              | 서버 통신                                               |
| [Gson](https://github.com/google/gson)                       | 서버에서 받아온 Json 객체를 Gson으로 변환               |
| [OkHttp](https://square.github.io/okhttp/)                   | 서버 통신에서 토큰 Interceptor 등 Util 기능 제작에 활용 |
| [Firebase](https://firebase.google.com/)                     | 푸쉬 알람 구현에 활용                                             |
| [Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) | 의존성 주입                        |
| [Glide](https://github.com/bumptech/glide)                   | URL 형식의 이미지          |       
</details>



<details>
	<summary><b>Convention</b><br></summary>
	
📕  [Journey Coding Convention](https://github.com/team-journey/journey-android/wiki/Coding-Convention)

📙 [Journey Commit, branch Convention](https://github.com/team-journey/journey-android/wiki/Commit-Message-and-Branch-Convention)

📗 [Journey Package Convention](https://github.com/team-journey/journey-android/wiki/Package-Convention)

📘 [Journey Kanban board](https://www.notion.so/AND-cf17a9d32246486e899e4eb2be8a1396)

</details>

## Contributors

<table align="center" style = "table-layout: auto; width: 100%; table-layout: fixed;">
  <colgroup>
    <col style="width:33%"/>
    <col style="width:34%"/>
    <col style="width:33%"/>
  </colgroup>
  <th align="center">박주예</th>
  <th align="center">김기현</th>
  <th align="center">신승민</th>
  <tr>
    <td align="center"><img src="https://user-images.githubusercontent.com/58849278/125941800-3787c40d-d98b-4201-86f0-e7fe52941679.jpeg?size=10"/></td>
    <td align="center"><img src="https://user-images.githubusercontent.com/58849278/125941958-5c296c28-9c0a-497a-9fa8-b4851b9e7069.jpeg? size=1000 "/></td>
    <td align="center"><img src="https://user-images.githubusercontent.com/58849278/125942180-078e2514-6e01-48be-b945-237e12cb1b6d.jpeg? size = 900"/></td>
  </tr>
  <tr>
    <td>
    <br>
    Login<br>
    Main<br>
		Journey Push Alarm<br>
		Journey Community<br>
    </td>
    <td>
    Signup<br>
    Journey Challenge<br>
	Journey Course<br>
    </td>
    <td>
    Find Password<br>
    Journey Diary<br>
    </td>
  </tr>
</table>






















