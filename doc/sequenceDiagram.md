```mermaid
sequenceDiagram
    actor 고객
    participant 예약
    participant 결제
    participant 항공권

    고객->>예약: 예약 취소 요청
    예약->>예약: 취소 수수료 계산
    예약->>결제: 결제 환불 요청
    결제-->>예약: 환불 완료
    예약->>항공권: 좌석 배정 취소 요청
    항공권-->>예약: 좌석 배정 취소 완료
    예약-->>고객: 예약 취소 알림
```
