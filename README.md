# Spring Boot 기반 쇼핑몰 Project

![홈페이지](README.assets/%ED%99%88%ED%8E%98%EC%9D%B4%EC%A7%80.PNG)

> 목표
- 개발 프레임워크를 이해하고 이를 활용한 쇼핑몰 홈페이지 제작
---
> 주요 구현 기능

- **[회원가입](https://github.com/wlgud2/shopping/blob/master/%EC%B0%B8%EA%B3%A0%EC%82%AC%EC%A7%84/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.PNG), [로그인/로그아웃](https://github.com/wlgud2/shopping/blob/master/%EC%B0%B8%EA%B3%A0%EC%82%AC%EC%A7%84/%EB%A1%9C%EA%B7%B8%EC%9D%B8.PNG)**
- **[MyPage](https://github.com/wlgud2/shopping/blob/master/%EC%B0%B8%EA%B3%A0%EC%82%AC%EC%A7%84/%EB%A7%88%EC%9D%B4%ED%8E%98%EC%9D%B4%EC%A7%80.PNG)**
  - 주문목록, 리뷰
- **[공지사항](https://github.com/wlgud2/shopping/blob/master/%EC%B0%B8%EA%B3%A0%EC%82%AC%EC%A7%84/%EA%B3%B5%EC%A7%80.PNG)**
  - 목록, 등록, 수정, 삭제
- **[상품](https://github.com/wlgud2/shopping/blob/master/%EC%B0%B8%EA%B3%A0%EC%82%AC%EC%A7%84/%EC%83%81%ED%92%88.PNG)**
  - 목록, 상세페이지
  - 등록, 수정, 삭제
  - _주문하기, 장바구니 -- 구현 중_
- **[QnA](https://github.com/wlgud2/shopping/blob/master/%EC%B0%B8%EA%B3%A0%EC%82%AC%EC%A7%84/1%EB%8C%801%EB%AC%B8%EC%9D%98.PNG)**
  - 챗봇

---

> 코드

1. 공지사항
   - [view](https://github.com/wlgud2/shopping/tree/master/src/main/webapp/WEB-INF/views/notice)
   - [controller](https://github.com/wlgud2/shopping/blob/master/src/main/java/com/study/notice/NoticeController.java)
2. 상품(목록, 등록, 수정, 삭제)
   - [view](https://github.com/wlgud2/shopping/tree/master/src/main/webapp/WEB-INF/views/contents)
   - [controller](https://github.com/wlgud2/shopping/blob/master/src/main/java/com/study/contents/ContentsController.java)
3. 주문하기
   - [view](https://github.com/wlgud2/shopping/tree/master/src/main/webapp/WEB-INF/views/orders)
   - [controller](https://github.com/wlgud2/shopping/blob/master/src/main/java/com/study/orders/OrdersController.java)
4. 장바구니
   - [view](https://github.com/wlgud2/shopping/tree/master/src/main/webapp/WEB-INF/views/cart)
   - [controller](https://github.com/wlgud2/shopping/blob/master/src/main/java/com/study/cart/CartController.java)

