echo build 폴더 삭제
rm -rf ./build/

echo 프로젝트 빌드
./gradlew build

echo docker image 생성
docker build -t infinity4869/underline-app .

echo image push
docker push infinity4869/underline-app

#echo network 확인
#docker network inspect underline-network

# local mount
#docker run -d -p 8080:8080 -v /Users/wanjongth/dev/vol/underline:/var/underline:rw --name=underline-app --network=underline-network infinity4869/underline-app