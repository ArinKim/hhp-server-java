# API 명세

## 목록
- [잔액 조회](#잔액-조회)
- [잔액 충전](#잔액-충전)
- [상위 상품 조회](#상위-상품-조회)
- [선착순 쿠폰 기능](#선착순-쿠폰-기능)
- [주문 / 결제](#주문--결제)

## 잔액 조회
GET /api/v1/{userId}/balance
- **설명**: 사용자 식별자를 통해 해당 사용자의 잔액을 조회합니다.
### Request
- Method: `GET`
- Path Parameter
  - `userId` (string): 사용자 식별자
  - **예시**: `user123`
- Headers
  - Content-Type: `application/json`
  - `Authorization` (string): 인증 토큰 (선택적)
- Request Body:
```json
{
  "userId": "user123"
}
```

### Response
- Status Code: `200 OK`
- Response Body:
```json
{
  "uid": "user123",
  "name": "John Doe",
  "balance": 10000
}
```
### Errors
- `400 Bad Request`: 잘못된 요청
- `401 Unauthorized`: 인증 실패
- `404 Not Found`: 사용자 정보가 존재하지 않음
- `500 Internal Server Error`: 서버 오류

## 잔액 충전
POST /api/v1/{userId}/balance/add
- **설명**: 사용자 식별자 및 충전할 금액을 받아 잔액을 충전합니다.
### Request
- Method: `POST`
- Path Parameter
    - `userId` (string): 사용자 식별자
    - **예시**: `user123`
- Headers
    - Content-Type: `application/json`
    - `Authorization` (string): 인증 토큰 (선택적)
- Request Body:
```json
{
  "userId": "user123",
  "amount": 5000
}
```

### Response
- Status Code: `201 Created`
- Response Body:
```json
{
  "uid": "user123",
  "name": "John Doe",
  "balance": 10000
}
```
### Errors
- `400 Bad Request`: 잘못된 요청
- `401 Unauthorized`: 인증 실패
- `404 Not Found`: 사용자 정보가 존재하지 않음
- `500 Internal Server Error`: 서버 오류

## 상위 상품 조회
GET /api/v1/products/popular
- **설명**: 상위 상품을 조회합니다.
### Request
- Method: `GET`
- Headers
  - Content-Type: `application/json`
- Request Body: 없음
### Response
- Status Code: `200 OK`
- Response Body:
```json
{
  "products": [
    {
      "id": "product1",
      "name": "Product 1",
      "price": 10000
    },
    {
      "id": "product2",
      "name": "Product 2",
      "price": 20000
    },
    {
      "id": "product3",
      "name": "Product 3",
      "price": 30000
    },
    {
      "id": "product4",
      "name": "Product 4",
      "price": 40000
    },
    {
      "id": "product5",
      "name": "Product 5",
      "price": 50000
    }
  ]
}
```
### Errors
- `400 Bad Request`: 잘못된 요청
- `500 Internal Server Error`: 서버 오류

## 선착순 쿠폰 기능
POST /api/v1/coupons/issue
- **설명**: 선착순 쿠폰 발급 API 및 보유 쿠폰 목록 조회 API를 작성합니다.
### Request
- Method: `POST`
- Headers
  - Content-Type: `application/json`
  - `Authorization` (string): 인증 토큰 (선택적)
- Request Body:
```json
{
  "uid": "user123",
  "coupon_id": "coupon123"

}
```
### Response
- Status Code: `201 Created`
-  Response Body:
```json
{
  "coupon_id": "coupon123",
  "uid": "user123",
  "created_at": "2023-10-01T12:00:00Z"
}
```
### Errors
- `400 Bad Request`: 잘못된 요청
- `401 Unauthorized`: 인증 실패
- `404 Not Found`: 사용자 정보가 존재하지 않음
- `500 Internal Server Error`: 서버 오류
- `409 Conflict`: 쿠폰 발급 한도 초과
- `429 Too Many Requests`: 요청이 너무 많음 (선착순 쿠폰 발급 제한 초과)
- `503 Service Unavailable`: 서비스 일시 중단 (쿠폰 발급 서비스가 일시적으로 사용 불가)
- `504 Gateway Timeout`: 쿠폰 발급 서비스 응답 시간 초과

## 주문 / 결제
POST /api/v1/orders
- **설명**: 사용자 식별자와 (상품 ID, 수량) 목록을 입력받아 주문하고 결제를 수행하는 API를 작성합니다.
### Request
- Method: `POST`
- Headers
  - Content-Type: `application/json`
  - `Authorization` (string): 인증 토큰 (선택적)
- Request Body:
```json
{
  "userId": "user123",
  "items": [
    {
      "productId": "product1",
      "quantity": 2
    },
    {
      "productId": "product2",
      "quantity": 1
    }
  ],
  "couponId": "coupon123"
}
```
### Response
- Status Code: `201 Created`
- Response Body:
```json
{
  "order_id": "order123",
  "uid": "user123",
  "items": [
    {
      "productId": "product1",
      "quantity": 2,
      "price": 20000
    },
    {
      "productId": "product2",
      "quantity": 1,
      "price": 20000
    }
  ],
  "totalAmount": 40000,
  "couponDiscount": 5000,
  "finalAmount": 35000,
  "status": "success"
}
```
### Errors
- `400 Bad Request`: 잘못된 요청
- `401 Unauthorized`: 인증 실패
- `404 Not Found`: 사용자 정보 또는 상품 정보가 존재하지 않음
- `409 Conflict`: 재고 부족 또는 주문 정보 충돌
- `422 Unprocessable Entity`: 주문 처리 불가 (예: 유효하지 않은 상품 ID, 수량)
- `500 Internal Server Error`: 서버 오류