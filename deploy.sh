echo build 폴더 삭제
rm -rf ./build/

echo 프로젝트 빌드
./gradlew build

echo docker image 생성
docker build -t infinity4869/underline-app .

echo network 확인
docker network inspect underline-network

echo 실행
docker run -d -p 8080:8080 --name=underline-app --network=underline-network infinity4869/underline-app