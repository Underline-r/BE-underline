sudo docker pull infinity4869/underline-app
sudo nohup docker run -p 8080:8080 -v /etc/localtime:/etc/localtime:ro -e TZ=Asia/Seoul --name=underline-app infinity4869/underline-app > nohup_test.out 2>&1 &