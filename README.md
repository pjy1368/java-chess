# java-chess
체스 게임 구현을 위한 저장소

## 미션 소개
### 1단계 기능 요구 사항
- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

#### 실행 결과
![image](https://user-images.githubusercontent.com/56083021/121466351-41509200-c9f2-11eb-986a-612f6f122d54.png)

### 2단계 기능 요구 사항
- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 체스 말의 이동 규칙을 찾아보고 체스 말이 이동할 수 있도록 구현한다.
- move source위치 target위치을 실행해 이동한다.
- 체스판의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.

#### 실행 결과
![image](https://user-images.githubusercontent.com/56083021/121466420-60e7ba80-c9f2-11eb-9392-2de2fef4a54e.png)

### 3단계 기능 요구 사항
- 체스 게임은 상대편 King이 잡히는 경우 게임에서 진다. King이 잡혔을 때 게임을 종료해야 한다.
- 체스 게임은 현재 남아 있는 말에 대한 점수를 구할 수 있어야 한다.
- "status" 명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 볼 수 있어야 한다.
- 점수 계산 규칙
  - 체스 프로그램에서 현재까지 남아 있는 말에 따라 점수를 계산할 수 있어야 한다.
  - 각 말의 점수는 queen은 9점, rook은 5점, bishop은 3점, knight는 2.5점이다.
  - pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.
  - king은 잡히는 경우 경기가 끝나기 때문에 점수가 없다. 
  - 한 번에 한 쪽의 점수만을 계산해야 한다.

#### 실행 결과
- 특정 플레이어의 왕이 잡힌 경우  
![image](https://user-images.githubusercontent.com/56083021/121466848-131f8200-c9f3-11eb-883e-172278910b1e.png)

- 게임이 도중의 종료된 경우  
![image](https://user-images.githubusercontent.com/56083021/121467055-6560a300-c9f3-11eb-8b68-177ee442d48f.png)

### 4, 5단계 기능 요구 사항
- 콘솔 UI와 더불어 웹으로 체스 게임이 가능해야 한다.
- 웹 서버를 재시작하더라도 이전에 하던 체스 게임을 다시 시작할 수 있어야 한다.

### 실행 결과
![image](https://user-images.githubusercontent.com/56083021/121467184-a0fb6d00-c9f3-11eb-9410-afb41f7846fd.png)

## Commit Convention
- 커밋 메시지 언어 : 한글
- feat : 기능 추가.
- refactor : 구조 개선. 
- fix : 에러가 나는 부분 해결.
- docs : document 파일 관련.
- test : 테스트 코드만 추가하거나 바꿀 때 사용.
- style : 들여쓰기, 변수명, 메소드명 수정 및 기타 수정 사항.
- 커밋 단위 : 기능 단위로 커밋을 하되, 테스트와 기능 커밋 구분.
