# FSSN gRPC Project

챕터 7의 Python 기반의 gRPC 예제를 kotlin을 이용한 native Android의 Client와 Go-lang을 이용한 Server로 변형하였습니다.

## 계획
- 챕터 7에 소개된 예제 1~4를 모두 구현함. 
- protobuf는 예제에 사용된 코드와 동일하게 사용. 
- python client는 kotlin을 사용한 Android client로 변경
- python server는 go-lang으로 변경

## 구체적 코드
- 안드로이드 Github : [링크](https://github.com/vkehfdl1/FSSN_grpc)
- Go-lang 서버 Github : [링크](https://github.com/vkehfdl1/FSSN_grpc_server)
- 사용한 protobuf : [링크](https://github.com/vkehfdl1/FSSN_grpc_proto)

## Description
GrpcTask.kt 파일에 대부분은 gRPC client 구현 코드가 담겨있습니다. Go 서버의 host 및 port 번호를 넣어주어야 작동합니다.
예제 2,3,4는 Log에서 server 통신 결과를 확인할 수 있습니다.
