# congcongjoacafe-backend

![KakaoTalk_20241111_183904023](https://github.com/user-attachments/assets/702c0317-959e-4425-b9f7-b0c46bc5c47e)

### **CONGCONGJOACONGCAFE 요구사항 분석**

**프로젝트 개요**

- **목적:** 모바일과 웹 환경에서 카페 주문을 효율적으로 관리하고, 관리자, 매장주(점주), 일반 유저를 위한 맞춤형 기능을 제공하여 주문, 결제, 멤버십, 재고 관리 등을 가능하게 합니다.
- **플랫폼:** 모바일과 웹 최적화(Responsive Design), 백엔드(Spring Boot, JPA, MySQL, Redis), 프론트엔드(React, Tailwind CSS, ShadCN)

### **1. 사용자 시나리오**

- **관리자(Admin):** 모든 매장의 승인 및 관리, 메뉴 및 재고 관리, 퍼스널 옵션 설정, 멤버십 정책 관리(등급, 포인트, 쿠폰), 매출 분석
- **점주(Store Owner):** 매장 등록 및 매출 관리, 매장 별 메뉴 및 재고 관리, 주문 승인/거절, 실시간 고객 채팅
- **일반 유저(Customer):** 회원가입, 로그인 후 근처 매장 검색, 매장 메뉴 선택 및 주문, 퍼스널 옵션 설정, 결제, 장바구니, 포인트 및 쿠폰 사용, 실시간 문의 채팅

---

### **2. 기능 목록**

### **A. 공통 기능 (All Users)**

- **회원가입 / 로그인**
    - SNS 간편 로그인 (예: 카카오, 네이버)
    - 이메일 회원가입 (이메일 중복 확인, 비밀번호 복구 기능 포함)
- **대시보드 홈**
    - 유저 권한에 따라 다른 대시보드 접근
    - 공지사항 및 최신 업데이트 사항 표시

### **B. 관리자 기능 (Admin)**

1. **매장 승인 및 관리**
    - 매장 등록 요청 리스트 및 승인/거절
    - 등록된 매장 상세 정보(매출, 메뉴, 위치 등) 관리
2. **메뉴 및 옵션 관리**
    - 기본 메뉴 및 옵션 설정(예: 사이즈, 온도, 추가 옵션 등)
    - 카테고리별 메뉴 정렬 및 검색 기능
3. **멤버십 관리**
    - 등급 설정 (예: 일반, VIP, 프리미엄)
    - 포인트 정책 설정 (주문 시 포인트 적립, 사용 기준)
    - 쿠폰 발급 및 관리 (할인율, 유효기간 설정)
4. **매출 분석**
    - 일간, 주간, 월간 매출 리포트
    - 매출 추이, 인기 메뉴, 시간대별 판매 통계

### **C. 점주 기능 (Store Owner)**

1. **매장 등록 및 관리**
    - 매장 정보 등록 (주소, 영업 시간, 전화번호 등)
    - 매장별 메뉴 등록 및 재고 관리 (재고 알림 설정)
2. **주문 관리**
    - 실시간 주문 확인 및 승인/거절 기능
    - 주문 목록 필터링(처리 완료, 승인 대기, 거절 등)
    - 주문 처리 상태에 따른 고객 알림
3. **재고 관리**
    - 메뉴 및 옵션별 재고 관리 (예: 음료 추가 옵션 관리)
    - 재고 소진 알림 및 자동 갱신
4. **고객 채팅 지원**
    - 실시간 채팅으로 고객과 소통
    - 채팅 문의 대응 기록 조회

### **D. 고객 기능 (Customer)**

1. **매장 검색 및 선택**
    - 현재 위치 기반 매장 추천 및 검색
    - 카테고리별, 거리별, 인기 순위별 매장 필터링
2. **주문 및 결제**
    - 메뉴 선택 및 퍼스널 옵션 설정 (예: 음료 크기, 시럽 추가)
    - 장바구니에 담기, 주문하기
    - 결제 수단 선택 및 결제 완료 화면
3. **멤버십 혜택**
    - 포인트 적립 및 사용 (구매 시 자동 적용 가능)
    - 쿠폰 다운로드 및 적용 가능 여부 확인
4. **고객 지원 채팅**
    - 매장과 실시간 채팅으로 문의 가능
    - 주문 관련 질문 및 답변 확인

---

### **3. 기술 사양**

- **프론트엔드**
    - **프레임워크:** React
    - **UI 라이브러리:** Tailwind CSS, ShadCN
    - **스타일 테마:** 로고 컬러에 맞춘 테마 적용 (관리자 - 네이비 계열, 점주 - 그린 계열, 고객 - 오렌지 계열 등)
    - **반응형 디자인:** 모바일, 웹 최적화 (미디어 쿼리 활용)
- **백엔드**
    - **프레임워크:** Spring Boot, JPA
    - **DB:** MySQL (유저 정보 및 주문, 매출 데이터), Redis (채팅 및 실시간 데이터 관리)
    - **API 구조:** RESTful API, WebSocket for Real-time Chat
    - **인증 및 보안:** JWT (JSON Web Token) for session handling, HTTPS, 데이터 암호화
    - **서버 배포:** AWS, Docker

---

### **4. 데이터베이스 설계**

### **MySQL**

- **사용자 테이블 (User)**
    - ID, 이름, 이메일, 비밀번호 해시, 권한(관리자, 점주, 유저), 가입일자
- **매장 테이블 (Store)**
    - ID, 이름, 주소, 영업 시간, 점주 ID(FK), 등록일자
- **메뉴 테이블 (Menu)**
    - ID, 이름, 가격, 매장 ID(FK), 재고 수량, 카테고리, 옵션
- **주문 테이블 (Order)**
    - ID, 사용자 ID(FK), 매장 ID(FK), 주문 시간, 주문 상태(대기, 승인, 거절), 총 금액
- **멤버십 테이블 (Membership)**
    - ID, 사용자 ID(FK), 등급, 포인트, 쿠폰 리스트
- **매출 테이블 (Sales)**
    - ID, 매장 ID(FK), 일자, 매출 금액, 결제 수단

### **Redis**

- **채팅 데이터 (Chat)**
    - 키 형식: `chat:{store_id}:{user_id}`
    - 실시간 채팅 기록 저장 및 조회용
- **주문 상태 (Order Status)**
    - 키 형식: `order_status:{order_id}`
    - 주문 상태 업데이트 시 실시간 반영

---

### **5. 추가 고려 사항**

- **알림 기능:** 주문 상태 변경, 재고 부족, 쿠폰 및 포인트 적립 시 푸시 알림 제공
- **로그 및 오류 처리:** 서버와 클라이언트에서 발생하는 주요 이벤트 및 오류를 로깅하여 관리
- **테스트 및 QA:** 각 권한별 사용자 경험을 테스트하고 주요 결제 및 주문 프로세스를 QA를 통해 검증
